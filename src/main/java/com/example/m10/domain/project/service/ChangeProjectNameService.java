package com.example.m10.domain.project.service;

import com.example.m10.domain.project.domain.Project;
import com.example.m10.domain.project.domain.repository.ProjectRepository;
import com.example.m10.domain.project.exception.ProjectNotFoundException;
import com.example.m10.domain.project.presentation.dto.request.ChangeProjectNameRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChangeProjectNameService {

    private final ProjectRepository projectRepository;

    @Transactional
    public void changeProjectName(Long id, ChangeProjectNameRequest request) {
        Project project = projectRepository.findById(id)
                .orElseThrow(ProjectNotFoundException::new);

        project.updateName(request.name());
    }
}
