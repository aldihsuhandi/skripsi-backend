package id.thesis.shumishumi.common.model.form.item.wishlist;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddWishlistForm extends BaseForm {
    private static final long serialVersionUID = -930381596836047660L;

    private String itemId;
}
