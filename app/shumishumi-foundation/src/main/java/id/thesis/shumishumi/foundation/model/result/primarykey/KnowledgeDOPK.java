package id.thesis.shumishumi.foundation.model.result.primarykey;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class KnowledgeDOPK implements Serializable {
    private static final long serialVersionUID = 4370156231676059189L;

    @Column(name = "knowledge_key")
    private String knowledgeKey;

    @Column(name = "knowledge_type")
    private String knowledgeType;

    public KnowledgeDOPK(String knowledgeKey, String knowledgeType) {
        this.knowledgeKey = knowledgeKey;
        this.knowledgeType = knowledgeType;
    }

    public KnowledgeDOPK() {
    }

    public String getKnowledgeKey() {
        return knowledgeKey;
    }

    public void setKnowledgeKey(String knowledgeKey) {
        this.knowledgeKey = knowledgeKey;
    }

    public String getKnowledgeType() {
        return knowledgeType;
    }

    public void setKnowledgeType(String knowledgeType) {
        this.knowledgeType = knowledgeType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
