package com.sweetcat.user_info.interfaces.web.controller;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.user_info.domain.user.entity.User;
import com.sweetcat.user_info.interfaces.facade.UserInfoFacade;
import com.sweetcat.user_info.interfaces.facade.assembler.UserAssembler;
import com.sweetcat.user_info.interfaces.facade.restdto.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/25-14:50
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private UserInfoFacade userInfoFacade;
    private UserAssembler userAssembler;

    @Autowired
    public void setFacade(UserInfoFacade userInfoFacade) {
        this.userInfoFacade = userInfoFacade;
    }

    @Autowired
    public void setUserAssembler(UserAssembler userAssembler) {
        this.userAssembler = userAssembler;
    }

    /**
     * 获得用户详情
     *
     * @param userId userId
     * @return 用户详情
     */
    @GetMapping("/{user_id}")
    public ResponseDTO getUserInfo(@PathVariable("user_id") Long userId) {
        User user = userInfoFacade.getUserInfo(userId);

        // response data 部分
        HashMap<String, Object> userInfo = new HashMap<>(2);
        userInfo.put("user_info", userAssembler.converter2UserInfoDTO(user));
        return response("一切OK", userInfo);
    }

    /**
     * 修改用户昵称
     *
     * @param userId   userId
     * @param nickName nickName
     */
    @GetMapping(value = "/{user_id}", params = {"nickName"})
    public ResponseDTO changeNickName(@PathVariable("user_id") Long userId, String nickName) {
        userInfoFacade.changeNickName(userId, nickName);
        return response("修改昵称成功", "{}");
    }

    /**
     * 修改用户性别
     *
     * @param userId userId
     * @param gender gender
     */
    @GetMapping(value = "/{user_id}", params = {"gender"})
    public ResponseDTO changeGender(@PathVariable("user_id") Long userId, Integer gender) {
        userInfoFacade.changeGender(userId, gender);
        return response("修改性别成功", "{}");
    }

    /**
     * 修改用户 生日
     *
     * @param userId   userId
     * @param birthday birthday
     */
    @GetMapping(value = "/{user_id}", params = {"birthday"})
    public ResponseDTO changeBirthday(@PathVariable("user_id") Long userId, Long birthday) {
        userInfoFacade.changeBirthday(userId, birthday);
        return response("修改生日成功", "{}");
    }

    /**
     * 修改用户 个性签名
     *
     * @param userId                userId
     * @param personalizedSignature personalizedSignature
     */
    @GetMapping(value = "/{user_id}", params = {"personalizedSignature"})
    public ResponseDTO changePersonalizedSignature(@PathVariable("user_id") Long userId, String personalizedSignature) {
        userInfoFacade.changePersonalizedSignature(userId, personalizedSignature);
        return response("修改个性签名成功", "{}");
    }

    /**
     * 修改用头像
     *
     * @param userId userId
     * @param avatar avatar
     */
    @PostMapping("/{user_id}/avatar/upload")
    public Map changeAvatar(@PathVariable("user_id") Long userId, @RequestParam("avatar") MultipartFile avatar) {
        String avatarPath = userInfoFacade.changeAvatar(userId, avatar);

        HashMap<String, String> response = new HashMap<>(2);
        response.put("name", avatarPath);
        response.put("status", "done");
        response.put("url", avatarPath);
        return response;
    }

    /**
     * 修改手机号
     *
     * @param userId userId
     * @param phone  phone
     */
    @PostMapping("/{user_id}/phone")
    public ResponseDTO changePhone(@PathVariable("user_id") Long userId, String phone) {
        userInfoFacade.changePhone(userId, phone);
        String tip = "换绑手机号成功";
        return response(tip, "{}");
    }

    /**
     * 修改密码
     *
     * @param userId   userId
     * @param password password
     */
    @PostMapping("/{user_id}/password")
    public ResponseDTO changePassword(@PathVariable("user_id") Long userId, String password) {
        userInfoFacade.changePassword(userId, password);
        String tip = "修改密码成功";
        return response(tip, "{}");
    }

    /**
     * 账号密码登录
     *
     * @param userId   userId
     * @param password password
     * @return
     */
    @PostMapping(value = "/login", params = {"user_id", "password"})
    public ResponseDTO login(Long userId, String password) {
        // 登录，返回登录成功时的用户信息
        User userLogined = userInfoFacade.loginByPassword(userId, password);
        return loginCommonCode(userLogined);
    }

    /**
     * 手机号登录
     *
     * @param phone   phone
     * @param captcha captcha
     * @return
     */
    @PostMapping(value = "/login", params = {"phone", "captcha"})
    public ResponseDTO login(String phone, String captcha) {
        User userLogined = userInfoFacade.loginByPhone(phone, captcha);
        return loginCommonCode(userLogined);
    }

    /**
     * 两种登录方式中，相同代码的抽取
     *
     * @param userLogined 以登录成功的用户的信息
     * @return ResponseDTO
     */
    private ResponseDTO loginCommonCode(User userLogined) {
        // User 转 UserInfoDTO
        UserInfoDTO userInfoDTO = userAssembler.converter2UserInfoDTO(userLogined);
        // 登录成，生成jwt
        String jwt = userInfoFacade.generateJwt(userLogined);
        // response data 部分
        HashMap<String, Object> userInfo = new HashMap<>(2);
        userInfo.put("user_info", userInfoDTO);
        userInfo.put("token", jwt);
        return response("登录成功", userInfo);
    }

    /**
     * 注册账号
     *
     * @param nickname nickname
     * @param password password
     * @param gender   gender
     * @param birthday birthday
     * @param phone    phone
     * @return
     */
    @PostMapping("/register")
    public ResponseDTO register(String nickname, String password, Integer gender, Long birthday, String phone) {
        // 注册账号
        User user = userInfoFacade.register(nickname, password, gender, birthday, phone);
        // response data 部分
        HashMap<String, Long> userId = new HashMap<>(2);
        userId.put("user_id", user.getUserId());
        return response("注册成功，前往登录吧！", userId);
    }

    /**
     * 获取用于登录的 captcha
     *
     * @param phone phone
     * @return
     */
    @GetMapping("/login/captcha")
    public ResponseDTO getCaptchaForLogin(String phone) {
        userInfoFacade.getCaptchaForLogin(phone);
        String tip = "验证码以发送到您的手机，请注意查收喔";
        return response(tip, "{}");
    }

    /**
     * 获取用于注册的 captcha
     *
     * @param phone phone
     * @return
     */
    @GetMapping("/register/captcha")
    public ResponseDTO getCaptchaForRegister(String phone) {
        userInfoFacade.getCaptchaForRegister(phone);
        String tip = "验证码以发送到您的手机，请注意查收喔";
        return response(tip, "{}");
    }

    /**
     * 向 phone 发送验证码
     *
     * @param phone phone
     * @return
     */
    @GetMapping("/captcha")
    public ResponseDTO getCaptcha(String phone) {
        userInfoFacade.getCaptcha(phone);
        String tip = "验证码以发送到您的手机，请注意查收喔";
        return response(tip, "{}");
    }

    /**
     * 验证手机号 与 验证码是否配对
     *
     * @param phone   phone
     * @param captcha captcha
     * @return
     */
    @PostMapping("/captcha/verification")
    public ResponseDTO verifyCaptcha(String phone, String captcha) {
        System.out.println("verificaiton : ----- phone: " + phone + " captcha: " + captcha);
        userInfoFacade.verifyCaptcha(phone, captcha);
        String tip = "验证码验证通过";
        return response(tip, "{}");
    }


    /**
     * 通用的放回 ResponseDTO
     *
     * @param tip  用户提示信息
     * @param data 数据部分
     * @return ResponseDTO
     */
    private ResponseDTO response(String tip, Object data) {
        return new ResponseDTO(
                ResponseStatusEnum.SUCCESS.getErrorCode(),
                ResponseStatusEnum.SUCCESS.getErrorMessage(),
                tip,
                data
        );
    }
}
