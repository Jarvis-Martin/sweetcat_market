package com.sweetcat.commons.global_exception_handler;

import com.sweetcat.commons.exception.*;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.commons.util.JSONUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/24-22:54
 * @Version: 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthenticateFailException.class)
    @ResponseBody
    public ResponseDTO AuthenticateFailExceptionHandler(AuthenticateFailException e) {
        return new ResponseDTO(
                e.getCode().toString(),
                e.getMessage(),
                "登录认证失败，可能输入信息有错误，请重新一下吧",
                JSONUtils.fromJson("{}", Object.class));
    }

    @ExceptionHandler(CaptchaErroException.class)
    @ResponseBody
    public ResponseDTO CaptchaErroExceptionHandler(CaptchaErroException e) {
        return new ResponseDTO(
                e.getCode().toString(),
                e.getMessage(),
                "验证码错误",
                JSONUtils.fromJson("{}", Object.class));
    }

    @ExceptionHandler(CaptchaTimeoutException.class)
    @ResponseBody
    public ResponseDTO CaptchaTimeoutExceptionHandler(CaptchaTimeoutException e) {
        return new ResponseDTO(
                e.getCode().toString(),
                e.getMessage(),
                "验证码已经过时了",
                JSONUtils.fromJson("{}", Object.class));
    }

    @ExceptionHandler(CaptchaSendedException.class)
    @ResponseBody
    public ResponseDTO CaptchaSendedExceptionHandler(CaptchaSendedException e) {
        return new ResponseDTO(
                e.getCode().toString(),
                e.getMessage(),
                "验证码已经发送过了，请稍后",
                JSONUtils.fromJson("{}", Object.class));
    }
    @ExceptionHandler(ParameterException.class)
    @ResponseBody
    public ResponseDTO ParameterExceptionHandler(ParameterException e) {
        return new ResponseDTO(
                e.getCode().toString(),
                e.getMessage(),
                "出错了，请稍后再重试吧",
                JSONUtils.fromJson("{}", Object.class));
    }

    @ExceptionHandler(ParameterFormatIllegalityException.class)
    @ResponseBody
    public ResponseDTO ParameterFormatIllegalityExceptionHandler(ParameterFormatIllegalityException e) {
        return new ResponseDTO(
                e.getCode().toString(),
                e.getMessage(),
                "参数格式错误了喔",
                JSONUtils.fromJson("{}", Object.class));
    }

    @ExceptionHandler(RedisSaveFailException.class)
    @ResponseBody
    public ResponseDTO RedisSaveFailExceptionHandler(RedisSaveFailException e) {
        return new ResponseDTO(
                e.getCode().toString(),
                e.getMessage(),
                "服务器出小差了，请等一下下再试试吧",
                JSONUtils.fromJson("{}", Object.class));
    }

    @ExceptionHandler(SmsSendException.class)
    @ResponseBody
    public ResponseDTO SmsSendExceptionHandler(SmsSendException e) {
        return new ResponseDTO(
                e.getCode().toString(),
                e.getMessage(),
                "短信发送失败了",
                JSONUtils.fromJson("{}", Object.class));
    }

    @ExceptionHandler(UesrExistedException.class)
    @ResponseBody
    public ResponseDTO UesrExistedExceptionHandler(UesrExistedException e) {
        return new ResponseDTO(
                e.getCode().toString(),
                e.getMessage(),
                "改手机号已经注册过了，直接登录吧",
                JSONUtils.fromJson("{}", Object.class));
    }
    @ExceptionHandler(UesrNotExistedException.class)
    @ResponseBody
    public ResponseDTO UesrNotExistedExceptionHandler(UesrNotExistedException e) {
        return new ResponseDTO(
                e.getCode().toString(),
                e.getMessage(),
                "该用户还没有注册喔，前往注册一个吧",
                JSONUtils.fromJson("{}", Object.class));
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public ResponseDTO ExceptionHandler(Exception e) {
//        return new ResponseDTO(
//                "500",
//                e.getMessage(),
//                "服务器出错",
//                JSONUtils.fromJson("{}", Object.class));
//    }
}
