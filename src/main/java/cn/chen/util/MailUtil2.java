package cn.chen.util;

import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
@Slf4j
public class MailUtil2 {
    // todo: Cannot invoke "org.springframework.mail.javamail.JavaMailSenderImpl.createMimeMessage()" because "this.mailSender" is null
    @Resource
    private JavaMailSenderImpl mailSender;

    @Value("${spring.mail.username}")
    private String username;

    public void sendMail(String email, String msg) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //构建邮件信息
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("你好吗");
        helper.setText(msg, true);
        helper.setTo(email);
        helper.setFrom(username);
        //调用邮箱服务发送邮件
        mailSender.send(mimeMessage);
        log.info("打印该信息-邮件发送成功--》{}", (Object) mimeMessage.getFrom());
    }
}