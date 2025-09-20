package com.example.m10.domain.project.presentation;

import com.example.m10.domain.project.presentation.dto.request.ChangeProjectNameRequest;
import com.example.m10.domain.project.presentation.dto.request.CreateProjectRequest;
import com.example.m10.domain.project.presentation.dto.response.ProjectResponse;
import com.example.m10.domain.project.presentation.dto.response.QueryAllProjectResponse;
import com.example.m10.domain.project.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final CreateProjectService createProjectService;
    private final QueryProjectService queryProjectService;
    private final QueryAllProjectService queryAllProjectService;
    private final ChangeProjectNameService changeProjectNameService;
    private final DeleteProjectService deleteProjectService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponse createProject(@RequestBody @Valid CreateProjectRequest request) {
        return createProjectService.createProject(request);
    }

    @GetMapping("/{id}")
    public ProjectResponse getProject(@PathVariable("id") Long id) {
        return queryProjectService.getProject(id);
    }

    @GetMapping
    public List<QueryAllProjectResponse> getAllProjects() {
        return queryAllProjectService.getAllProjects();
    }

    @PatchMapping("/{id}")
    public void changeProjectName(
            @PathVariable("id") Long id,
            @RequestBody @Valid ChangeProjectNameRequest request) {
        changeProjectNameService.changeProjectName(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable("id") Long id) {
        deleteProjectService.deleteProject(id);
    }
}
