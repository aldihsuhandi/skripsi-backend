package id.thesis.shumishumi.foundation.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostDO extends BaseDO {
    private static final long serialVersionUID = -5065151365666389613L;

    private String postId;
    private String userId;
    private String title;
    private String content;
    private String tags;
    private String images;
    private boolean isDeleted = false;
}
