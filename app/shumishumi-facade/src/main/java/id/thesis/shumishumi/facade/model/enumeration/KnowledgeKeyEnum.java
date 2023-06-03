package id.thesis.shumishumi.facade.model.enumeration;

import lombok.Getter;

@Getter
public enum KnowledgeKeyEnum {
    MERCHANT_LEVEL("KNOWLEDGE_MERCHANT_LEVEL"),
    USER_LEVEL("KNOWLEDGE_USER_LEVEL"),
    CATEGORY("KNOWLEDGE_CATEGORY"),
    HOBBY("KNOWLEDGE_HOBBY"),
    ;

    private final String key;

    KnowledgeKeyEnum(String key) {
        this.key = key;
    }
}
