package com.example.m10.domain.node.domain.entity.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Port {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "out_port_id", unique = true, columnDefinition = "BINARY(16)")
    private UUID outPortId;

    @Column(name = "in_port_id", unique = true, columnDefinition = "BINARY(16)")
    private UUID inPortId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "node_id", nullable = false)
    private Node node;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "fromPort", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Edge> outgoingEdges = new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "toPort", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Edge> incomingEdges = new ArrayList<>();

    @PrePersist
    void prePersist(){
        this.outPortId = UUID.randomUUID();
    }

    public void lastOutPortId(){
        this.outPortId = null;
    }
}
