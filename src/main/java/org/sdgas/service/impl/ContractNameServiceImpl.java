package org.sdgas.service.impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.ContractName;
import org.sdgas.service.ContractNameService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 120378 on 2015-03-09.
 */
@Service
@Transactional
public class ContractNameServiceImpl extends DaoSupport<ContractName> implements ContractNameService {
    @Override
    public List<ContractName> findAll() {
        return this.find(ContractName.class);
    }

    @Override
    public ContractName findById(int contractNameId) {
        return this.find(ContractName.class, contractNameId);
    }
}
