package id.thesis.shumishumi.foundation.dalgen.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ItemCrowdDAORequest implements Serializable {
    private static final long serialVersionUID = -789119598911304000L;

    private String crowdId;
}
