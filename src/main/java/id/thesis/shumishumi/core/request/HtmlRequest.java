/**
 * 
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.core.request;

import id.thesis.shumishumi.common.model.context.Headers;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: HtmlRequest.java, v 0.1 2022‐12‐26 2:59 PM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class HtmlRequest<T> {
    private Headers head;
    private T body;
}