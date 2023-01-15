package id.thesis.shumishumi.test.facade;

import id.thesis.shumishumi.dalgen.service.ClientDAO;
import id.thesis.shumishumi.dalgen.service.ContentDAO;
import id.thesis.shumishumi.dalgen.service.OtpDAO;
import id.thesis.shumishumi.dalgen.service.RoleDAO;
import id.thesis.shumishumi.dalgen.service.SessionDAO;
import id.thesis.shumishumi.dalgen.service.UserDAO;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TestBase {
    @MockBean
    protected UserDAO userDAO;
    @MockBean
    protected ContentDAO contentDAO;
    @MockBean
    protected OtpDAO otpDAO;
    @MockBean
    protected SessionDAO sessionDAO;
    @MockBean
    protected RoleDAO roleDAO;
    @MockBean
    protected ClientDAO clientDAO;
}
