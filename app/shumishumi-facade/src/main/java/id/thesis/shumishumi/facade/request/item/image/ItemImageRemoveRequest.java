package id.thesis.shumishumi.facade.request.item.image;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ItemImageRemoveRequest extends BaseRequest {
    private static final long serialVersionUID = -1933781384140313255L;

    private String itemId;
    private List<String> imageIds;
}
