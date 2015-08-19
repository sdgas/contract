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
    public List<Payment> findByContractId(String contractId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("contractId", contractService.find(Contract.class, contractId));
        return this.findByFields(Contract.class, params);
    }

    @Resource(name = "contractServiceImpl")
    public void setContractService(ContractService contractService) {
        this.contractService = contractService;
    }
}
