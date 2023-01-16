package id.thesis.shumishumi.common.model.context;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingContext {
    private Integer pageNumber = 1;
    private Integer numberOfItem = 10;
    private Boolean hasNext = false;
}
