package id.thesis.shumishumi.common.service;

public interface ResetPasswordService {
    String resetPassword(String email);

    String queryResetPassword(String uuid);

    void invalidateResetPassword(String uuid);
}