package com.example.m10.domain.project.service;

import com.example.m10.domain.project.domain.repository.ProjectRepository;
import com.example.m10.domain.project.exception.ProjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ProjectNotFoundException();
        }
        projectRepository.deleteById(id);
    }
}