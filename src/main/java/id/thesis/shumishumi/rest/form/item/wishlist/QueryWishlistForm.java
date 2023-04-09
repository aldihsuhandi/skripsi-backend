package id.thesis.shumishumi.rest.form.item.wishlist;

import id.thesis.shumishumi.common.model.context.ItemFilterContext;
import id.thesis.shumishumi.rest.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QueryWishlistForm extends BaseForm {
    private static final long serialVersionUID = -6428169694570723815L;

    private int pageNumber = 1;
    private int numberOfItem = 10;
    private ItemFilterContext itemFilterContext;
}
