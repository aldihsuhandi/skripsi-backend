package id.thesis.shumishumi.foundation.model.result;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contents")
public class ContentDO extends BaseDO {
    private static final long serialVersionUID = 1365074682615114070L;

    @Id
    @Column(name = "content_name")
    private String contentName;

    @Column(name = "content")
    private String content;

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
