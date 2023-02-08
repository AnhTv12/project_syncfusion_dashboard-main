package com.sergio.auth.backend.resources.controller;

import com.sergio.auth.backend.resources.dto.EmployeesDto;
import com.sergio.auth.backend.resources.model.Employees;
import com.sergio.auth.backend.resources.service.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
@Transactional
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<EmployeesDto>> listEmployee(){
        List<Employees> employees = employeeService.listEmployees();
        List<EmployeesDto> employeesDtoES = employees.stream().map(employeeService::dtoMapper).toList();
        return ResponseEntity.ok().body(employeesDtoES);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeesDto> findEmployee(@PathVariable("employeeId") int id){
        Employees employees = employeeService.findEmployee(id);
        return ResponseEntity.ok().body(employeeService.dtoMapper(employees));
    }

    @PostMapping("/add")
    public ResponseEntity<EmployeesDto> addEmployee(@RequestBody EmployeesDto employees){
        employeeService.addEmployee(employeeService.EntityMapper(employees));
        return ResponseEntity
                        .ok()
                        .body(employees);
    }

    @PostMapping("/edit")
    public ResponseEntity<EmployeesDto> editEmployee(@RequestBody EmployeesDto employeesDto){
        employeeService.editEmployee(employeeService.EntityMapper(employeesDto));
        return ResponseEntity
                .accepted()
                .body(employeesDto);
    }

    @GetMapping("/delete/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("employeeId") int id){
        return ResponseEntity
                .ok()
                .body(employeeService.deleteEmployee(id));
    }

    @GetMapping("/delete/all")
    public ResponseEntity<String> deleteAllEmployee(){
        return ResponseEntity
                .ok()
                .body(employeeService.deleteAllEmployees());
    }

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
