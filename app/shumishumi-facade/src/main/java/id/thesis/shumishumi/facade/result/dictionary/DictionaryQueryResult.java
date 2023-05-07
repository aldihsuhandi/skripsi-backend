package id.thesis.shumishumi.facade.result.dictionary;

import id.thesis.shumishumi.facade.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class DictionaryQueryResult extends BaseResult {
    private static final long serialVersionUID = 4509615274587518279L;

    private List<String> dictionaries;
}
