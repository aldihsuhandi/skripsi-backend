package id.thesis.shumishumi.facade.request.cache;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RefreshCacheRequest extends BaseRequest {
    private static final long serialVersionUID = -3238646889292524799L;

    private boolean refreshItemCache = true;
    private boolean refreshUserCache = true;
    private boolean refreshDictionaryCache = true;
}
