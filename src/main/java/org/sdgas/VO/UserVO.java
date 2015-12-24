package org.sdgas.VO;

import org.sdgas.model.Contract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 120378 on 2015-03-05.
 */
public class UserVO extends BaseVO {

    private String userId;
    private String userName;
    private String pwd;
    private String pwd2;
    private String department;
    private String position;
    private String remark;

    private List<Contract> overDate = new ArrayList<Contract>();
    List<Contract> overPerformanceBond = new ArrayList<Contract>();

    public List<Contract> getOverPerformanceBond() {
        return overPerformanceBond;
    }

    public void setOverPerformanceBond(List<Contract> overPerformanceBond) {
        this.overPerformanceBond = overPerformanceBond;
    }

    public List<Contract> getOverDate() {
        return overDate;
    }

    public void setOverDate(List<Contract> overDate) {
        this.overDate = overDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPwd2() {
        return pwd2;
    }

    public void setPwd2(String pwd2) {
        this.pwd2 = pwd2;
    }
}
