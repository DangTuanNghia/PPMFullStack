package com.dangnghia.PPMFullStack.repositories;

import com.dangnghia.PPMFullStack.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {

    Project findByProjectIdentifier(String projectId);

    @Override
    List<Project> findAll();

    Optional<Project> findByProjectName(String projectName);
}
