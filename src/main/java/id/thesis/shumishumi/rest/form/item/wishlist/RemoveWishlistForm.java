package id.thesis.shumishumi.rest.form.item.wishlist;

import id.thesis.shumishumi.rest.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RemoveWishlistForm extends BaseForm {
    private static final long serialVersionUID = 8854492628911760555L;

    private String itemId;
}
