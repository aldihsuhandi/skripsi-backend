package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.request.ItemDAORequest;
import id.thesis.shumishumi.foundation.model.result.ItemDO;
import id.thesis.shumishumi.foundation.repository.ItemRepository;
import id.thesis.shumishumi.foundation.service.ItemDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemDAOImpl implements ItemDAO {

    private static final Logger DALGEN_LOGGER = LoggerFactory.
            getLogger(LogConstant.DALGEN_LOGGER);

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<ItemDO> queryAll(ItemDAORequest request) {

        LogUtil.info(DALGEN_LOGGER, String.format("itemDAO#queryAll[request=%s]", request.toString()));

        PagingContext pagination = request.getPagingContext();
        Pageable pageable = PageRequest.of(pagination.calculateOffset(), pagination.getNumberOfItem());

        List<ItemDO> result;
        try {
            result = itemRepository.findAll(pageable).stream().collect(Collectors.toList());
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN_LOGGER, String.format("itemDAO#queryAll[result=%s]", result));

        return result;
    }

    @Override
    public ItemDO queryById(ItemDAORequest request) {
        LogUtil.info(DALGEN_LOGGER, String.format("itemDAO#queryById[request=%s]", request.toString()));

        ItemDO result;
        try {
            result = itemRepository.findById(request.getItemId()).orElse(null);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN_LOGGER, String.format("itemDAO#queryById[result=%s]", result));

        return result;
    }

    @Override
    public List<ItemDO> query(ItemDAORequest request) {
        LogUtil.info(DALGEN_LOGGER, String.format("itemDAO#query[request=%s]", request.toString()));

        PagingContext paging = request.getPagingContext();

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withMatcher("itemName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        ItemDO itemDO = convertToExampleDO(request);
        Example<ItemDO> exampleObject = Example.of(itemDO, matcher);

        Pageable pageable = PageRequest.of(paging.getPageNumber(), paging.getNumberOfItem());
        List<ItemDO> result;
        try {
            if (request.getMinPrice() != null && request.getMaxPrice() != null) {
                result = itemRepository.findByItemPriceBetween(exampleObject, request.getMinPrice(),
                        request.getMaxPrice(), pageable).getContent();
            } else if (request.getMinPrice() != null) {
                result = itemRepository.findByItemPriceGreaterThanEqual(exampleObject,
                        request.getMinPrice(), pageable).getContent();
            } else if (request.getMaxPrice() != null) {
                result = itemRepository.findByItemPriceLessThanEqual(exampleObject,
                        request.getMaxPrice(), pageable).getContent();
            } else {
                result = itemRepository.findAll(exampleObject, pageable).getContent();
            }
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN_LOGGER, String.format("itemDAO#query[result=%s]", result));

        return result;
    }

    @Override
    public int count() {
        LogUtil.info(DALGEN_LOGGER, "itemDAO#count[]");

        int result = 0;
        try {
            result = (int) itemRepository.count();
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN_LOGGER, String.format("itemDAO#count[result=%d]", result));

        return result;
    }

    @Override
    public void create(ItemDO request) {
        LogUtil.info(DALGEN_LOGGER, String.format("itemDAO#create[request=%s]", request.toString()));

        try {
            itemRepository.save(request);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void update(ItemDO request) {
        LogUtil.info(DALGEN_LOGGER, String.format("itemDAO#update[request=%s]", request.toString()));

        try {
            itemRepository.save(request);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void updateImage(ItemDAORequest request) {
        LogUtil.info(DALGEN_LOGGER, String.format("itemDAO#updateImage[request=%s]", request));
        try {
            itemRepository.updateImageByItemId(request.getItemId(), request.getItemImages());
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void approve(ItemDAORequest request) {
        LogUtil.info(DALGEN_LOGGER, String.format("itemDAO#approve[request=%s]", request.toString()));
        try {
            itemRepository.approve(request.getItemId());
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public List<String> autocomplete(ItemDAORequest request) {
        LogUtil.info(DALGEN_LOGGER, String.format("itemDAO#autocomplete[request=%s]", request.toString()));

        Pageable pageable = PageRequest.of(1, 15);
        ItemDO itemDO = new ItemDO();
        itemDO.setItemName(request.getItemName());
        itemDO.setApproved(true);
        itemDO.setDeleted(false);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("itemName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        Example<ItemDO> exampleObject = Example.of(itemDO, matcher);

        List<String> result;
        try {
            result = itemRepository.findAll(exampleObject, pageable).getContent().stream().
                    map(ItemDO::getItemName).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN_LOGGER, String.format("itemDAO#autocomplete[result=%s]", result));

        return result;
    }

    private ItemDO convertToExampleDO(ItemDAORequest request) {
        if (request == null) {
            return new ItemDO();
        }

        ItemDO item = new ItemDO();
        item.setItemName(request.getItemName());
        item.setCategoryId(request.getCategoryId());
        item.setHobbyId(request.getHobbyId());
        item.setMerchantId(request.getMerchantId());
        item.setMerchantLevelId(request.getMerchantLevelId());

        return item;
    }
}
