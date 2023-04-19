package id.thesis.shumishumi.facade.model.viewobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemCategoryVO extends BaseVO {
    private static final long serialVersionUID = 424393159142676091L;

    private String categoryId;
    private String categoryName;
}
