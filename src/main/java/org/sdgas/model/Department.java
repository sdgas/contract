package org.sdgas.model;

import javax.persistence.*;

/**
 * 部门
 * Created by 120378 on 2014/7/11.
 */

@Entity
public class Department {

    /**
     * 部门编号
     */
    private int departmentId;

    /**
     * 部门名称
     */
    private String departmentName;

    @Id
    @GeneratedValue
    @Column(length = 4)
    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Column(length = 15, nullable = false, unique = true)
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
