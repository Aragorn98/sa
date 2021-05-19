//tag::all[]
//tag::allButValidation[]
package com.example.Timetable1;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Department implements Serializable {

    private int id;

    @NotEmpty(message="Academic title is required")
    private String name;
}
