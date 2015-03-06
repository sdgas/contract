package org.sdgas.service.impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.Contract;
import org.sdgas.service.ContractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 120378 on 2015-03-06.
 */
@Service
@Transactional
public class ContractServiceImpl extends DaoSupport<Contract> implements ContractService {

    @Override
    public List<Contract> findAll() {
        return this.find(Contract.class);
    }

    @Override
    public Contract findContractById(String contractId) {
        return this.find(Contract.class, contractId);
    }
}
