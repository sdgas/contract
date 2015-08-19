package org.sdgas.service.impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.Contract;
import org.sdgas.model.Payment;
import org.sdgas.service.ContractService;
import org.sdgas.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 120378 on 2015/8/19.
 */
@Service
@Transactional
public class PaymentServiceImpl extends DaoSupport<Payment> implements PaymentService {

    private ContractService contractService;

    @Override
    public Payment findByContractId(String contractId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("contractId", contractService.find(Contract.class, contractId));
        return (Payment) this.findSpecialObject(Contract.class, params);
    }

    @Override
    public Payment saveOrUpdatePay(String contract, String paymentDate, String paymentMoney) {
        String[] tmp = paymentMoney.split(",");
        double money = 0.0d;
        for (String str : tmp) {
            money += Double.valueOf(str);
        }

        Payment payment = this.findByContractId(contract);
        if (payment == null) {
            payment = new Payment();
            payment.setContract(contractService.find(Contract.class, contract));
            payment.setPaymentDate(paymentDate);
            payment.setPayMoney(money);
            this.save(payment);
        } else {
            payment.setPaymentDate(paymentDate);
            payment.setPayMoney(money);
            this.update(payment);
        }
        return payment;
    }

    @Resource(name = "contractServiceImpl")
    public void setContractService(ContractService contractService) {
        this.contractService = contractService;
    }
}
