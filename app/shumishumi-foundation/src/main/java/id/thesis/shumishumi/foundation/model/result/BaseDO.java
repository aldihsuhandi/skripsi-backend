package id.thesis.shumishumi.foundation.model.result;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class BaseDO implements Serializable {
    private static final long serialVersionUID = -347896096392840987L;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gmt_create", precision = 3, updatable = false)
    private Date gmtCreate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gmt_modified", precision = 3, updatable = false)
    private Date gmtModified;

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
