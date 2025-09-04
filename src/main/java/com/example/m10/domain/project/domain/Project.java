package com.example.m10.domain.project.domain;

import com.example.m10.domain.node.domain.Node;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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

    private boolean active; // 현재는 on / off 두 개만 있어서 boolean으로 처리했는데 나중에 상태가 더 늘어나면 enum으로 바꾸는게 좋을듯
    // 아니면 관리자같은 권한이 추가되면 enum으로 바꾸거나

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @Builder.Default
    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Node> nodes = new ArrayList<>();

    public void updateName(String name) {
        this.name = name;
        this.updateAt = LocalDateTime.now();
    }
}
