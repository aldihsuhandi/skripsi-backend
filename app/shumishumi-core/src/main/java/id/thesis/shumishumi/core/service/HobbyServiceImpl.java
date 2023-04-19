/**
 *
 */
package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.service.HobbyService;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.core.converter.ViewObjectConverter;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.DatabaseConst;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.viewobject.HobbyVO;
import id.thesis.shumishumi.foundation.service.HobbyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: HobbyServiceImpl.java, v 0.1 2023‐01‐18 10:56 Aldih Suhandi Exp $$
 */
@Service
public class HobbyServiceImpl implements HobbyService {

    @Autowired
    private HobbyDAO hobbyDAO;

    @Override
    public List<HobbyVO> queryAll() {
        return hobbyDAO.queryAll().stream().map(
                ViewObjectConverter::toViewObject).collect(Collectors.toList());
    }

    @Override
    public HobbyVO query(String key, String identifier) {
        if (DatabaseConst.HOBBY_ID.equalsIgnoreCase(identifier)) {
            return ViewObjectConverter.toViewObject(
                    hobbyDAO.queryById(key));
        } else if (DatabaseConst.HOBBY_NAME.equalsIgnoreCase(identifier)) {
            return ViewObjectConverter.toViewObject(
                    hobbyDAO.queryByName(key));
        }
        throw new ShumishumiException("wrong identifier", ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public void create(String hobbyName) {
        hobbyDAO.create(FunctionUtil.generateUUID(), hobbyName);
    }
}
