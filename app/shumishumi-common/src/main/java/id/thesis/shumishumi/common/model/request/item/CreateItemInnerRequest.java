/**
 *
 */
package id.thesis.shumishumi.common.model.request.item;

import id.thesis.shumishumi.common.model.request.BaseInnerRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: CreateItemInnerRequest.java, v 0.1 2023‐01‐16 5:00 PM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class CreateItemInnerRequest extends BaseInnerRequest {
    private static final long serialVersionUID = -4904517867685475927L;

    private String itemId;
    private String postId;
    private String itemName;
    private Long itemPrice;
    private List<String> itemImages;
    private String itemDescription;
    private Integer itemQuantity;
    private String categoryId;
    private String hobbyId;
    private String merchantLevelId;
    private String merchantId;
}
