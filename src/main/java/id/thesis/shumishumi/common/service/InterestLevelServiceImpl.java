/**
 * 
 *
 */
package id.thesis.shumishumi.common.service;

import id.thesis.shumishumi.common.util.constant.DatabaseConst;
import id.thesis.shumishumi.common.util.converter.ViewObjectConverter;
import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.model.viewobject.InterestLevelVO;
import id.thesis.shumishumi.core.service.InterestLevelService;
import id.thesis.shumishumi.foundation.dalgen.service.InterestLevelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: InterestLevelServiceImpl.java, v 0.1 2023‐01‐18 10:56 Aldih Suhandi Exp $$
 */
@Service
public class InterestLevelServiceImpl implements InterestLevelService {

    @Autowired
    private InterestLevelDAO interestLevelDAO;

    @Override
    public List<InterestLevelVO> queryAll() {
        return interestLevelDAO.queryAll().stream().
                map(ViewObjectConverter::toViewObject).collect(Collectors.toList());
    }

    @Override
    public InterestLevelVO query(String key, String identifier) {
        if (DatabaseConst.INTEREST_LEVEL_ID.equalsIgnoreCase(identifier)) {
            return ViewObjectConverter.toViewObject(interestLevelDAO.queryById(key));
        } else if (DatabaseConst.INTEREST_LEVEL_NAME.equalsIgnoreCase(identifier)) {
            return ViewObjectConverter.toViewObject(interestLevelDAO.queryByName(key));
        }
        throw new ShumishumiException("wrong identifier", ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }
}
