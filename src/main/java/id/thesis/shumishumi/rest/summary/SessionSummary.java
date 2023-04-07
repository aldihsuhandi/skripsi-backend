package id.thesis.shumishumi.rest.summary;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class SessionSummary extends BaseSummary {
    private static final long serialVersionUID = 8247996709447850819L;

    private String email;
    private Date sessionDt;
    private boolean isRemembered;
}
