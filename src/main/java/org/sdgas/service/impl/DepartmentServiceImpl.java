package org.sdgas.service.impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.Department;
import org.sdgas.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 120378 on 2015-03-05.
 */
@Service
@Transactional
public class DepartmentServiceImpl extends DaoSupport<Department> implements DepartmentService {


    @Override
    public List<Department> findAllDepartment() {
        return this.find(Department.class);
    }

    @Override
    public Department findDepartmentById(int departmentId) {
        return this.find(Department.class, departmentId);
    }
}
