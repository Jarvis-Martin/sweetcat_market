package com.sweetcat.commons;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/15-21:00
 * @Version: 1.0
 */
public enum ResponseStatusEnum {

    /**
     * 成功处理时的错误码 和 错误信息
     */
    SUCCESS("00000", "一切 ok"),
    /**
     * 用户请求参数错误时的错误码 和 错误信息
     */
    PARAMETERERROR("A0400", "用户请求参数错误"),
    /**
     * 用户请求参数错误时的错误码 和 错误信息
     */
    SAVEFILEFAILE("S4516", "文件保存失败，请稍后重试"),
    /**
     * 参数格式错误
     */
    PARAMETERFORMATILLEGALITY("c6000", "用户提供的参数格式"),
    /**
     * 记录已存在
     */
    RECORDEEXISTED("A0201", "记录已存在"),
    /**
     * 记录已存在
     */
    RECORDENOTEXISTED("A0201", "记录不存在"),
    /**
     * 用户账户不存在
     */
    USEREXISTED("A0201", "用户账户已存在"),
    /**
     * 用户账户不存在
     */
    USERNOTEXISTED("A0201", "用户账户不存在"),
    /**
     * 订单不存在
     */
    ORDERNOTEXISTED("A0201", "订单不存在"),
    /**
     * 地址信息不存在
     */
    ADDRESSNOTEXISTED("A0201", "地址信息不存在"),
    /**
     * 评论不存在
     */
    COMMENTNOTEXISTED("A0201", "评论不存在"),
    /**
     * 优惠券不存在
     */
    CouponNotExisted("A0201", "优惠券不存在"),
    /**
     * 物品不足
     */
    COMMODITYNOTENOUGH("A0201", "物品不足"),
    /**
     * 用户账户不存在
     */
    CustomerServiceStaffNOTEXISTED("A0201", "该客服人员不存在"),
    /**
     * 商品缺货
     */
    StockOut("A0201", "商品缺货"),
    /**
     * 优惠券已失效
     */
    CouponInvalidated("A0201", "优惠券已失效"),
    /**
     * 用户积分不足
     */
    CreditNotEnough("A0201", "用户积分不足"),
    /**
     * 用户已签到
     */
    CHECKEDIN("A0201", "您已签到过了，不能重复签到哈"),
    /**
     * 积分中心签到失败
     */
    CREDITCHECKINFAIL("A0201", "积分中心签到失败"),
    /**
     * 反馈不存在
     */
    FEEDBACKSNOTEXISTED("A0201", "反馈不存在"),
    /**
     * 商品邮费记录不存在
     */
    POSTCHARGENOTEXISTED("A0201", "商品邮费记录不存在"),
    /**
     * 该店铺不存在
     */
    STORENOTEXISTED("A0201", "该店铺不存在"),
    /**
     * 商品不存在
     */
    COMMODITYNOTEXISTED("A0201", "商品不存在"),
    /**
     * 用户账户不存在
     */
    CAPTCHASENDED("S0246", "验证码已发送"),
    /**
     * 验证吗超时
     */
    CAPTCHATIMEOUT("C0203", "验证吗超时"),
    /**
     * 验证吗错误
     */
    CAPTCHAERROR("C0203", "验证吗错误"),
    /**
     * 用户账号或密码错误，
     */
    PHONEAUTHENTICATERROR("A0220", "用户手机号或验证码错误"),
    /**
     * redis 缓存报错
     */
    REDISSAVEERROR("C0130", "缓存服务出错"),
    /**
     * 服务器端异常，邮件发送错误
     */
    SMSSERVERDIDEERROR("s5001", "服务器端异常，邮件发送错误"),
    /**
     * 服务器端异常，邮件发送错误
     */
    SMSCLIENTSIDEERROR("s5001", "服务器端异常，邮件发送错误"),
    /**
     * 用户账号或密码错误，
     */
    PASSWORDAUTHENTICATERROR("A0220", "用户账号或密码错误");
    /**
     * 错误码
     */
    private String errorCode;
    /**
     * 错误信息
     */
    private String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    ResponseStatusEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
