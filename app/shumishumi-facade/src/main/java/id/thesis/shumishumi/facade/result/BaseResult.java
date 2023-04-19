package id.thesis.shumishumi.facade.result;

import id.thesis.shumishumi.facade.model.context.ResultContext;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class BaseResult implements Serializable {
    private static final long serialVersionUID = -2195644164642266073L;

    private ResultContext resultContext;
}
