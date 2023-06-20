package id.thesis.shumishumi.common.model.form.cache;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RefreshCacheForm extends BaseForm {
    private static final long serialVersionUID = 3826026196769167738L;

    private boolean refreshUserCache = true;
    private boolean refreshItemCache = true;
    private boolean refreshDictionaryCache = true;
}
