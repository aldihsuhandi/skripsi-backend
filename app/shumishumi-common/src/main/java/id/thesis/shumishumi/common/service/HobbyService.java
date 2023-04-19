/**
 *
 */
package id.thesis.shumishumi.common.service;

import id.thesis.shumishumi.facade.model.viewobject.HobbyVO;

import java.util.List;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: HobbyService.java, v 0.1 2023‐01‐18 10:51 Aldih Suhandi Exp $$
 */
public interface HobbyService {
    List<HobbyVO> queryAll();

    HobbyVO query(String key, String identifier);

    void create(String hobbyName);
}
