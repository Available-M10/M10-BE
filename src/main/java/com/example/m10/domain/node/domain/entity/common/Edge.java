package com.example.m10.domain.node.domain.entity.common;

import com.example.m10.domain.project.domain.Project;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(
        uniqueConstraints = @UniqueConstraint(name = "uk_edge_from_to", columnNames = {"from_port_id","to_port_id"}),
        indexes = {
                @Index(name="ix_edge_from_port", columnList="from_port_id"),
                @Index(name="ix_edge_to_port", columnList="to_port_id")
        }
)

public class Edge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    // from_port_id(UUID) → Port.out_port_id(UUID)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_port_id", referencedColumnName = "out_port_id", nullable = false)
    private Port fromPort;

    // to_port_id(UUID) → Port.in_port_id(UUID)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_port_id", referencedColumnName = "in_port_id", nullable = false)
    private Port toPort;
}
