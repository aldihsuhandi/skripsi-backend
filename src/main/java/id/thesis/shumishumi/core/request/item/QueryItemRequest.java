/**
 *
 */
package id.thesis.shumishumi.core.request.item;

import id.thesis.shumishumi.common.model.context.ItemFilterContext;
import id.thesis.shumishumi.core.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: QueryItemRequest.java, v 0.1 2023‐01‐18 11:41 Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class QueryItemRequest extends BaseRequest {
    private static final long serialVersionUID = -2932956834568197652L;

    private ItemFilterContext itemFilterContext = new ItemFilterContext();
    private int pageNumber = 1;
    private int numberOfItem = 10;
}
