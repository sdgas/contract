package org.sdgas.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 120378 on 2015/8/19.
 */
@Entity
public class Payment {

    /**
     * 标识列
     */
    private int id;

    /**
     * 合同编号
     */
    private Contract contract;

    /**
     * 付款时间
     */
    private String paymentDate;

    /**
     * 付款金额
     */
    private double payMoney;

    /**
     * 备注
     * @return
     */
    private String remark;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "contractId")
    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Column(precision = 12, scale = 2, nullable = false)
    public double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(double payMoney) {
        this.payMoney = payMoney;
    }

    @Column(length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
