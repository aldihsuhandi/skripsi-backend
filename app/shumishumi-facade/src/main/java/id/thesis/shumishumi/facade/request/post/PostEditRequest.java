package id.thesis.shumishumi.facade.request.post;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PostEditRequest extends BaseRequest {
    private static final long serialVersionUID = 1741144392577406630L;

    private String postId;
    private String title;
    private String content;
    private List<String> tags;
    private List<String> images;
}
