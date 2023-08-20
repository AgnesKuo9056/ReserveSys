package common.util;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.Date;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class EmailUtil {
    private static String fromEmail = "1763168563@qq.com";
    private static String fromEmailPw = "zvawajvocixgdhha";
    private static String myEmailSMTPHost = "smtp.qq.com";
    private static Properties props;
    private static Session session;
    private String vCode;
    public static EmailUtil instance = new EmailUtil();

    public String getVCode() {
        return this.vCode;
    }

    private EmailUtil() {
        props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", myEmailSMTPHost);
        props.setProperty("mail.smtp.auth", "true");
        session = Session.getInstance(props);
    }

    public MimeMessage createMailContent(String toEmail) throws Exception, MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail, "验证码发送系统", "UTF-8"));
        message.setRecipient(RecipientType.TO, new InternetAddress(toEmail));
        message.setSubject("验证码", "UTF-8");
        this.vCode = VCodeUtil.verifyCode(6);
        message.setContent("您好，您的验证码是：" + this.vCode + "。", "text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }

    public void sendEmail(String toEmail) throws Exception {
        Transport transport = session.getTransport();
        transport.connect(fromEmail, fromEmailPw);
        MimeMessage message = this.createMailContent(toEmail);
        transport.sendMessage(message, message.getAllRecipients());
        System.out.println("验证码发送成功！");
        transport.close();
    }

    public static void main(String[] args) {
        try {
            instance.sendEmail("1763168563@qq.com");
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }
}
