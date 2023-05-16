package id.thesis.shumishumi.facade.model.viewobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartVO extends BaseVO {
    private static final long serialVersionUID = -7941515958656437439L;

    private String userId;
    private ItemVO item;
    private int quantity;
}
