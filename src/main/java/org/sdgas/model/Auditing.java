package org.sdgas.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 120378 on 2015/8/24.
 */
@Entity
public class Auditing {

    /**
     * 标识列
     */
    private int id;

    /**
     * 处理时间
     */
    private Date audDate;

    /**
     * 审核合同
     */
    private Contract contract;

    /**
     * 审核信息
     */
    private String state;

    /**
     * 备注
     */
    private String remark;


    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "contractId")
    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    @Column(nullable = false, length = 100)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getAudDate() {
        return audDate;
    }

    public void setAudDate(Date audDate) {
        this.audDate = audDate;
    }
}
