package id.thesis.shumishumi.foundation.model.result;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "email_encrypt")
public class EmailEncryptDO extends BaseDO {
    private static final long serialVersionUID = -1210746244513957140L;

    @Id
    @Column(name = "uuid")
    private String uuid;

    @Column(name = "email")
    private String email;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
