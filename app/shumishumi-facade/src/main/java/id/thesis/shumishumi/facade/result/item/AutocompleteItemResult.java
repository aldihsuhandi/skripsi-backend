package id.thesis.shumishumi.facade.result.item;

import id.thesis.shumishumi.facade.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
public class AutocompleteItemResult extends BaseResult {
    private static final long serialVersionUID = 5294808264354958290L;

    private Set<String> itemName;
}
