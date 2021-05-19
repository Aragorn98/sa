package com.example.departmentCRUD.web;


import com.example.departmentCRUD.Department;
import org.springframework.web.bind.annotation.*;

import javax.naming.NameNotFoundException;
import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    ArrayList<Department> departments = new ArrayList<>();

    public DepartmentController() {
        for (int i = 0; i < 10; i++) {
            Department department = new Department();
            department.setId(i);
            department.setName("Information Systems" + i);
            departments.add(department);
        }
    }

    @GetMapping
    public Iterable<Department> getAllDepartments() {
        return departments;
    }

    @GetMapping("/find")
    public Iterable<Department> findDepartments(@RequestParam String searchText) {
        ArrayList<Department> filteredDepartments = new ArrayList<>();
        for (Department dep: departments) {
            if (dep.getName().contains(searchText))
                filteredDepartments.add(dep);
        }
        return filteredDepartments;
    }

    @GetMapping("/departmentID/{id}")
    public Department getDepartmentById(@PathVariable("id") int id) throws NameNotFoundException {

        Department department = new Department();
        switch (id) {
            case 0:
                department.setId(id);
                department.setName("Information Systems0");
                break;
            case 1:
                department.setId(id);
                department.setName("Information Systems1");
                break;
            case 2:
                department.setId(id);
                department.setName("Information Systems2");
                break;
            case 3:
                department.setId(id);
                department.setName("Information Systems3");
                break;
            case 4:
                department.setId(id);
                department.setName("Information Systems4");
                break;
            case 5:
                department.setId(id);
                department.setName("Information Systems5");
                break;
            case 6:
                department.setId(id);
                department.setName("Information Systems6");
                break;
            case 7:
                department.setId(id);
                department.setName("Information Systems7");
                break;
            case 8:
                department.setId(id);
                department.setName("Information Systems8");
                break;
        }

        return department;
    }


    @PostMapping("/department/add")
    public Department saveDepartment(@RequestBody @Valid Department department) {
        departments.add(department);
        return department;
    }

    @PutMapping("/department/update/{id}")
    public void updateDepartment(@PathVariable int id, @RequestBody Department department) {
        for (Department dep: departments) {
            if (dep.getId() == id) {
                dep.setId(id);
                dep.setName(department.getName());
            }
        }
    }

    @DeleteMapping("/department/delete/{id}")
    public void deleteDepartment(@PathVariable int id) {
        departments.removeIf(dep -> dep.getId() == id);
    }

}