package com.artmall.email;


import com.artmall.config.RedisTokenManager;
import com.artmall.pojo.Business;
import com.artmall.pojo.Student;
import com.artmall.response.ServerResponse;
import com.artmall.utils.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author mllove
 * @create 2018-08-22 15:15
 **/
@Service
public class EmailServiceImpl implements EmailService {
    private final static Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);


    private static final String SIGN_TITLE_SIGN_UP = "The register massage";

    private static final String FORGET_TITLE_SIGN_UP = "The register massage";

    private static final String SIGN_CONTENT = "please check it哈哈:";

    private static final String FORGET_CONTENT = "please reset the password:";
    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String from;


    //可以发送，发送成功
  /*  @Override
    public ServerResponse sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
//        try {
            emailSender.send(message);
            return ServerResponse.Success("邮件已发送");
//        }catch (Exception e){
//            return ServerResponse.Failure("邮件发送失败");
//        }
    }*/
    //可发送，但是会乱码
/*    public  ServerResponse  test(Business business,String token){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(business.getEmail());
        message.setSubject("验证邮件");
        String link = "http://localhost:8080/business/register/verify?token="+token+"&id="+business.getId();
        String mes=String.format(CONTENT,business.getBusinessName(),link,link);
        message.setText(link+mes);
        emailSender.send(message);
        return ServerResponse.Success("Send Sucess");
    }*/

    /**
     * 发送注册邮件
     * @param business
     * @param token
     * @return
     */
    @Override
    public ServerResponse registerEmail(Business business, String token) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            helper.setFrom(from);
            helper.setTo(business.getEmail());
            helper.setSubject(SIGN_TITLE_SIGN_UP);
            String link = "http://120.79.239.141:8080/business/register/verify?token="+token+"&id="+business.getId();
            String message = business.getBusinessName()+SIGN_CONTENT+"\n"+link;
            helper.setText(message);
            emailSender.send(mimeMessage);
        } catch (MessagingException e) {
            return ServerResponse.Failure("send failure");
        }
        return ServerResponse.Success("send sucess");

    }

    @Autowired
    RedisTokenManager redisTokenManager;
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 验证token是否有效
     * @param id
     * @param token
     * 返回true表示验证成功，返回false表示验证失败
     */
    @Override
    public Boolean userValidate(Long id, String token) {
        Business valBusiness= (Business) redisTemplate.opsForValue().get(token);
        if (id.equals(valBusiness.getId()))
            return true;
        else
            return false;
    }

    /**
     * 忘记密码，邮箱找回，发送验证码
     * @param user
     * @return
     */
    @Override
    public ServerResponse sendResetEmail(Business user,String code) {

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            helper.setFrom(from);
            helper.setTo(user.getEmail());
            helper.setSubject(FORGET_TITLE_SIGN_UP);
            String message = user.getBusinessName()+FORGET_CONTENT+"\n"+code;
            helper.setText(message);
            emailSender.send(mimeMessage);
        } catch (MessagingException e) {
            return ServerResponse.Failure("send failure");
        }
        return ServerResponse.Success("send sucess");
    }


    public ServerResponse sendResetEmail(Student user, String code) {

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            helper.setFrom(from);
            helper.setTo(user.getEmail());
            helper.setSubject(FORGET_TITLE_SIGN_UP);
            String message = user.getLoginName()+FORGET_CONTENT+"\n"+code;
            helper.setText(message);
            emailSender.send(mimeMessage);
        } catch (MessagingException e) {
            return ServerResponse.Failure("send failure");
        }
        return ServerResponse.Success("send sucess");
    }

    /**
     * 判断code是否有效
     * @param business
     * @param code
     * @return
     */
    @Override
    public boolean codeVerify(Business business, String code) {
        Business redisBusiness = (Business) redisTemplate.opsForValue().get(code);
        if (redisBusiness.getId().equals(business.getId()))
            return true;
        else
            return false;
    }

    /**
     * 判断code是否有效
     * @param student
     * @param code
     * @return
     */
    @Override
    public boolean codeVerify(Student student, String code) {
        Student redStudent = (Student) redisTemplate.opsForValue().get(code);
        if (redStudent.getId().equals(student.getId()))
            return true;
        else
            return false;
    }
}
