package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.data.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompensationServiceImpl implements CompensationService{

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public Compensation create(Compensation compensation){
        LOG.debug("Creating compensation [{}]", compensation);

        //Employee employee = employeeRepository.findByEmployeeId(compensation.getEmployee().getEmployeeId());
        Employee employee = employeeService.read(compensation.getEmployee().getEmployeeId());

        compensation.setEmployee(employee);
        compensationRepository.insert(compensation);

        return compensation;
    }

    @Override
    public Compensation read(String employeeId){
        LOG.debug("Creating compensation with id [{}]", employeeId);

        //Employee employee = employeeService.read(employeeId);
        Employee employee = employeeService.read(employeeId);
        Compensation compensation = compensationRepository.findByEmployee(employee);

        if(employee == null){
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }
        return compensation;
    }

    /*
        This will return all compensation with employees
    */
    @Override
    public List<Compensation> findAll() {
        LOG.debug("Creating compensation for all");

        List<Compensation> list = compensationRepository.findAll();
        return list;
    }

    /*
    This is creating a duplicate entry so not completely working
    */
    @Override
    public Compensation update(String employeeId, Compensation compensation) {
        LOG.debug("Updating employee [{}]", compensation);

        Employee employee = employeeService.read(employeeId);
        if(employee == null){
            throw new RuntimeException("Employee doesn't exist");
        }
        Compensation existingComp = compensationRepository.findByEmployee(employee);
        if(existingComp == null){
            throw new RuntimeException("Compensation doesn't exist");
        }
        existingComp.setSalary(compensation.getSalary());

        return compensationRepository.save(existingComp);
    }
}
