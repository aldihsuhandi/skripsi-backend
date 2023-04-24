package id.thesis.shumishumi.foundation.seeders;

import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.enumeration.UserRolesEnum;
import id.thesis.shumishumi.foundation.model.result.UserDO;
import id.thesis.shumishumi.foundation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSeeder extends BaseSeeder {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void setOrder() {
        this.order = 2;
    }

    @Override
    void setSeederName() {
        this.seederName = "user seeders";
    }

    @Override
    public void deleteRecords() {
        userRepository.deleteAll();
    }

    public UserSeeder() {
        super();
    }

    @Override
    public void seed() {
        List<UserRequest> userRequests = new ArrayList<>();
        userRequests.add(new UserRequest("KeyboardShop", "Keyboard Shop",
                "KeyboardShop@mail.com", generatePhoneNumber(), UserRolesEnum.MERCHANT.getUserRoleId(), true, false,
                "https://cdn.shopify.com/s/files/1/0472/8874/6142/files/DSC00120.jpg", "keyboard123"));

        userRequests.add(new UserRequest("ComputerShop", "Computer Shop",
                "ComputerShop@mail.com", generatePhoneNumber(), UserRolesEnum.MERCHANT.getUserRoleId(), true, false,
                "https://c1.neweggimages.com/ProductImageCompressAll1280/19-118-343-05.jpg", "computer123"));

        userRequests.add(new UserRequest("GuitarShop", "Guitar Shop",
                "GuitarShop@mail.com", generatePhoneNumber(), UserRolesEnum.MERCHANT.getUserRoleId(), true, false,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/45/GuitareClassique5.png/366px-GuitareClassique5.png", "guitar123"));

        userRequests.forEach(this::createUser);
    }

    private String generatePhoneNumber() {
        return "08" + FunctionUtil.generateOtp(9, true, false);
    }

    private void createUser(UserRequest userRequest) {
        String imageId = downloadImage(userRequest.profilePicture);

        UserDO userDO = new UserDO();
        userDO.setUserId(userRequest.userId);
        userDO.setUsername(userRequest.username);
        userDO.setEmail(userRequest.email);
        userDO.setPhoneNumber(userRequest.phoneNumber);
        userDO.setRoleId(userRequest.roleId);
        userDO.setActive(userRequest.isActive);
        userDO.setActive(userRequest.isDeleted);
        userDO.setProfilePicture(imageId);
        userDO.setPassword(FunctionUtil.hashPassword(userRequest.password));

        try {
            userRepository.save(userDO);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

    }

    private class UserRequest {
        String userId;
        String username;
        String email;
        String phoneNumber;
        String roleId;
        boolean isActive;
        boolean isDeleted;
        String profilePicture;
        String password;

        public UserRequest(String userId, String username, String email, String phoneNumber, String roleId, boolean isActive, boolean isDeleted, String profilePicture, String password) {
            this.userId = userId;
            this.username = username;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.roleId = roleId;
            this.isActive = isActive;
            this.isDeleted = isDeleted;
            this.profilePicture = profilePicture;
            this.password = password;
        }
    }
}
