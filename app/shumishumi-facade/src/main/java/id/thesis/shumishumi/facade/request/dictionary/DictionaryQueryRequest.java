package id.thesis.shumishumi.facade.request.dictionary;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DictionaryQueryRequest extends BaseRequest {
    private static final long serialVersionUID = -4039800021071802593L;

    private String dictionaryKey;
}
