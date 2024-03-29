package id.thesis.shumishumi.foundation.model.request;

import id.thesis.shumishumi.facade.model.context.PagingContext;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class PostDAORequest implements Serializable {
    private static final long serialVersionUID = 3224709948482272481L;

    private String postId;
    private String userId;
    private String title;
    private String content;
    private String tags;
    private String images;
    private PagingContext pagingContext;
}
