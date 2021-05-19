package com.example.Timetable1.web;

import javax.validation.Valid;
import com.example.Timetable1.Department;
import com.example.Timetable1.Search;
import com.example.Timetable1.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
@Controller
@RequestMapping("/departmentList")
public class DepartmentListController {
    HashMap<String ,String> direction = new HashMap<>();
    String orderBy;
    int page=0;
    int size=100;
    public DepartmentListController() {
        direction.put("id","ASC");
        direction.put("name","ASC");
    }

    @Autowired
    private DepartmentRepository departmentRepository = new DepartmentRepository();
    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("edit", new Department());
        return "DepartmentList_admin";
    }

    @GetMapping("/current/orderList")
    public String orderList(Model model) {
        model.addAttribute("edit", new Department());
        model.addAttribute("edits", departmentRepository.getAllDepartments());
        return "DepartmentList_admin";
    }

    @PostMapping("/find")
    public String findDepartments(Model model, Search departmentSearch) {

        System.out.println(departmentSearch.getName());
        model.addAttribute("edit", new Department());
        model.addAttribute("edits", departmentRepository.findDepartments(departmentSearch.getName()));
        return "DepartmentList_admin";
    }

    @GetMapping(path="/")
    public @ResponseBody Iterable<Department> getAllDepartments() {
        return departmentRepository.getAllDepartments();
    }

    @GetMapping("/orderBy/{orderBy}")
    public String getAllDepartments(@PathVariable("orderBy") String orderBy1, Model model) {
        orderBy = orderBy1.substring(1,orderBy1.length()-1);
        model.addAttribute("edit", new Department());
        model.addAttribute("edits", departmentRepository.getAllDepartmentsBySorting(orderBy, direction.get(orderBy),
                Integer.toString(page),Integer.toString(size)));
        if(direction.get(orderBy).equals("ASC")){
            direction.replace(orderBy, "DESC");
        } else{
            direction.replace(orderBy, "ASC");
        }
        return "DepartmentList_admin";
    }

    @GetMapping("/page/{page}")
    public String getAllDepartmentsForPage(@PathVariable("page") String page1, Model model) {
        if (page1.equals("{previous}") && page != 0) page -= 1;
        else if (page1.equals("{next}")) page++;
        if (orderBy != null) {

            String direction1;
            if (direction.get(orderBy).equals("ASC")) {
                direction1 = "DESC";
            } else {
                direction1 = "ASC";
            }

            model.addAttribute("edit", new Department());
            model.addAttribute("edits", departmentRepository.getAllDepartmentsBySorting(orderBy, direction1,
                    Integer.toString(page), Integer.toString(size)));
            return "DepartmentList_admin";
        }
        model.addAttribute("edit", new Department());
        ArrayList<Department> list = new ArrayList<>(departmentRepository.getAllDepartments());
        int start,end;
        if(page*size >= list.size()) start = list.size();
        else start = page*size;
        if((page+1)*size > list.size()) end = list.size();
        else end = (page+1)*size;
        list = new ArrayList<>(list.subList(start,end));
        model.addAttribute("edits", list);
        return "DepartmentList_admin";
    }

    @GetMapping("/size/{size}")
    public String getAllDepartmentsForSize(@PathVariable("size") String size1, Model model) {
        if(size1.equals("{five}")) size=5;
        else if(size1.equals("{ten}")) size = 10;
        else if(size1.equals("{twenty}")) size = 20;
        if (orderBy != null) {
            String direction1;
            if(direction.get(orderBy).equals("ASC")){
                direction1 = "DESC";
            }else{
                direction1 = "ASC";
            }

            model.addAttribute("edit", new Department());
            model.addAttribute("edits", departmentRepository.getAllDepartmentsBySorting(orderBy, direction1,
                    Integer.toString(page), Integer.toString(size)));
            return "DepartmentList_admin";
        }
        model.addAttribute("edit", new Department());
        ArrayList<Department> list = new ArrayList<>(departmentRepository.getAllDepartments());
        int s = size;
        if(size>list.size()) s = list.size();
        list = new ArrayList<>(list.subList(0,s));
        model.addAttribute("edits", list);
        return "DepartmentList_admin";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Department department = departmentRepository.getDepartmentById(id);
        model.addAttribute("order", department);
        return "editDepartment";
    }

    @PostMapping("/update/{id}")
    public String updateOrder(@PathVariable("id") int id, @Valid Department department,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            department.setId(id);
            return "editDepartment";
        }
        departmentRepository.updateDepartment(department);
        model.addAttribute("order", departmentRepository.getAllDepartments());
        return "redirect:/departmentList/current/orderList";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") int id, Model model) {
        departmentRepository.deleteDepartment(id);
        model.addAttribute("order", departmentRepository.getAllDepartments());
        return "redirect:/departmentList/current/orderList";
    }
}
