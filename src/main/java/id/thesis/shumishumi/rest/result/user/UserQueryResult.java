/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.rest.result.user;

import id.thesis.shumishumi.common.model.viewobject.UserVO;
import id.thesis.shumishumi.rest.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: UserQueryResult.java, v 0.1 2022‐12‐28 9:15 AM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class UserQueryResult extends BaseResult {
    private UserVO userInfo;
}
