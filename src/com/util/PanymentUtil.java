package com.util;

public class PanymentUtil {

    /**
     *
     * @param p0_Cmd
     * @param p1_MerId
     * @param p2_Order
     * @param p3_Amt
     * @param p4_Cur
     * @param p5_Pid
     * @param p6_Pcat
     * @param p7_Pdesc
     * @param p8_Url
     * @param p9_SAF
     * @param pa_MP
     * @param pd_FrpId
     * @param pr_NeedResponse
     * @param keyValue
     * @return
     */
    public static String buildHmac(String p0_Cmd,String p1_MerId,
                                   String p2_Order, String p3_Amt, String p4_Cur,String p5_Pid, String p6_Pcat,
                                   String p7_Pdesc,String p8_Url, String p9_SAF,String pa_MP,String pd_FrpId,
                                   String pr_NeedResponse,String keyValue) {
        //将协议内容装载入StringBuffer
        StringBuffer sValue = new StringBuffer();
        //加密顺序一定要按着加密规范顺序加密 顺序不一样加密码不同  变量不能不能null只能为空串
        // 业务类型
        sValue.append(p0_Cmd);
        // 商户编号
        sValue.append(p1_MerId);
        // 商户订单号
        sValue.append(p2_Order);
        // 支付金额
        sValue.append(p3_Amt);
        // 交易币种
        sValue.append(p4_Cur);
        // 商品名称
        sValue.append(p5_Pid);
        // 商品种类
        sValue.append(p6_Pcat);
        // 商品描述
        sValue.append(p7_Pdesc);
        // 商户接收支付成功数据的地址
        sValue.append(p8_Url);
        // 送货地址
        sValue.append(p9_SAF);
        // 商户扩展信息
        sValue.append(pa_MP);
        // 银行编码
        sValue.append(pd_FrpId);
        // 应答机制
        sValue.append(pr_NeedResponse);

        //利用加密算法工具类将其转换为键值对的形式
        String sNewString = DigestUtil.hmacSign(sValue.toString(), keyValue);
        return sNewString;
    }

    /**
     * 返回校验hmac方法
     * @Explain:检验协议是否匹配
     * @param hmac 支付网关发来的加密验证码
     * @param p1_MerId 商户编号
     * @param r0_Cmd 业务类型
     * @param r1_Code 支付结果
     * @param r2_TrxId 易宝支付交易流水号
     * @param r3_Amt 支付金额
     * @param r4_Cur 交易币种
     * @param r5_Pid 商品名称
     * @param r6_Order 商户订单号
     * @param r7_Uid 易宝支付会员ID
     * @param r8_MP 商户扩展信息
     * @param r9_BType 交易结果返回类型
     * @param keyValue 密钥
     * @return
     */
    public static boolean verifyCallback(String hmac, String p1_MerId,
                                         String r0_Cmd, String r1_Code, String r2_TrxId, String r3_Amt,
                                         String r4_Cur, String r5_Pid, String r6_Order, String r7_Uid,
                                         String r8_MP, String r9_BType, String keyValue) {
        StringBuffer sValue = new StringBuffer();
        // 商户编号
        sValue.append(p1_MerId);
        // 业务类型
        sValue.append(r0_Cmd);
        // 支付结果
        sValue.append(r1_Code);
        // 易宝支付交易流水号
        sValue.append(r2_TrxId);
        // 支付金额
        sValue.append(r3_Amt);
        // 交易币种
        sValue.append(r4_Cur);
        // 商品名称
        sValue.append(r5_Pid);
        // 商户订单号
        sValue.append(r6_Order);
        // 易宝支付会员ID
        sValue.append(r7_Uid);
        // 商户扩展信息
        sValue.append(r8_MP);
        // 交易结果返回类型
        sValue.append(r9_BType);
        String sNewString = DigestUtil.hmacSign(sValue.toString(), keyValue);

        if (hmac.equals(sNewString)) {
            return true;
        }
        return false;
    }

}
