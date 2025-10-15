package com.example.m10.domain.project.service;

import com.example.m10.domain.project.domain.repository.ProjectRepository;
import com.example.m10.domain.project.presentation.dto.response.QueryAllProjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryAllProjectService {

    private final ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public List<QueryAllProjectResponse> getAllProjects(Long accountId) {
        return projectRepository.findAll()
                .stream()
                .map(QueryAllProjectResponse::from)
                .toList();
    }
}
