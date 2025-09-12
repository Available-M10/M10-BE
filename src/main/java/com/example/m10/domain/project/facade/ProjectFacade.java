package com.example.m10.domain.project.facade;

import com.example.m10.domain.project.domain.Project;
import com.example.m10.domain.project.domain.repository.ProjectRepository;
import com.example.m10.domain.project.exception.ProjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectFacade {
    private final ProjectRepository projectRepository;

    public Project findByProject(Long id){
        return projectRepository.findById(id)
                .orElseThrow(ProjectNotFoundException::new);
    }

}
