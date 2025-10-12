package com.example.m10.domain.project.service;

import com.example.m10.domain.project.domain.repository.ProjectRepository;
import com.example.m10.domain.project.presentation.dto.response.QueryAllProjectResponse;
import com.example.m10.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryAllProjectService {

    private final ProjectRepository projectRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public List<QueryAllProjectResponse> getAllProjects() {
        return projectRepository.findAllByOwner(userFacade.getCurrentUser())
                .stream()
                .map(QueryAllProjectResponse::from)
                .toList();
    }
}
