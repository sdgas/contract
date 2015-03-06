package org.sdgas.service.impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.ContractType;
import org.sdgas.service.ContractTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 120378 on 2015-03-06.
 */
@Service
@Transactional
public class ContractTypeServiceImpl extends DaoSupport<ContractType> implements ContractTypeService {
    @Override
    public List<ContractType> findAll() {
        return this.find(ContractType.class);
    }

    @Override
    public ContractType findTypeById(int contractTypeId) {
        return this.find(ContractType.class, contractTypeId);
    }
}
