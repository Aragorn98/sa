package com.example.Timetable1.repository;


import com.example.Timetable1.Department;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;

@Service
@Slf4j
public class DepartmentRepository {
    public ArrayList<Department> findDepartments(String searchText) {
        try {

            RestTemplate rest = new RestTemplate();
            String url = "http://localhost:8084/department/find?searchText=" + searchText;
            return rest.exchange(url,
                    HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<Department>>() {
                    })
                    .getBody();
        } catch (Exception e) {
            System.err.println("Exception in TacoCloudClient: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Department getDepartmentById(long id) {
        try {
            RestTemplate rest = new RestTemplate();
            return rest.getForObject("http://localhost:8084/department/departmentID/{id}",
                    Department.class, id);
        } catch (Exception e) {
            System.err.println("Exception in TacoCloudClient: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Department> getAllDepartments() {
        try {
            RestTemplate rest = new RestTemplate();
            return rest.exchange("http://localhost:8084/department",
                    HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<Department>>() {
                    })
                    .getBody();
        } catch (Exception e) {
            System.err.println("Exception in TacoCloudClient: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Department> getAllDepartmentsBySorting(String orderBy, String direction, String page, String size) {
        try {
            RestTemplate rest = new RestTemplate();
            String url = "http://localhost:8084/department/ps?orderBy=" + orderBy + "&direction=" + direction;


            return rest.exchange(url+ "&page="+page+"&size="+size,
                    HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<Department>>() {
                    })
                    .getBody();
        } catch (Exception e) {
            System.err.println("Exception in TacoCloudClient: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void updateDepartment(Department department) {
        try {
            RestTemplate rest = new RestTemplate();
            rest.put("http://localhost:8084/department/department/update/{id}",
                    department, department.getId());
        } catch (Exception e) {
            System.err.println("Exception in TacoCloudClient: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteDepartment(long id) {
        try {
            RestTemplate rest = new RestTemplate();
            rest.delete("http://localhost:8084/department/department/delete/{id}",
                    id);
        } catch (Exception e) {
            System.err.println("Exception in TacoCloudClient: " + e.getMessage());
            e.printStackTrace();
        }
    }

}