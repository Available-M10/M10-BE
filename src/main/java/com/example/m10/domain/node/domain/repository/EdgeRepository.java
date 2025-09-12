package com.example.m10.domain.node.domain.repository;

import com.example.m10.domain.node.domain.entity.common.Edge;
import com.example.m10.domain.node.domain.entity.common.Port;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EdgeRepository extends JpaRepository<Edge, Long> {
    boolean existsByFromPortAndToPort(Port prevOutPort, Port currentPort);
    Optional<Edge> findByFromPort(Port fromPort);
}
