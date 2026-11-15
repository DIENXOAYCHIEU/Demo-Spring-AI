package com.ai.springaidemo.services;

import com.ai.springaidemo.exception.ResourceNotFoundException;
import com.ai.springaidemo.entities.Employee;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmployeeService {

    private final Map<String, Employee> employeeTable = new ConcurrentHashMap<>();
    private final Map<LocalDate, List<String>> employeeLeavesTable = new ConcurrentHashMap<>();

    @PostConstruct
    void init() {
        employeeTable.put("emp1001", new Employee("emp1001", "John Doe", "john.doe@example.com"));
        employeeTable.put("emp1002", new Employee("emp1002", "Siva", "siva@example.com"));
        employeeTable.put("emp1003", new Employee("emp1003", "James", "james@example.com"));
    }

    public Employee getEmployee(String empId) {
        if (empId == null) {
            return null;
        }
        return employeeTable.get(empId.trim().toLowerCase());
    }

    public List<Employee> findEmployeesOnLeave(LocalDate date) {
        if (date == null) {
            return List.of();
        }
        List<String> empIds = employeeLeavesTable.get(date);
        return empIds == null ? List.of() : getEmployees(empIds);
    }

    public List<Employee> getEmployees(List<String> empIds) {
        if (empIds == null || empIds.isEmpty()) {
            return List.of();
        }
        return empIds.stream()
                .map(this::getEmployee)
                .filter(Objects::nonNull)
                .toList();
    }

    public void applyLeave(String empId, LocalDate date) {
        if (empId == null || !employeeTable.containsKey(empId.trim().toLowerCase())) {
            throw new ResourceNotFoundException("Không tìm thấy nhân viên với mã: " + empId);
        }
        String normalizedId = empId.trim().toLowerCase();
        List<String> leavesOnDate = employeeLeavesTable.computeIfAbsent(date, k -> new ArrayList<>());
        
        if (!leavesOnDate.contains(normalizedId)) {
            leavesOnDate.add(normalizedId);
        }
    }

	public List<Employee> getAllEmployeesOnLeave() {
	    List<String> allEmpIds = employeeLeavesTable.values().stream()
	            .flatMap(List::stream)
	            .distinct()
	            .toList();
	            
	    return getEmployees(allEmpIds);
	}

}
