package id.thesis.shumishumi.facade.request.item;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AutocompleteItemRequest extends BaseRequest {
    private static final long serialVersionUID = -8834548708694212113L;

    private String autocomplete;
}
