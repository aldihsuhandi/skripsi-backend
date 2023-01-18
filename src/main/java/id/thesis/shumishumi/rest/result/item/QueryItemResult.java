/**
 * Dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package id.thesis.shumishumi.rest.result.item;

import id.thesis.shumishumi.common.model.context.PagingContext;
import id.thesis.shumishumi.common.model.viewobject.ItemVO;
import id.thesis.shumishumi.rest.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.catalina.LifecycleState;

import java.util.List;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
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