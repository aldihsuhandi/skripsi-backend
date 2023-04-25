/**
 *
 */
package id.thesis.shumishumi.foundation.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: CategoryDO.java, v 0.1 2023‐01‐17 13:08 Aldih Suhandi Exp $$
 */
@Entity
@Table(name = "item_categories")
public class ItemCategoryDO extends BaseDO {
    private static final long serialVersionUID = 7645598343037731746L;

    @Id
    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "category_name")
    private String categoryName;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
