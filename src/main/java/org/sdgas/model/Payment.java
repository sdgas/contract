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
    private String payMoney;

    /**
     * 合同剩下金额
     */
    private double leftMoney;

    /**
     * 备注
     *
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
    @JoinColumn(name = "contractId", unique = true)
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

    @Column(nullable = false)
    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    @Column(length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(precision = 12, scale = 2, nullable = false)
    public double getLeftMoney() {
        return leftMoney;
    }

    public void setLeftMoney(double leftMoney) {
        this.leftMoney = leftMoney;
    }
}
