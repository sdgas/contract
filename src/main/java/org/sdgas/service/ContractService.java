package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.Contract;
import org.sdgas.model.Department;

import java.util.List;

/**
 * Created by 120378 on 2015-03-06.
 */
public interface ContractService extends DAO {

    /**
     * 查找全部合同
     *
     * @return 全部合同
     */
    public List<Contract> findAll();


    /**
     * 根据合同编号（contractId）查找
     *
     * @param contractId 合同编号
     * @return 合同
     */
    public Contract findContractById(String contractId);

    /**
     * 根据部门查找合同
     *
     * @param department 部门
     * @return 合同
     */
    //public List<Contract> findContractByDepartment(Department department);
}
