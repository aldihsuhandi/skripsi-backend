package id.thesis.shumishumi.test;

import id.thesis.shumishumi.dalgen.service.ClientDAO;
import id.thesis.shumishumi.dalgen.service.ContentDAO;
import id.thesis.shumishumi.dalgen.service.HobbyDAO;
import id.thesis.shumishumi.dalgen.service.InterestLevelDAO;
import id.thesis.shumishumi.dalgen.service.ItemCategoryDAO;
import id.thesis.shumishumi.dalgen.service.ItemDAO;
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

    @MockBean
    protected ItemDAO itemDAO;

    @MockBean
    protected HobbyDAO hobbyDAO;

    @MockBean
    protected InterestLevelDAO interestLevelDAO;

    @MockBean
    protected ItemCategoryDAO itemCategoryDAO;
}
