package id.thesis.shumishumi.rest.result.item;

import id.thesis.shumishumi.common.model.context.PagingContext;
import id.thesis.shumishumi.common.model.viewobject.ItemVO;
import id.thesis.shumishumi.rest.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.mail.FetchProfile;
import java.util.List;

@Getter
@Setter
@ToString
public class RecommendResult extends BaseResult {
    private static final long serialVersionUID = -8047870347183552861L;

    private List<ItemVO> items;
    private PagingContext pagingContext;
}
