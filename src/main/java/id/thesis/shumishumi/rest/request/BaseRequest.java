package id.thesis.shumishumi.rest.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class BaseRequest implements Serializable {
    private static final long serialVersionUID = -8455088724935820070L;

    private String sessionId;
}
