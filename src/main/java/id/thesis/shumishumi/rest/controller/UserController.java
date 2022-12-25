package id.thesis.shumishumi.rest.controller;

import id.thesis.shumishumi.core.facade.UserFacade;
import id.thesis.shumishumi.rest.request.user.UserRegisterRequest;
import id.thesis.shumishumi.rest.result.user.UserRegisterResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @PostMapping("/register")
    public UserRegisterResult register(UserRegisterRequest request) {
        return userFacade.register(request);
    }
}
