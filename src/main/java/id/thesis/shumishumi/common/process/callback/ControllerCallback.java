/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.common.process.callback;

import id.thesis.shumishumi.core.result.BaseResult;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ControllerCallback.java, v 0.1 2022‐12‐26 3:13 PM Aldih Suhandi Exp $$
 */
public interface ControllerCallback {

    void authCheck();

    BaseResult initResult();

    BaseResult doProcess();
}
