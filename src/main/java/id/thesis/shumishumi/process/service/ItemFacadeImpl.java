/**
 * Dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package id.thesis.shumishumi.process.service;

import id.thesis.shumishumi.common.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.core.facade.ItemFacade;
import id.thesis.shumishumi.process.callback.ProcessCallback;
import id.thesis.shumishumi.process.callback.ProcessCallbackSupport;
import id.thesis.shumishumi.rest.request.item.CreateItemRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import id.thesis.shumishumi.rest.result.item.CreateItemResult;
import org.springframework.stereotype.Service;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: ItemFacadeImpl.java, v 0.1 2023‐01‐16 4:25 PM Aldih Suhandi Exp $$
 */
@Service
public class ItemFacadeImpl extends ProcessFacade implements ItemFacade {
    @Override
    public CreateItemResult create(CreateItemRequest request) {
        return (CreateItemResult) ProcessCallbackSupport.process(ProcessTypeEnum.ITEM_CREATE, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new CreateItemResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }
}
