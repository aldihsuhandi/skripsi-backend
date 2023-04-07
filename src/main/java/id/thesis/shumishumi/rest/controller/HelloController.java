package id.thesis.shumishumi.rest.controller;

import id.thesis.shumishumi.common.constant.LogConstant;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.rest.request.TestBlobRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogConstant.CONTROLLER_LOGGER);

    @GetMapping("/hello")
    public String hello(String name) {
        if (name == null) {
            name = "World";
        }
        return "Hello " + name + "!";
    }

    @PostMapping("/blob")
    public String sendBlob(@ModelAttribute TestBlobRequest request) {
        LogUtil.info(LOGGER, String.format("controller invoke request: %s", request.toString()));
        return "is successful";
    }
}
