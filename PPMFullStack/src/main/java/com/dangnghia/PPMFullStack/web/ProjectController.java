package com.dangnghia.PPMFullStack.web;

import com.dangnghia.PPMFullStack.domain.Project;
import com.dangnghia.PPMFullStack.exceptions.ProjectIdException;
import com.dangnghia.PPMFullStack.services.MapValidationErrorService;
import com.dangnghia.PPMFullStack.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @RequestMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap!=null) return errorMap;

        Project project1 = projectService.saveAndUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
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

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId){
        projectService.deleteProjectByIdentifier(projectId);
        return new ResponseEntity<String>("Project with ID'" + projectId +"'was deleted", HttpStatus.OK);
    }

    @RequestMapping("/edit/{id}")
    public ResponseEntity<Project> editProject(@PathVariable (name="id") Long id, @RequestBody Project project){
        Project project1 = projectService.getProjectById(id);
        project1.setProjectName(project.getProjectName());
        project1.setProjectIdentifier(project.getProjectIdentifier());
        project1.setDescription(project.getDescription());
        Project projectUpdate = projectService.saveAndUpdateProject(project1);
        return new ResponseEntity<Project>(projectUpdate,HttpStatus.OK);
    }
}
