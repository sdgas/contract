package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.Auditing;

/**
 * Created by 120378 on 2015/8/24.
 */
public interface AuditingService extends DAO {

    /**
     * 根据合同编号查找付款信息
     *
     * @param contractId 合同编号
     * @return 审核信息
     */
    public Auditing findByContractId(String contractId);
}
