package id.thesis.shumishumi.foundation.model.result;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dictionaries")
public class DictionaryDO extends BaseDO {
    private static final long serialVersionUID = -3760116238701963417L;

    @Id
    @Column(name = "dictionary_name")
    private String dictionaryName;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "dictionary_type")
    private String dictionaryType;

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDictionaryType() {
        return dictionaryType;
    }

    public void setDictionaryType(String dictionaryType) {
        this.dictionaryType = dictionaryType;
    }
}
