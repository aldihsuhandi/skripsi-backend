package id.thesis.shumishumi.facade.model.context;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResultContext {
    private String resultMsg;
    private String resultCode;
    private boolean success;
}
