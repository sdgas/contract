package org.sdgas.service.impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.Contract;
import org.sdgas.model.ContractState;
import org.sdgas.service.ContractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.Date;
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

    @Override
    public List<Contract> findOverDate() {
        Query query = em.createQuery("from Contract c where to_days(c.contractEndDate)-to_days(now()) < 7 and c.state>?1");
        query.setParameter(1, ContractState.CONPLETED);
        return query.getResultList();
    }

    @Override
    public List<Contract> findOverPerformanceBond() {
        Query query = em.createQuery("from Contract c where to_days(c.duePerformanceBond)-to_days(now()) < 7 and c.state>?1");
        query.setParameter(1, ContractState.CONPLETED);
        return query.getResultList();
    }
}
