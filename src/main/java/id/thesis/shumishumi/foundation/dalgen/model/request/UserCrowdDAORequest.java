package id.thesis.shumishumi.foundation.dalgen.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class UserCrowdDAORequest implements Serializable {
    private static final long serialVersionUID = -6392632401832585280L;

    private String userId;
}
