package cn.chen.util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public final class MailUtil {
    private MailUtil() {
    }

    //	账号信息
    private static final String username = "example@163.com";//	邮箱发送账号
    private static final String password = "xxxxxxx";//	邮箱授权码

    private static Session createSession() {



        //	创建一个配置文件，并保存
        Properties props = new Properties();

        //	SMTP服务器连接信息
        //  126——smtp.126.com
        //  163——smtp.163.com
        //  qqsmtp.qq.com"
        props.put("mail.smtp.host", "smtp.163.com");//	SMTP主机名

        //  126——25
        //  163——465
        props.put("mail.smtp.port", "465");//	主机端口号
        props.put("mail.smtp.auth", "true");//	是否需要用户认证
        props.put("mail.smtp.starttls.enale", "true");//	启用TlS加密
        props.put("mail.smtp.ssl.enable", true);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // TODO Auto-generated method stub
                return new PasswordAuthentication(username, password);
            }
        });

        //  控制台打印调试信息
        session.setDebug(true);
        return session;
    }

    public static void SendEmil(String toEmail, String text) throws MessagingException {
        //	创建Session会话
        Session session = createSession();

        //	创建邮件对象
        MimeMessage message = new MimeMessage(session);
        message.setSubject("你好吗");
        message.setText(text);
        message.setFrom(new InternetAddress(username));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toEmail));

        //	发送
        Transport.send(message);
    }
}