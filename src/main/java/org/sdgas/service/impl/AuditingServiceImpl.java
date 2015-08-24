package org.sdgas.service.impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.Auditing;
import org.sdgas.service.AuditingService;
import org.sdgas.service.ContractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 120378 on 2015/8/24.
 */
@Service
@Transactional
public class AuditingServiceImpl extends DaoSupport<Auditing> implements AuditingService {

    private ContractService contractService;

    @Override
    public Auditing findByContractId(String contractId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("contract", contractService.findContractById(contractId));
        return (Auditing) this.findSpecialObject(Auditing.class, params);
    }

    @Resource(name = "contractServiceImpl")
    public void setContractService(ContractService contractService) {
        this.contractService = contractService;
    }
}
