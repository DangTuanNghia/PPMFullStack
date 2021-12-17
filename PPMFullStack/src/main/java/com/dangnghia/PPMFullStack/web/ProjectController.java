package com.dangnghia.PPMFullStack.web;

import com.dangnghia.PPMFullStack.domain.Project;
import com.dangnghia.PPMFullStack.exceptions.ProjectIdException;

import com.dangnghia.PPMFullStack.exceptions.ProjectNameException;
import com.dangnghia.PPMFullStack.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("/createProject")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project){
        projectService.getProjectByName(project.getProjectName())
                .<ProjectNameException>map(u ->{
                    throw new ProjectNameException("the " + project.getProjectName() + " is already");
                });
        return new ResponseEntity<Project>(projectService.saveAndUpdateProject(project), HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectByIdentifier(@PathVariable String projectId){
        Project project = projectService.findProjectByIdentifier(projectId);
        return new ResponseEntity<Project>(project,HttpStatus.OK);
    }

    @GetMapping("/allProject")
    public Iterable<Project> getAllProject(){
        return projectService.findAllProjects();
    }

    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId){
        projectService.deleteProjectByIdentifier(projectId);
        return new ResponseEntity<String>("Project with ID'" + projectId +"'was deleted", HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Project> editProject(@PathVariable (name="id") Long id, @Valid @RequestBody Project project){
        projectService.getProjectById(id);
        Project projectUpdate = projectService.saveAndUpdateProject(project);
        return new ResponseEntity<Project>(projectUpdate,HttpStatus.OK);
    }
}
