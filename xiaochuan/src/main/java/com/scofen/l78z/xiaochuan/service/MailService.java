package com.scofen.l78z.xiaochuan.service;

import com.scofen.l78z.xiaochuan.controller.request.MailParam;
import com.scofen.l78z.xiaochuan.dao.MailDao;
import com.scofen.l78z.xiaochuan.dao.dataObject.MailDO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Example;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;
import java.util.List;

@Service
public class MailService {

    @Resource
    MailDao mailDao;

    @Resource
    JavaMailSender javaMailSender;

    @Resource
    Mapper beanMapper;

    @Value("${spring.mail.username}")
    private String from;

    public void send(MailParam param) {
        this.add(param);
        sendSimpleMail(param);
    }


    /**
     * 发送简单邮件
     */
    public void sendSimpleMail(MailParam param) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setSubject(param.getSubject());
        message.setTo(param.getEmail());
        message.setSentDate(new Date());
        message.setText(param.getMessage());
        javaMailSender.send(message);
    }

    /**
     * 发送带附件的邮件
     * @throws MessagingException
     */
    public void sendAttachFileMail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setSubject("这是一封测试邮件");
        helper.setFrom("857903497@qq.com");
        helper.setTo("1559***26@qq.com");
        helper.setSentDate(new Date());
        helper.setText("这是测试邮件的正文");
        helper.addAttachment("测试图片.jpg",new File("C:\\Users\\Administrator\\Desktop\\11.jpg"));
        javaMailSender.send(mimeMessage);
    }

    /**
     * 带图片资源的邮件
     * @throws MessagingException
     */
    public void sendImgResMail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("这是一封测试邮件");
        helper.setFrom("857903497@qq.com");
        helper.setTo("1559908926@qq.com");
        helper.setSentDate(new Date());
        helper.setText("<p>hello 大家好，这是一封测试邮件，这封邮件包含两种图片，分别如下</p><p>第一张图片：</p><img src='cid:p01'/><p>第二张图片：</p><img src='cid:p02'/>",true);
        helper.addInline("p01",new FileSystemResource(new File("C:\\Users\\Administrator\\Desktop\\11.jpg")));
        helper.addInline("p02",new FileSystemResource(new File("C:\\Users\\Administrator\\Desktop\\11.jpg")));
        javaMailSender.send(mimeMessage);
    }






    public MailDO add(MailParam param) {
        MailDO record = beanMapper.map(param, MailDO.class);
        record.setIsRemoved(0);
        mailDao.save(record);
        return record;
    }


    public List<MailDO> findAllByEmail(String email) {
        MailDO query = new MailDO();
        query.setIsRemoved(0);
        Example<MailDO> example = Example.of(query);
        return mailDao.findAll(example);
    }



}
