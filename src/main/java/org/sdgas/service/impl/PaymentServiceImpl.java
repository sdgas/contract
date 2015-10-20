package org.sdgas.service.impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.Contract;
import org.sdgas.model.Payment;
import org.sdgas.service.ContractService;
import org.sdgas.service.PaymentService;
import org.sdgas.util.ChangeTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
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
        params.put("contract", contractService.findContractById(contractId));
        return (Payment) this.findSpecialObject(Payment.class, params);
    }

    @Override
    public Payment saveOrUpdatePay(String contract, String paymentDate, String paymentMoney, String remark) {
        String tep[] = paymentMoney.split(",");
        double money = 0.0d;
        for (String str : tep) {
            if (!str.trim().isEmpty()) {
                money += Double.valueOf(str);
            }
        }
        int k = 0;
        for (; k < tep.length; k++) {
            if ("".equals(tep[k].trim()))
                break;
        }
        String pm = "";
        for (int j = k - 1; j > -1; j--)
            pm = tep[j] + "," + pm;
        pm = pm.substring(0, pm.length() - 1);


        tep = paymentDate.split(",");
        String pd = "";
        int i = 0;
        for (; i < tep.length; i++) {
            if ("".equals(tep[i].trim()))
                break;
        }
        for (int j = i - 1; j > -1; j--)
            pd = tep[j] + "," + pd;
        pd = pd.substring(0, pd.length() - 1);

        Payment payment = this.findByContractId(contract);
        if (payment == null) {
            payment = new Payment();
            payment.setContract(contractService.find(Contract.class, contract));
            payment.setPaymentDate(pd);
            payment.setPayMoney(paymentMoney);
            payment.setRemark(remark);
            payment.setLeftMoney(contractService.findContractById(contract).getContractMoney() - money);
            this.save(payment);
        } else {
            if (payment.getPaymentDate() != null)
                payment.setPaymentDate(payment.getPaymentDate() + "," + pd);
            else
                payment.setPaymentDate(payment.getPaymentDate() + pd);

            if (payment.getPayMoney() != null)
                payment.setPayMoney(payment.getPayMoney() + "," + pm);
            else
                payment.setPayMoney(payment.getPayMoney() + pm);
            payment.setRemark(payment.getRemark() + "<br/>" + remark);
            payment.setLeftMoney(payment.getLeftMoney() - money);
            this.update(payment);
        }
        return payment;
    }

    @Resource(name = "contractServiceImpl")
    public void setContractService(ContractService contractService) {
        this.contractService = contractService;
    }
}
