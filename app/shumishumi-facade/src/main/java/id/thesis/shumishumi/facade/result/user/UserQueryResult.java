/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.facade.result.user;

import id.thesis.shumishumi.facade.model.summary.UserSummary;
import id.thesis.shumishumi.facade.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: UserQueryResult.java, v 0.1 2022‐12‐28 9:15 AM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class UserQueryResult extends BaseResult {
    private UserSummary userInfo;
}
