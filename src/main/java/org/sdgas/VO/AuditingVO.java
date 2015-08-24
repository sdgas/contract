package org.sdgas.VO;

/**
 * Created by 120378 on 2015/8/24.
 */
public class AuditingVO extends BaseVO {


    private String contract;
    private String state;
    private String remark;

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

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
}
