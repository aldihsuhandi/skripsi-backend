package id.thesis.shumishumi.facade.model.summary;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@ToString
public class UserSummary extends BaseSummary {
    private static final long serialVersionUID = -987412761200895475L;

    private String email;
    private String phoneNumber;
    private String profilePicture;
    private String username;
    private String role;
    private String gender;
    private Date dateOfBirth;
    private Map<String, String> location;
    private boolean canWhatsapp = false;
    private boolean canTelegram = false;
}
