package com.example.hrms.controller;
import com.example.hrms.exception.ResourceNotFoundException;
import com.example.hrms.model.Department;
import org.springframework.web.bind.annotation.*;
import com.example.hrms.repository.DepartmentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/hrms/departments")
public class DepartmentController {

    @Autowired

    private DepartmentRepository departmentRepository;

    @GetMapping("/list")
    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }
    
    @PostMapping("/create")
    public Department createDepartment(@RequestBody Department department){
        return departmentRepository.save(department);
    }

    //  @GetMapping("{id}")
    //  public ResponseEntity<Department> getDepartmentById(@PathVariable int id){

    //      Department department = departmentRepository.findById(id)
    //      .orElseThrow(() -> ResourceNotFoundException("Department doesn't exist with id :" + id));
    //     

    //      return ResponseEntity.ok(department);
    //  }

    @GetMapping("getDepartment/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable  int id){
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not exist with id:" + id));
        return ResponseEntity.ok(department);
    }

    @PutMapping("updateDepartment/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable int id,@RequestBody Department departmentDetails) {
        Department updateDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not exist with id: " + id));

                updateDepartment.setName(departmentDetails.getName());
                updateDepartment.setDescription(departmentDetails.getDescription());
                

                departmentRepository.save(updateDepartment);

        return ResponseEntity.ok(updateDepartment);
    }

    @DeleteMapping("deleteDepartment/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable int id){

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not exist with id: " + id));

        departmentRepository.delete(department);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
