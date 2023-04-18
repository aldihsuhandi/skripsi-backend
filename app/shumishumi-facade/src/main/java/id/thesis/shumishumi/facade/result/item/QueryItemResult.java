/**
 *
 */
package id.thesis.shumishumi.facade.result.item;

import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import id.thesis.shumishumi.facade.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: QueryItemResult.java, v 0.1 2023‐01‐18 11:39 Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class QueryItemResult extends BaseResult {
    private static final long serialVersionUID = -1098573945789136940L;

    private List<ItemVO> items;
    private PagingContext pagingContext;
}
