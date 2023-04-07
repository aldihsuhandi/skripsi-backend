package id.thesis.shumishumi.common.model.summary;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Blob;

@Getter
@Setter
@ToString
public class UserSummary extends BaseSummary {
    private static final long serialVersionUID = -987412761200895475L;

    private String email;
    private String phoneNumber;
    private String username;
    private Blob profilePicture;
    private String role;
}
