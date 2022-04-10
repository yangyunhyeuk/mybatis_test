package com.example.demomybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Company> getAll() {
        List<Company> companyList = companyMapper.getAll();

        if (companyList != null && companyList.size() > 0) {
            for (Company company : companyList) {
                company.setEmployeeList(employeeMapper.getByCompanyId(company.getId()));
            }
        }

        return companyList;
    }

    // 에러 발생 시 rollback, 정상 처리 시 commit
    @Transactional(rollbackFor = Exception.class)
    public Company add(Company company) {
        companyMapper.insert(company);
        // add company into legacy system => rollback !!!
        if (true) {
            throw new RuntimeException(("Legacy Exception"));
        }
        return company;
    }


}
