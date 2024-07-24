package com.example.hrms.controller;

import com.example.hrms.exception.ProjectNotFoundException;
import com.example.hrms.model.Employee;
import com.example.hrms.model.Project;
import com.example.hrms.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/hrms/project")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/getAllproject")
    public List<Project> getAllProject(){
        return projectRepository.findAll();
    }

    @PostMapping("/createProject")
    public Project createProject(@RequestBody Project project){
        return projectRepository.save(project);
    }

    @GetMapping("/getProjectById/{id}")
    public ResponseEntity<Project> getProjectbyId(@PathVariable int id){
        Project project = projectRepository.findById(id).orElseThrow(()->new ProjectNotFoundException("Project not exist with id"+id));
        return ResponseEntity.ok(project);
    }
    @PutMapping("/updateProject/{id}")
    public  ResponseEntity<Project> updateProject(@PathVariable int id,@RequestBody Project projectDetail){
        Project updateProject=projectRepository.findById(id).orElseThrow(()->new ProjectNotFoundException("Project Not exist with id: " + id));

        updateProject.setProjectName(projectDetail.getProjectName());

        projectRepository.save(updateProject);
        return ResponseEntity.ok(updateProject);
    }
    @DeleteMapping("/deleteProject/{id}")
    public ResponseEntity<HttpStatus> deleteProject(@PathVariable int id){

        Project project=projectRepository.findById(id).orElseThrow(()->new ProjectNotFoundException("Project not exist with entered id"+id));
        projectRepository.delete(project);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

