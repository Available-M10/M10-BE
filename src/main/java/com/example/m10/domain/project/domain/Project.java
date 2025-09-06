package com.example.m10.domain.project.domain;

import com.example.m10.domain.node.domain.Node;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private boolean active;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder.Default
    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Node> nodes = new ArrayList<>();

    public void updateName(String name) {
        this.name = name;
    }

    @PrePersist
    public void beforePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    public void beforeUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
