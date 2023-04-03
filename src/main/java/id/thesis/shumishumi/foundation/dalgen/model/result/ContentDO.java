package id.thesis.shumishumi.foundation.dalgen.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContentDO extends BaseDO {
    private static final long serialVersionUID = 1365074682615114070L;

    private String contentName;
    private String content;
}
