package com.example.m10.domain.project.domain.repository;

import com.example.m10.domain.project.domain.Project;
import com.example.m10.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByOwner(User owner);
}
