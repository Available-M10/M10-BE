package com.example.m10.domain.node.domain.repository.common;

import com.example.m10.domain.node.domain.entity.common.Edge;
import com.example.m10.domain.node.domain.entity.common.Port;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EdgeRepository extends JpaRepository<Edge, Long> {
    boolean existsByFromPortAndToPort(Port prevOutPort, Port currentPort);
    Optional<Edge> findByFromPort(Port fromPort);
    boolean existsByFromPort_OutPortId(UUID outPortId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    void deleteByToPort_Node_Id(Long nodeId);
}
