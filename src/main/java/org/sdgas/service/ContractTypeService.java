package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.ContractType;

import java.util.List;

/**
 * Created by 120378 on 2015-03-06.
 */
public interface ContractTypeService extends DAO {

    /**
     * 提取全部合同类别
     *
     * @return 合同类别
     */
    public List<ContractType> findAll();

    /**
     * 根据类别编号查询合同类别
     *
     * @param contractTypeId 类别编号
     * @return 合同类别
     */
    public ContractType findTypeById(int contractTypeId);
}
