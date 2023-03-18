/**
 * 
 *
 */
package id.thesis.shumishumi.dalgen.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: CategoryDO.java, v 0.1 2023‐01‐17 13:08 Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class ItemCategoryDO extends BaseDO {
    private static final long serialVersionUID = 7645598343037731746L;

    private String categoryId;
    private String categoryName;
}
