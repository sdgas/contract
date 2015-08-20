package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.Payment;

import java.util.List;

/**
 * Created by 120378 on 2015/8/19.
 */
public interface PaymentService extends DAO {

    /**
     * 根据合同编号查找付款信息
     *
     * @param contractId 合同编号
     * @return 付款信息
     */
    public Payment findByContractId(String contractId);

    /**
     * 保存或更新付款信息
     *
     * @param contract     合同编号
     * @param paymentDate  付款日期
     * @param paymentMoney 付款金额
     * @param remark       备注
     * @return
     */
    public Payment saveOrUpdatePay(String contract, String paymentDate, String paymentMoney, String remark);
}
