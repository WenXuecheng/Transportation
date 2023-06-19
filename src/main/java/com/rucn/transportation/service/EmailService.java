package com.rucn.transportation.service;

import com.rucn.transportation.entity.dto.EmailDto;

/**
 * 邮箱服务接口
 *
 * @author zhuhuix
 * @date 2021-07-19
 */
public interface EmailService {

    /**
     * 发送邮件
     * @param emailDto 邮箱列表
     */
    void send(EmailDto emailDto);

    void sendMailCode(String mail, String code);
}

