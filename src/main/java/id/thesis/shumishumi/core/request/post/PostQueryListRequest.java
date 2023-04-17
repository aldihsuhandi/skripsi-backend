package id.thesis.shumishumi.core.request.post;

import id.thesis.shumishumi.core.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class PostQueryListRequest extends BaseRequest {
    private static final long serialVersionUID = -167640848127625891L;

    private String title;
    private List<String> tags = new ArrayList<>();
    private int pageNumber = 1;
    private int numberOfItem = 10;
}
