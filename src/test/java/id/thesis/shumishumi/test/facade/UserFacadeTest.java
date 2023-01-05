package id.thesis.shumishumi.test.facade;

import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.facade.UserFacade;
import id.thesis.shumishumi.dalgen.model.result.RoleDO;
import id.thesis.shumishumi.dalgen.model.result.UserDO;
import id.thesis.shumishumi.dalgen.service.RoleDAO;
import id.thesis.shumishumi.dalgen.service.UserDAO;
import id.thesis.shumishumi.rest.request.user.UserRegisterRequest;
import id.thesis.shumishumi.rest.result.user.UserRegisterResult;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;

@SpringBootTest
public class UserFacadeTest {
    @Autowired
    private UserFacade userFacade;

    @MockBean
    private UserDAO userDAO;

    @MockBean
    private RoleDAO roleDAO;

    @Test
    public void userRegisterTest_SUCCESS() {
        UserRegisterRequest registerRequest = new UserRegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setEmail("email@email.com");
        registerRequest.setPassword("password");
        registerRequest.setPhoneNumber("12345");

        Mockito.when(userDAO.queryByEmail(Mockito.any())).thenReturn(null);
        Mockito.when(userDAO.queryByPhoneNumber(Mockito.any())).thenReturn(null);

        UserRegisterResult result = userFacade.register(registerRequest);
        try {
            AssertUtil.isExpected(result.getResultContext().isSuccess(),
                    "result", ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        } catch (ShumishumiException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void userRegisterTest_Failed() {
        UserRegisterRequest registerRequest = new UserRegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setEmail("email@email.com");
        registerRequest.setPassword("password");
        registerRequest.setPhoneNumber("12345");

        Mockito.when(userDAO.queryByEmail(Mockito.any())).thenReturn(mockUserDO());
        Mockito.when(userDAO.queryByPhoneNumber(Mockito.any())).thenReturn(null);
        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO());

        UserRegisterResult result = userFacade.register(registerRequest);
        try {
            AssertUtil.isExpected(!result.getResultContext().isSuccess(),
                    "result", ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        } catch (ShumishumiException e) {
            throw new RuntimeException(e);
        }
    }

    private UserDO mockUserDO() {
        UserDO user = new UserDO();
        user.setUserId("userId");
        user.setEmail("email");

        return user;
    }

    private RoleDO mockRoleDO() {
        RoleDO roleDO = new RoleDO();
        roleDO.setRoleId("roleId");
        roleDO.setRoleName("roleName");
        roleDO.setGmtCreate(new Date());
        roleDO.setGmtModified(new Date());

        return roleDO;
    }
}
