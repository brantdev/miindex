package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingService;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportingServiceImpl implements ReportingService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingServiceImpl.class);
    private int reportCount=0;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure read(String id){

        LOG.debug("Creating report with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        //If employee doesn't exist return exception
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        ReportingStructure reportingStructure = new ReportingStructure(employee);
        List<Employee> directReports = new ArrayList<>();
        directReports = employee.getDirectReports();

        for (int i = 0; i < directReports.size() ; i++) {
            List<Employee> reports = employee.getDirectReports();
            if (reports != null) {
                reportCount += employee.getDirectReports().size();
            }
        }
        reportingStructure.setNumberOfReports(reportCount);

        return reportingStructure;
    }
}
