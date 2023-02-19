/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.facade.result.session;

import id.thesis.shumishumi.common.model.viewobject.SessionVO;
import id.thesis.shumishumi.facade.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: SessionQueryResult.java, v 0.1 2022‐12‐30 1:13 PM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class SessionQueryResult extends BaseResult {
    private SessionVO sessionVO;
}
