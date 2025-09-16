package com.example.m10.domain.node.domain.repository;

import com.example.m10.domain.node.domain.entity.common.Node;
import com.example.m10.domain.node.domain.enums.node.NodeName;
import com.example.m10.domain.project.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NodeRepository extends JpaRepository<Node, Long> {
    List<Node> findAllByProjectId(Long projectId);
    boolean existsByProjectAndName(Project project, NodeName name);
    Optional<Node> findByProjectAndName(Project project, NodeName name);
}
