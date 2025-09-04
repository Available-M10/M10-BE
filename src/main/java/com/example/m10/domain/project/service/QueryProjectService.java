package com.example.m10.domain.project.service;

import com.example.m10.domain.project.domain.repository.ProjectRepository;
import com.example.m10.domain.project.exception.ProjectNotFoundException;
import com.example.m10.domain.project.presentation.dto.response.ProjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryProjectService {

    private final ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public ProjectResponse getProject(Long projectId) {
        return projectRepository.findById(projectId)
                .map(ProjectResponse::from)
                .orElseThrow(ProjectNotFoundException::new);
    }
}
