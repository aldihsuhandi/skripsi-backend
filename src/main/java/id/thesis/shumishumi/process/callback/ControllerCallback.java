/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.process.callback;

import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.rest.result.BaseResult;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: ControllerCallback.java, v 0.1 2022‐12‐26 3:13 PM Aldih Suhandi Exp $$
 */
public interface ControllerCallback {

    void authCheck() throws ShumishumiException;

    BaseResult initResult();

    BaseResult doProcess();
}
