package com.sweetcat.user_info.interfaces.facade;

import com.sweetcat.user_info.application.command.address.AddAddressCommand;
import com.sweetcat.user_info.application.command.address.EditAddressCommand;
import com.sweetcat.user_info.application.service.UserAddressApplicationService;
import com.sweetcat.user_info.application.service.UserInfoApplicationService;
import com.sweetcat.user_info.domain.address.entity.UserAddress;
import com.sweetcat.user_info.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/25-19:15
 * @Version: 1.0
 */
@Component
public class UserInfoFacade {

    private UserInfoApplicationService userInfoApplicationService;
    private UserAddressApplicationService userAddressApplicationService;

    @Autowired
    public void setUserInfoApplicationService(UserInfoApplicationService userInfoApplicationService) {
        this.userInfoApplicationService = userInfoApplicationService;
    }

    @Autowired
    public void setUserAddressApplicationService(UserAddressApplicationService userAddressApplicationService) {
        this.userAddressApplicationService = userAddressApplicationService;
    }

    /**
     * 获得用户详情
     *
     * @param userId userId
     * @return 用户详情
     */
    public User getUserInfo(Long userId) {
        return this.userInfoApplicationService.getUserInfo(userId);
    }

    /**
     * 修改用户昵称
     *
     * @param userId      userId
     * @param newNickName newNickName
     */
    public void changeNickName(Long userId, String newNickName) {
        userInfoApplicationService.changeNickName(userId, newNickName);
    }

    /**
     * 修改用户性别
     *
     * @param userId userId
     * @param gender gender
     */
    public void changeGender(Long userId, Integer gender) {
        userInfoApplicationService.changeGender(userId, gender);
    }

    /**
     * 修改用户 生日
     *
     * @param userId   userId
     * @param birthday birthday
     */
    public void changeBirthday(Long userId, Long birthday) {
        userInfoApplicationService.changeBirthday(userId, birthday);
    }

    /**
     * 修改用户 个性签名
     *
     * @param userId                userId
     * @param personalizedSignature personalizedSignature
     */
    public void changePersonalizedSignature(Long userId, String personalizedSignature) {
        userInfoApplicationService.changePersonalizedSignature(userId, personalizedSignature);
    }

    /**
     * 向 phone 发送用于 login 的 captcha
     *
     * @param phone phone
     */
    public void getCaptchaForLogin(String phone) {
        userInfoApplicationService.getCaptchaForLogin(phone);
    }

    /**
     * 向 phone 发送用于 register 的 captcha
     *
     * @param phone phone
     */
    public void getCaptchaForRegister(String phone) {
        userInfoApplicationService.getCaptchaForRegister(phone);
    }

    /**
     * 发送验证码
     *
     * @param phone phone
     */
    public void getCaptcha(String phone) {
        userInfoApplicationService.sendCaptcha(phone);
    }

    /**
     * 验证 phone 与 captcha 是否配对
     *
     * @param phone   phone
     * @param captcha captcha
     */
    public void verifyCaptcha(String phone, String captcha) {
        userInfoApplicationService.verifyCaptcha(phone, captcha);
    }

    /**
     * 通过密码登录
     *
     * @param userId userId
     * @param pwd    pwd
     * @return 登录成功的用户信息
     */
    public User loginByPassword(Long userId, String pwd) {
        return userInfoApplicationService.loginByPassword(userId, pwd);
    }

    /**
     * 通过手机号登录
     *
     * @param phone   phone
     * @param captcha captcha
     * @return 登录成功的用户信息
     */
    public User loginByPhone(String phone, String captcha) {
        return userInfoApplicationService.loginByPhone(phone, captcha);
    }

    /**
     * 注册账号
     *
     * @param nickname nickname
     * @param password password
     * @param gender   gender
     * @param birthday birthday
     * @param phone    phone
     * @return 注册成功的用户信息
     */
    public User register(String nickname, String password, Integer gender, Long birthday, String phone) {
        return userInfoApplicationService.register(nickname, password, gender, birthday, phone);
    }

    /**
     * 根据 user 生成 jwt token
     *
     * @param user user
     * @return jws
     */
    public String generateJwt(User user) {
        return this.userInfoApplicationService.generateJwt(user);
    }

    /**
     * 修改用头像
     *
     * @param userId userId
     * @param avatar avatar
     */
    public String changeAvatar(Long userId, MultipartFile avatar) {
        return userInfoApplicationService.changeAvatar(userId, avatar);
    }

    /**
     * 修改手机号
     * @param userId userId
     * @param newPhone newPhone
     */
    public void changePhone(Long userId, String newPhone) {
        userInfoApplicationService.changePhone(userId, newPhone);
    }


    /**
     * 修改密码
     *
     * @param userId   userId
     * @param password password
     */
    public void changePassword(Long userId, String password) {
        userInfoApplicationService.changePassword(userId, password);
    }
}
