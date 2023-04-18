package id.thesis.shumishumi.facade.model.summary;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class BaseSummary implements Serializable {
    private static final long serialVersionUID = 4977988913128131207L;

    private Date gmtCreate;
    private Date gmtModified;
}
