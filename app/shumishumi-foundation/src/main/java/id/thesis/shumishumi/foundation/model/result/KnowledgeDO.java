package id.thesis.shumishumi.foundation.model.result;

import id.thesis.shumishumi.foundation.model.result.primarykey.KnowledgeDOPK;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "knowledges")
public class KnowledgeDO extends BaseDO {
    private static final long serialVersionUID = 7117537078556372809L;

    @EmbeddedId
    private KnowledgeDOPK pk;

    @Column(name = "knowledge")
    private String knowledge;

    public KnowledgeDOPK getPk() {
        return pk;
    }

    public void setPk(KnowledgeDOPK pk) {
        this.pk = pk;
    }

    public String getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
    }
}
