package org.sdgas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by 120378 on 2015-03-09.
 */

@Entity
public class ContractName {

    /**
     * 标识列
     */
    private int id;

    /**
     * 合同名称
     */
    private String contractName;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(length = 10, nullable = false)
    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }
}
