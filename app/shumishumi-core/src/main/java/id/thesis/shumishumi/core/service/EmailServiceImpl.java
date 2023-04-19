package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.model.request.otp.OTPSendInnerRequest;
import id.thesis.shumishumi.common.service.EmailService;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.result.ContentDO;
import id.thesis.shumishumi.foundation.service.ContentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;


@Service
public class EmailServiceImpl implements EmailService {

    @Value("email.username")
    private String email;

    @Autowired
    private ContentDAO contentDAO;

    private JavaMailSender javaMailSender;

    @Async
    @Override
    public void sendOtpEmail(OTPSendInnerRequest request) {
        String recipients = request.getEmail();
        String otp = request.getOtp();

        ContentDO contentDO = contentDAO.queryContent(CommonConst.OTP_EMAIL);
        String content = String.format(contentDO.getContent(), recipients, otp);
        String subject = "Shumishumi Verification Email";

        sendMessage(subject, content, recipients);
    }

    private void sendMessage(String subject, String content, String recipients) {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, "utf-8");
        try {
            helper.setTo(recipients);
            helper.setFrom(email);
            helper.setSubject(subject);
            helper.setText(content, true);

            javaMailSender.send(msg);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
}
