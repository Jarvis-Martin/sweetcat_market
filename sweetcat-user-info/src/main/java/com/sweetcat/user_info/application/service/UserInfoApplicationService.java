package com.sweetcat.user_info.application.service;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.*;
import com.sweetcat.user_info.domain.user.entity.User;
import com.sweetcat.user_info.domain.user.repository.UserRepository;
import com.sweetcat.user_info.domain.user.service.authen_service.AuthenService;
import com.sweetcat.user_info.domain.user.vo.UserDescriptor;
import com.sweetcat.user_info.infrastructure.factory.UserFactory;
import com.sweetcat.user_info.infrastructure.service.encrypt_service.EncryptAndDecryptService;
import com.sweetcat.user_info.infrastructure.service.encrypt_service.impl.BCryptService;
import com.sweetcat.user_info.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.user_info.infrastructure.service.jwt_service.JwtService;
import com.sweetcat.user_info.infrastructure.service.number_captcha_service.NumberCaptchaService;
import com.sweetcat.user_info.infrastructure.service.phone_format_verfiy_service.VerifyPhoneFormatService;
import com.sweetcat.user_info.infrastructure.service.redis_service.RedisService;
import com.sweetcat.user_info.infrastructure.service.snowflake_service.SnowFlakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/25-19:12
 * @Version: 1.0
 */
@Service
public class UserInfoApplicationService {
    @Value("${register-captcha-length}")
    private Integer registerCaptchaLength;
    @Value("${login-captcha-length}")
    private Integer loginCaptchaLength;
    @Value("${upload-file-path}")
    private String uploadFilePath;
    @Value("${static-server-address}")
    private String staticServerAddress;
    @Value("${avatar-img-path}")
    private String avatarImgPath;

    private UserRepository userRepository;
    private UserFactory userFactory;
    private RedisService redisService;
    private VerifyPhoneFormatService verifyPhoneFormatService;
    private VerifyIdFormatService verifyIdFormatService;

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    private JwtService jwtService;
    private SnowFlakeService snowFlakeService;

    @Autowired
    public void setSnowFlakeService(SnowFlakeService snowFlakeService) {
        this.snowFlakeService = snowFlakeService;
    }

    @Autowired
    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Autowired
    public void setVerifyPhoneFormat(VerifyPhoneFormatService verifyPhoneFormatService) {
        this.verifyPhoneFormatService = verifyPhoneFormatService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserFactory(UserFactory userFactory) {
        this.userFactory = userFactory;
    }

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }


    /**
     * 获得用户详情
     * @param userId userId
     * @return 用户详情
     */
    public User getUserInfo(Long userId) {
        verifyIdFormatService.verifyId(userId);
        return userRepository.find(userId);
    }

    /**
     * 修改用户昵称
     * @param userId userId
     * @param newNickName newNickName
     */
    public void changeNickName(Long userId, String newNickName) {
        // 验证userId
        this.verifyIdFormatService.verifyId(userId);
        // 获得用户信息
        User user = userRepository.find(userId);
        // 修改 昵称
        user.changeNickname(newNickName);
        // 保存回db
        userRepository.save(user);
    }

    /**
     * 修改用户性别
     * @param userId userId
     * @param gender gender
     */
    public void changeGender(Long userId, Integer gender) {
        // 验证userId
        this.verifyIdFormatService.verifyId(userId);
        // 获得用户信息
        User user = userRepository.find(userId);
        // 修改 性别
        user.changeGender(gender);
        // 修改 昵称
        userRepository.save(user);
    }

    /**
     * 修改用户 生日
     * @param userId userId
     * @param birthday birthday
     */
    public void changeBirthday(Long userId, Long birthday) {
        // 验证userId
        this.verifyIdFormatService.verifyId(userId);
        // 获得用户信息
        User user = userRepository.find(userId);
        // 修改 生日
        user.changeBirthday(birthday);
        // 修改 昵称
        userRepository.save(user);
    }

    /**
     * 修改用户 个性签名
     * @param userId userId
     * @param personalizedSignature personalizedSignature
     */
    public void changePersonalizedSignature(Long userId, String personalizedSignature) {
        // 验证userId
        this.verifyIdFormatService.verifyId(userId);
        // 获得用户信息
        User user = userRepository.find(userId);
        // 修改 个性签名
        user.changePersonalizedSignature(personalizedSignature);
        // 修改 昵称
        userRepository.save(user);
    }

    /**
     * 通过手账号密码登录
     *
     * @param userId userId
     * @param pwd    pwd
     * @return 登录成功的用户信息
     */
    public User loginByPassword(Long userId, String pwd) {
        // 如国 userid <= 0 抛异常
        if (userId <= 0) {
            throw new AuthenticateFailException(
                    ResponseStatusEnum.PASSWORDAUTHENTICATERROR.getErrorCode(),
                    ResponseStatusEnum.PASSWORDAUTHENTICATERROR.getErrorMessage());
        }
        // 正确的用户信息
        User user = userRepository.find(userId);
        UserDescriptor userDescriptor = new UserDescriptor(userId, pwd);
        // 认证服务：认证身份合法性
        AuthenService authenService = new AuthenService();
        Boolean authenticateOk = authenService.authenticate(userDescriptor, user);
        // 登录失败
        if (!authenticateOk) {
            throw new AuthenticateFailException(
                    ResponseStatusEnum.PASSWORDAUTHENTICATERROR.getErrorCode(),
                    ResponseStatusEnum.PASSWORDAUTHENTICATERROR.getErrorMessage()
            );
        }
        return user;
    }

    /**
     * 通过手机号登录
     *
     * @param phone   phone
     * @param captcha captcha
     * @return 登录成功的用户信息
     */
    public User loginByPhone(String phone, String captcha) {
        // 打印参数
        System.out.println("login by phone with phone" + phone);
        System.out.println("login by phone with captcha" + captcha);
        // 验证手机号格式
        this.verifyPhoneFormatService.verifyPhoneFormat(phone);
        // 验证登录验证码格式
        verifyCaptchaFormat(captcha, loginCaptchaLength);
        // 根据 phone 从 db 中获得用户信息
        User user = userRepository.find(phone);
        // 用户不存在，通知用户前往注册
        if (user == null) {
            throw new UesrNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
        // 从 redis 查询其 验证码
        String captchaStored = (String) this.redisService.get("phone_captcha:" + phone);
        // 已存储验证码 过时
        if (captchaStored == null) {
            throw new CaptchaTimeoutException(
                    ResponseStatusEnum.CAPTCHATIMEOUT.getErrorCode(),
                    ResponseStatusEnum.CAPTCHATIMEOUT.getErrorMessage()
            );
        }
        // 验证码不存在 或 超时
        compareCaptcha(captcha, captchaStored);
        // 登录成功，删除 redis 中验证码
        this.redisService.del("phone:" + phone);
        return user;
    }

    /**
     * 向 phone 发送短信验证码
     *
     * @param phone phone
     */
    public void sendCaptcha(String phone) {
        // 验证手机号格式
        this.verifyPhoneFormatService.verifyPhoneFormat(phone);
        // 判断 redis 中存在 phone
        String phoneStored = (String) redisService.get("phone:" + phone);
        // redis 中存在 phone，以发送阎真没，通知用户稍后重试（60s）
        if (phoneStored != null) {
            throw new CaptchaSendedException(
                    ResponseStatusEnum.CAPTCHASENDED.getErrorCode(),
                    ResponseStatusEnum.CAPTCHASENDED.getErrorMessage(),
                    "验证码已经发送过了，请稍后重试"
            );
        }
        // 生成 验证码
        NumberCaptchaService numberCaptchaService = new NumberCaptchaService();
        String code = numberCaptchaService.generate(registerCaptchaLength);
        System.out.println("验证码：---------------" + code);
        // 调用 SMS api 发送手机短信
//        try {
//            SmsService.send(code);
//        } catch (ServerException e) {
//            throw new SmsSendException(
//                    ResponseStatusEnum.SMSSERVERDIDEERROR.getErrorCode(),
//                    ResponseStatusEnum.SMSSERVERDIDEERROR.getErrorMessage()
//            );
//        } catch (ClientException e) {
//            throw new SmsSendException(
//                    ResponseStatusEnum.SMSCLIENTSIDEERROR.getErrorCode(),
//                    ResponseStatusEnum.SMSCLIENTSIDEERROR.getErrorMessage()
//            );
//        }
        // 手机号存入 redis 60s
        redisService.setnx("phone:" + phone, phone, (long) (60));
        // 发送给用户的验证码存入 redis 5min
        redisService.set("phone_captcha:" + phone, code, (long) (5 * 60));
    }

    /**
     * 发送短信，用于 注册的发送短信的api
     *
     * @param phone phone
     */
    public void getCaptchaForRegister(String phone) {
        // 检查手机号格式
        this.verifyPhoneFormatService.verifyPhoneFormat(phone);
        // 根据 phone 查找用户
        User user = userRepository.find(phone);
        // 用户已注册，通知用户前往登录
        if (user != null) {
            throw new UesrExistedException(
                    ResponseStatusEnum.USEREXISTED.getErrorCode(),
                    ResponseStatusEnum.USEREXISTED.getErrorMessage()
            );
        }
        // 向 phone 发送验证码
        sendCaptcha(phone);
    }

    /**
     * 发送短信，用户登录的短信验证码
     *
     * @param phone phone
     */
    public void getCaptchaForLogin(String phone) {
        // 检查手机号格式
        this.verifyPhoneFormatService.verifyPhoneFormat(phone);
        // 根据 phone 查询用户信息
        User user = userRepository.find(phone);
        // 该手机号尚未注册
        if (user == null) {
            throw new UesrNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
        // 向 phone 发送验证码
        sendCaptcha(phone);
    }

    /**
     * 验证手机验证码
     *
     * @param phone   phone
     * @param captcha captcha
     */
    public void verifyCaptcha(String phone, String captcha) {
        // 检查手机格式
        this.verifyPhoneFormatService.verifyPhoneFormat(phone);
        // 检查验证码格式
        verifyCaptchaFormat(captcha, loginCaptchaLength);
        // 从 redis 中查询 验证码
        String captchaStored = (String) redisService.get("phone_captcha:" + phone);
        // 打印参数
        System.out.println("user phone------------------" + phone);
        System.out.println("captchaStored ------------" + captchaStored);
        // 已存储验证码 过时
        if (captchaStored == null) {
            throw new CaptchaTimeoutException(
                    ResponseStatusEnum.CAPTCHATIMEOUT.getErrorCode(),
                    ResponseStatusEnum.CAPTCHATIMEOUT.getErrorMessage()
            );
        }
        // 比较用户提供的验证码 和 从redis中查到的验证码
        compareCaptcha(captcha, captchaStored);
        // 比较相同，删除 redis 中验证码
        redisService.del("phone_captcha:" + phone);
    }

    /**
     * 对比 captcha 和 captchStored 是否相同
     *
     * @param captcha       captcha
     * @param captchaStored captchaStored
     */
    private void compareCaptcha(String captcha, String captchaStored) {
        // 比较 captcha 和 captchStored 是否相同
        boolean captchaIsEquals = captchaStored.equals(captcha);
        // 不相同
        if (!captchaIsEquals) {
            throw new CaptchaErroException(
                    ResponseStatusEnum.CAPTCHAERROR.getErrorCode(),
                    ResponseStatusEnum.CAPTCHAERROR.getErrorMessage()
            );
        }
    }

    /**
     * 注册账号
     *
     * @param nickname nickname
     * @param password password
     * @param gender   gender
     * @param birthday birthday
     * @param birthday birthday
     * @return 注册成功后的用户信息
     */
    public User register(String nickname, String password, Integer gender, Long birthday, String phone) {
        // 检查手机号格式
        this.verifyPhoneFormatService.verifyPhoneFormat(phone);
        // 根据 phone 查找用户信息
        User user = userRepository.find(phone);
        // 该手机号已注册
        if (user != null) {
            throw new UesrExistedException(
                    ResponseStatusEnum.USEREXISTED.getErrorCode(),
                    ResponseStatusEnum.USEREXISTED.getErrorMessage()
            );
        }
        // 获取 空user
        user = userFactory.createEmpty();
        // 加密明文密码
        EncryptAndDecryptService encryptAndDecryptService = new BCryptService();
        String encryptedPassword = encryptAndDecryptService.encrypt(password);
        // 注册 user（填充必要数据）
        user.register(nickname, encryptedPassword, gender, birthday, phone);
        // 插入 db
        userRepository.save(user);
        // 再次通过 phone 找到刚注册的用户信息（以便获得其 userId）
        user = userRepository.find(phone);
        // 返回 userid
        return user;
    }

    /**
     * 检查验证码格式：null？长度？
     *
     * @param captcha       captcha
     * @param captchaLength captchaLength
     */
    private void verifyCaptchaFormat(String captcha, Integer captchaLength) {
        // captcha 为 null，通知用户参数格式错误
        if (captcha == null) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        // 正则匹配验证码
        boolean captchaMatches = Pattern.matches("\\d{" + captchaLength + "}", captcha);
        // captcha 不匹配
        if (!captchaMatches) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
    }

    /**
     * 根据 user 生成 jwt token
     *
     * @param user user
     * @return jws
     */
    public String generateJwt(User user) {
        String jwtId = String.valueOf(this.snowFlakeService.snowflakeId());
        String issuer = "蓝盘子商城";
        String subject = "登录";
        long ttlMillis = 1000 * 60 * 30;
        HashMap<String, Object> claim = new HashMap<>(4);
        claim.put("userId", user.getUserId());
        claim.put("userNickname", user.getNickname());
        try {
            return this.jwtService.createJwt(jwtId, issuer, subject, ttlMillis, claim);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 修改用头像
     * @param userId userId
     * @param avatar avatar
     * @return 头像文件路径
     */
    public String changeAvatar(Long userId, MultipartFile avatar) {

        // 保存头像文件到 nginx，返回 头像文件路径
        String avatarPath = saveFile(avatar);
        System.out.println("头像文件路径 --------" + avatarPath);
        // 找到 user
        User user = userRepository.find(userId);
        // 修改头像路径
        user.changeAvatarPath(avatarPath);
        // 保存回db
        userRepository.save(user);
        // 返回头像路径
        return user.getAvatarPath();
    }

    /**
     * 保存文件到 nginx
     * @param file file
     * @return 保存的文件的文件名
     */
    private String saveFile(MultipartFile file) {
        // 上传文件的文件名
        String randomId = UUID.randomUUID().toString();
        String fileName = file.getOriginalFilename();
        String destFileName = randomId + fileName;
        File dest = new File(uploadFilePath +'/'+ destFileName);
        System.out.println(uploadFilePath +'/'+ destFileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new SaveFileFailException(
                    ResponseStatusEnum.SAVEFILEFAILE.getErrorCode(),
                    ResponseStatusEnum.SAVEFILEFAILE.getErrorMessage()
            );
        }
        return staticServerAddress + avatarImgPath + destFileName;
    }

    /**
     * 修改手机号
     * @param userId userId
     * @param newPhone newPhone
     */
    public void changePhone(Long userId, String newPhone) {
        // 找到 userid 对应信息
        User user = userRepository.find(userId);
        // 改变手机号
        user.changePhone(newPhone);
        // 保存修改
        userRepository.save(user);
    }
}
