package com.ai.springaidemo.tools;

import com.ai.springaidemo.entities.Employee;
import com.ai.springaidemo.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeTool {
    private static final Logger log = LoggerFactory.getLogger(EmployeeTool.class);
    private final EmployeeService employeeService;

    public EmployeeTool(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Tool(description = "Lấy thông tin chi tiết về nhân viên có mã số nhân viên cụ thể tại công ty Phuc")
    public Employee getEmployee(String empId) {
        log.info("Getting employee: {}", empId);
        Employee employee = employeeService.getEmployee(empId);
        log.info("Employee: {}", employee);
        return employee;
    }

    @Tool(description = "Tìm kiếm nhân viên của công ty Phuc đang nghỉ phép vào một ngày cụ thể theo định dạng YYYY-MM-DD.")
    public List<Employee> findEmployeesOnLeave(LocalDate date) {
        log.info("Finding employees on leave for date: {}", date);
        List<Employee> employeesOnLeave = employeeService.findEmployeesOnLeave(date);
        log.info("Employees on leave: {} on date {}", employeesOnLeave, date);
        return employeesOnLeave;
    }

    @Tool(description = "Đăng ký nghỉ phép cho mã số nhân viên cụ thể của công ty Phuc và ngày tháng theo định dạng YYYY-MM-DD.")
    public void applyLeave(String empId, LocalDate date) {
        log.info("Applying leave for employee: {} on date: {}", empId, date);
        employeeService.applyLeave(empId, date);
    }
}