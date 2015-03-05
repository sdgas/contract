package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.Department;

import java.util.List;

/**
 * Created by 120378 on 2015-03-05.
 */
public interface DepartmentService extends DAO {


    /**
     * 查找全部部门
     *
     * @return 全部部门
     */
    public List<Department> findAllDepartment();

    /**
     * 根据部门名称查找
     *
     * @param department 部门名称
     * @return 部门
     */
    public Department findDepartmentByName(String department);
}
