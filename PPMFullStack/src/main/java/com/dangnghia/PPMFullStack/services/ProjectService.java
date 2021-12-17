package com.dangnghia.PPMFullStack.services;

import com.dangnghia.PPMFullStack.domain.Project;
import com.dangnghia.PPMFullStack.exceptions.ProjectIdException;
import com.dangnghia.PPMFullStack.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveAndUpdateProject(Project project){
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Project ID "+project.getProjectIdentifier().toUpperCase());
        }
    }

    public Project findProjectByIdentifier(String projectId){
       Project project = projectRepository.findByProjectIdentifier(projectId);
       if (project == null){
           throw new ProjectIdException("Project ID " + projectId + " does not exist");
       }
       return project;
    }

    public List<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId){
        Project project =projectRepository.findByProjectIdentifier(projectId);
        if (project==null){
            throw new ProjectIdException("Can not Project with ID'" +projectId+ "'The Project does not exist");
        }
        projectRepository.delete(project);
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id).get();
    }
    public Optional<Project> getProjectByName(String projectName){
        return projectRepository.findByProjectName(projectName);
    }
}
