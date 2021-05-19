//tag::all[]
//tag::allButValidation[]
package com.example.departmentCRUD;

import lombok.Data;

//import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
//@Entity
//@Table(name = "department")
public class Department implements Serializable {

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty(message="department name is required")
    private String name;

}
