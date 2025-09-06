package com.example.m10.domain.project.service;

import com.example.m10.domain.project.domain.Project;
import com.example.m10.domain.project.domain.repository.ProjectRepository;
import com.example.m10.domain.project.presentation.dto.request.CreateProjectRequest;
import com.example.m10.domain.project.presentation.dto.response.ProjectResponse;
import com.example.m10.domain.user.domain.User;
import com.example.m10.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateProjectService {

    private final UserFacade userFacade;
    private final ProjectRepository projectRepository;

    @Transactional
    public ProjectResponse createProject(CreateProjectRequest request) {
        User user = userFacade.getCurrentUser();

        Project project = projectRepository.save(
                Project.builder()
                        .name(request.name())
                        .active(false)
                        .build()
        );

        return ProjectResponse.from(project);
    }
}
