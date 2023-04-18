/**
 *
 */
package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.facade.model.viewobject.InterestLevelVO;

import java.util.List;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: InterestLevelService.java, v 0.1 2023‐01‐18 10:51 Aldih Suhandi Exp $$
 */
public interface InterestLevelService {
    List<InterestLevelVO> queryAll();

    InterestLevelVO query(String key, String identifier);
}
