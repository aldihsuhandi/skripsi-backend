package id.thesis.shumishumi.foundation.seeders;

import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.model.enumeration.InterestLevelEnum;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.result.ItemDO;
import id.thesis.shumishumi.foundation.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ItemSeeders extends BaseSeeder {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    void setOrder() {
        this.order = 10;
    }

    @Override
    void setSeederName() {
        this.seederName = "item seeder";
    }

    @Override
    void deleteRecords() {
        itemRepository.deleteAll();
    }

    @Override
    void seed() {
        List<ItemRequest> r = new ArrayList<>();

        // KeyboardShop
        // KEYBOARD
        // KEYBOARD_SWITCH
        r.add(new ItemRequest("Gateron Yellow", 3500L, "Gateron yellow", 999,
                Collections.singletonList("https://www.mechanicalkeyboards.com/switches/images/Gateron_Yellow_Switch_45716_large.jpg"),
                "KEYBOARD_SWITCH", "KEYBOARD", "KeyboardShop", InterestLevelEnum.BEGINNER.getId()));

        r.add(new ItemRequest("Tecsee Sapphire V2", 6000L, "Tecsee Sapphire V2", 999,
                Collections.singletonList("https://cdn.shopify.com/s/files/1/0275/3649/0561/products/tecsee-sapphire-v2-tactile-switches-907344_1024x1024.jpg?v=1631230596"),
                "KEYBOARD_SWITCH", "KEYBOARD", "KeyboardShop", InterestLevelEnum.BEGINNER.getId()));

        r.add(new ItemRequest("Durock L1", 8000L, "Durock Linear L1 60 gram actuation forces", 999,
                Arrays.asList("https://cdn.shopify.com/s/files/1/0573/7339/3094/products/1_e30e589f-4d9d-484d-a02a-a52709a76213_grande.png?v=1623956493",
                        "https://cdn.shopify.com/s/files/1/0573/7339/3094/products/3_a2318fe4-fafe-4ade-8301-78e0eca4fbda_grande.png?v=1623956493"),
                "KEYBOARD_SWITCH", "KEYBOARD", "KeyboardShop", InterestLevelEnum.ENTHUSIAST.getId()));

        // KEYCAPS

        // DIY_KIT

        // ComputerShop
        // COMPUTER
        // CPU
        r.add(new ItemRequest("AMD Ryzen 5 3600", 3200000L, "AMD Ryzen 5 3600, 6 Core 12 Threads", 29,
                Arrays.asList("https://cdn.wccftech.com/wp-content/uploads/2016/12/AMD-RYZEN-ZEN.jpg",
                        "https://www.bhphotovideo.com/images/images1000x1000/amd_100_100000031box_ryzen_5_3600_3_6_1485462.jpg"),
                "CPU", "COMPUTER", "ComputerShop", InterestLevelEnum.INTERMEDIATE.getId()));

        r.add(new ItemRequest("Intel i7 12700k", 5200000L, "Intel i& 12700k, unlocked processor from intel, base clock 3.6 Ghz", 29,
                Arrays.asList("https://i.pcmag.com/imagery/reviews/07rfvBq3YYV4bfaooOD3INP-4.fit_lim.size_768x.jpg",
                        "https://i.pcmag.com/imagery/reviews/07rfvBq3YYV4bfaooOD3INP-1.fit_scale.size_1028x578.v1640904828.jpg"),
                "CPU", "COMPUTER", "ComputerShop", InterestLevelEnum.ENTHUSIAST.getId()));

        // GPU

        // RAM

        // MOTHERBOARD

        // GuitarShop
        // MUSIC
        // GUITAR

        r.forEach(this::insert);
    }

    private void insert(ItemRequest request) {
        ItemDO itemDO = convertFromRequest(request);
        try {
            itemRepository.save(itemDO);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    private ItemDO convertFromRequest(ItemRequest request) {

        List<String> images = new ArrayList<>();
        request.imageUrl.forEach(url -> images.add(downloadImage(url)));

        StringBuilder sb = new StringBuilder();
        images.forEach(image -> sb.append(image).append(CommonConst.SEPARATOR));

        String imageStr = sb.substring(0, sb.length() - 1);

        ItemDO item = new ItemDO();
        item.setItemId(FunctionUtil.generateUUID());
        item.setItemName(request.itemName);
        item.setItemPrice(request.itemPrice);
        item.setItemDescription(request.itemDescription);
        item.setItemQuantity(request.itemQuantity);
        item.setItemImages(imageStr);
        item.setCategoryId(request.categoryId);
        item.setHobbyId(request.hobbyId);
        item.setMerchantId(request.merchantId);
        item.setMerchantLevelId(request.merchantLevelId);
        item.setDeleted(false);
        item.setApproved(true);

        return item;
    }

    private final class ItemRequest {
        String itemName;
        Long itemPrice;
        String itemDescription;
        Integer itemQuantity;
        List<String> imageUrl;
        String categoryId;
        String hobbyId;
        String merchantId;
        String merchantLevelId;

        public ItemRequest(String itemName, Long itemPrice, String itemDescription, Integer itemQuantity, List<String> imageUrl, String categoryId, String hobbyId, String merchantId, String merchantLevelId) {
            this.itemName = itemName;
            this.itemPrice = itemPrice;
            this.itemDescription = itemDescription;
            this.itemQuantity = itemQuantity;
            this.imageUrl = imageUrl;
            this.categoryId = categoryId;
            this.hobbyId = hobbyId;
            this.merchantId = merchantId;
            this.merchantLevelId = merchantLevelId;
        }
    }
}
