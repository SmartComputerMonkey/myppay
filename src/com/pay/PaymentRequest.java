package com.pay;

import com.properties.ConfigInfo;
import com.util.PanymentUtil;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet("/servlet/yeepay/paymentRequest")
public class PaymentRequest extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String orderid = request.getParameter("orderid");//订单号
        String amount = request.getParameter("amount");//支付金额
        String pd_FrpId = request.getParameter("pd_FrpId");//选择支付银行
        String p1_MerId = ConfigInfo.getValue("p1_MerId");
        String keyValue = ConfigInfo.getValue("keyValue");
        String merchantCallbackURL = ConfigInfo.getValue("merchantCallbackURL");
        String messageType = "Buy"; // 请求命令,在线支付固定为Buy
        String currency = "CNY"; // 货币单位
        String productDesc = ""; // 商品描述
        String productCat = ""; // 商品种类
        String productId = ""; // 商品ID
        String addressFlag = "0"; // 需要填写送货信息 0：不需要 1:需要
        String sMctProperties = ""; // 商家扩展信息
        String pr_NeedResponse = "0"; // 应答机制
        String md5hmac = PanymentUtil.buildHmac(messageType, p1_MerId, orderid, amount, currency,
                productId, productCat, productDesc, merchantCallbackURL, addressFlag, sMctProperties,
                pd_FrpId, pr_NeedResponse, keyValue);

        request.setAttribute("messageType", messageType);
        request.setAttribute("merchantID", p1_MerId);
        request.setAttribute("orderId", orderid);
        request.setAttribute("amount", amount);
        request.setAttribute("currency", currency);
        request.setAttribute("productId", productId);
        request.setAttribute("productCat", productCat);
        request.setAttribute("productDesc", productDesc);
        request.setAttribute("merchantCallbackURL", merchantCallbackURL);
        request.setAttribute("addressFlag", addressFlag);
        request.setAttribute("sMctProperties", sMctProperties);
        request.setAttribute("frpId", pd_FrpId);
        request.setAttribute("pr_NeedResponse", pr_NeedResponse);
        request.setAttribute("hmac", md5hmac);

        //跳转到支付链接页面（并且传递相应值）
        request.getRequestDispatcher("/WEB-INF/page/Connection.jsp").forward(request, response);

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doPost(request,response);
    }
}
