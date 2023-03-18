/**
 * 
 *
 */
package id.thesis.shumishumi.dalgen.service;

import id.thesis.shumishumi.dalgen.model.result.HobbyDO;

import java.util.List;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: HobbyDAO.java, v 0.1 2023‐01‐17 13:50 Aldih Suhandi Exp $$
 */
public interface HobbyDAO {
    List<HobbyDO> queryAll();
    HobbyDO queryById(String hobbyId);
    HobbyDO queryByName(String hobbyName);
    void create(String hobbyId, String hobbyName);
}
