package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.ContractName;

import java.util.List;

/**
 * Created by 120378 on 2015-03-09.
 */
public interface ContractNameService extends DAO {

    /**
     * 提取全部合同类别
     *
     * @return 合同类别
     */
    public List<ContractName> findAll();

    /**
     * 根据类别编号查询合同类别
     *
     * @param contractNameId 类别编号
     * @return 合同类别
     */
    public ContractName findById(int contractNameId);
}
