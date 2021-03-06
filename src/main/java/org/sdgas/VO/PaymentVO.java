package org.sdgas.VO;

/**
 * Created by 120378 on 2015/8/19.
 */
public class PaymentVO extends BaseVO {

    private String contract;//合同编号
    private String paymentDate;//付款时间
    private String payMoney;//付款金额
    private String remark;//备注
    private String settleAccount;//是否超结算  0否 1是，已审核  2是，未审核

    public String getSettleAccount() {
        return settleAccount;
    }

    public void setSettleAccount(String settleAccount) {
        this.settleAccount = settleAccount;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
