package id.thesis.shumishumi.foundation.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class RoleDAORequest implements Serializable {
    private static final long serialVersionUID = -7543638415614194237L;

    private String roleId;
    private String roleName;
}
