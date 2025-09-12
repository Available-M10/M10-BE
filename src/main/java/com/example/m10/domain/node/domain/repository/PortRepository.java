package com.example.m10.domain.node.domain.repository;

import com.example.m10.domain.node.domain.entity.common.Node;
import com.example.m10.domain.node.domain.entity.common.Port;
import com.example.m10.domain.project.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PortRepository extends JpaRepository<Port, Long> {
    Optional<Port> findByOutPortIdAndNode_Project(UUID outPortId, Project project);
    Optional<Port> findByOutPortId(UUID outPortId);
    Optional<Port> findByNode(Node node);

    // 방금 방문한 노드의 OUT 포트 UUID 가져오기
    @Query("select p.outPortId from Port p where p.node.id = :nodeId")
    Optional<UUID> findOutPortIdByNodeId(@Param("nodeId") Long nodeId);
}
