package com.rucn.transportation.service.impl;

import cn.hutool.core.lang.Dict;
import cn.hutool.extra.mail.Mail;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.rucn.transportation.entity.Order;
import com.rucn.transportation.entity.User;
import com.rucn.transportation.entity.dto.EmailDto;
import com.rucn.transportation.entity.dto.OrderDto;
import com.rucn.transportation.service.EmailService;
import com.rucn.transportation.utils.VerificationCodeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * 邮箱发送接口实现类
 *
 * @author zhuhuix
 * @date 2021-07-19
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.email}")
    private String email;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private String port;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.sender}")
    private String sender;



    @Override
    public void send(EmailDto emailDto) {

        // 读取邮箱配置
        if (email == null || host == null || port == null || username == null || password == null) {
            throw new RuntimeException("邮箱配置异常");
        }

        // 设置
        MailAccount account = new MailAccount();
        account.setHost(host);
        account.setPort(Integer.parseInt(port));
        // 设置发送人邮箱
        account.setFrom(sender + "<" + email + ">");
        // 设置发送人名称
        account.setUser(username);
        // 设置发送授权码
        account.setPass(password);
        account.setAuth(true);
        // ssl方式发送
        account.setSslEnable(true);
        // 使用安全连接
//        account.setStarttlsEnable(true);

        // 发送邮件
        try {
            int size = emailDto.getTos().size();
            Mail.create(account)
                    .setTos(emailDto.getTos().toArray(new String[size]))
                    .setTitle(emailDto.getSubject())
                    .setContent(emailDto.getContent())
                    .setHtml(true)
                    //关闭session
                    .setUseGlobalSession(false)
                    .send();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void sendMailCode(String email, String code) {

        // 获取发送邮箱验证码的HTML模板
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH));
        Template template = engine.getTemplate("email-code.ftl");

        this.send(new EmailDto(Collections.singletonList(email),
                "Verification code", template.render(Dict.create().set("code", code))));

    }
    public void sendMailOrderDetails(String nickname,String email, Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderName(order.getOrderName());
        orderDto.setSendRegion(order.getSendRegion());
        orderDto.setReceiveRegion(order.getReceiveRegion());
        orderDto.setReceiveAdress(order.getReceiveAdress());
        orderDto.setDateOut(order.getDateOut());
        orderDto.setDeliveryNum(order.getDeliveryNum());
        orderDto.setOthers(order.getOthers());
        orderDto.setName(order.getName());
        orderDto.setPhone(order.getPhone());
        orderDto.setDetails(order.getDetails());
        orderDto.setRemark(order.getRemark());
        orderDto.setPay(order.getPay());
        // 获取发送邮箱验证码的HTML模板
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH));
        Template template = engine.getTemplate("email-order-details.ftl");
        Dict dict = Dict.create().set("nickname", nickname);
        dict.set("email", email);
        dict.set("OrderName", orderDto.getOrderName());
        dict.set("SendRegion", orderDto.getSendRegion());
        dict.set("ReceiveRegion", orderDto.getReceiveRegion());
        dict.set("ReceiveAdress", orderDto.getReceiveAdress());
        dict.set("DateOut", orderDto.getDateOut());
        dict.set("DeliveryNum", orderDto.getDeliveryNum());
        if (orderDto.getOthers().equals("没有/自取")){
            orderDto.setOthers("No / Self Pick Up");
        } else if (orderDto.getOthers().equals("没有/没有")){
            orderDto.setOthers("No / No");
        } else if (orderDto.getOthers().equals("保险/自取")){
            orderDto.setOthers("Insurance / Self Pick Up");
        } else if (orderDto.getOthers().equals("保险/没有")){
            orderDto.setOthers("Insurance / No");
        }
        dict.set("Others", orderDto.getOthers());
        dict.set("Name", orderDto.getName());
        dict.set("Phone", orderDto.getPhone());
        dict.set("Details", orderDto.getDetails());
        dict.set("Remark", orderDto.getRemark());
        if (orderDto.getPay().equals("在线付款")){
            orderDto.setPay("Online Payment");
        } else if (orderDto.getPay().equals("到店付款")){
            orderDto.setPay("Payment in shop");
        }
        dict.set("Pay", orderDto.getPay());
        this.send(new EmailDto(Collections.singletonList(email),
                "Order details", template.render(dict)));

    }

    public void sendMailOrderInfo(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setEmail(order.getEmail());
        orderDto.setOrderName(order.getOrderName());
        orderDto.setSendRegion(order.getSendRegion());
        orderDto.setReceiveRegion(order.getReceiveRegion());
        orderDto.setReceiveAdress(order.getReceiveAdress());
        orderDto.setDateOut(order.getDateOut());
        orderDto.setDeliveryNum(order.getDeliveryNum());
        orderDto.setOthers(order.getOthers());
        orderDto.setName(order.getName());
        orderDto.setPhone(order.getPhone());
        orderDto.setDetails(order.getDetails());
        orderDto.setRemark(order.getRemark());
        orderDto.setPay(order.getPay());
        orderDto.setWeight(order.getWeight());
        // 获取发送邮箱验证码的HTML模板
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH));
        Template template = engine.getTemplate("email-order-changed.ftl");
        Dict dict = Dict.create();
        dict.set("email", orderDto.getEmail());
        dict.set("OrderName", orderDto.getOrderName());
        dict.set("SendRegion", orderDto.getSendRegion());
        dict.set("ReceiveRegion", orderDto.getReceiveRegion());
        dict.set("ReceiveAdress", orderDto.getReceiveAdress());
        dict.set("DateOut", orderDto.getDateOut());
        dict.set("DeliveryNum", orderDto.getDeliveryNum());
        if (orderDto.getOthers().equals("没有/自取")){
            orderDto.setOthers("No / Self Pick Up");
        } else if (orderDto.getOthers().equals("没有/没有")){
            orderDto.setOthers("No / No");
        } else if (orderDto.getOthers().equals("保险/自取")){
            orderDto.setOthers("Insurance / Self Pick Up");
        } else if (orderDto.getOthers().equals("保险/没有")){
            orderDto.setOthers("Insurance / No");
        }
        dict.set("Others", orderDto.getOthers());
        dict.set("Name", orderDto.getName());
        dict.set("Phone", orderDto.getPhone());
        dict.set("Details", orderDto.getDetails());
        dict.set("Remark", orderDto.getRemark());
        if (orderDto.getPay().equals("在线付款")){
            orderDto.setPay("Online Payment");
        } else if (orderDto.getPay().equals("到店付款")){
            orderDto.setPay("Payment in shop");
        }
        dict.set("Pay", orderDto.getPay());
        dict.set("Weight", orderDto.getWeight());
        this.send(new EmailDto(Collections.singletonList(orderDto.getEmail()),
                "Order changed", template.render(dict)));
    }

    public void sendMailOrderPay(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setEmail(order.getEmail());
        orderDto.setOrderName(order.getOrderName());
        orderDto.setWeight(order.getWeight());
        orderDto.setRmb(order.getRmb());
        orderDto.setPay(order.getPay());
        orderDto.setRubs(order.getRubs());
        // 获取发送邮箱验证码的HTML模板
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH));
        Template template = engine.getTemplate("email-order-pay.ftl");
        Dict dict = Dict.create();
        dict.set("email", orderDto.getEmail());
        dict.set("OrderName", orderDto.getOrderName());
        if (orderDto.getPay().equals("在线付款")){
            orderDto.setPay("Online Payment");
        } else if (orderDto.getPay().equals("到店付款")){
            orderDto.setPay("Payment in shop");
        }
        dict.set("Pay", orderDto.getPay());
        dict.set("Weight", orderDto.getWeight());
        dict.set("rmb", orderDto.getPay());
        dict.set("rubs", orderDto.getPay());
        this.send(new EmailDto(Collections.singletonList(orderDto.getEmail()),
                "Order " + orderDto.getOrderName() + " Billing", template.render(dict)));
    }


    public void sendMailOrderOk(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setEmail(order.getEmail());
        orderDto.setOrderName(order.getOrderName());
        // 获取发送邮箱验证码的HTML模板
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH));
        Template template = engine.getTemplate("email-order-ok.ftl");
        Dict dict = Dict.create();
        dict.set("OrderName", orderDto.getOrderName());
        this.send(new EmailDto(Collections.singletonList(orderDto.getEmail()),
                "Order " + orderDto.getOrderName() + " has been successfully paid", template.render(dict)));

    }
}
