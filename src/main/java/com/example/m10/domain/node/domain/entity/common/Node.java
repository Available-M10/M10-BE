package com.example.m10.domain.node.domain.entity.common;

import com.example.m10.domain.node.domain.enums.node.NodeName;
import com.example.m10.domain.node.domain.enums.node.NodeType;
import com.example.m10.domain.project.domain.Project;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NodeName name; //노드의 이름(채팅, 문서, LLM)을 나타냄

    @Enumerated(EnumType.STRING)
    private NodeType type; //시작 노드, 중간 노드, 마지막 노드를 나타냄

    @Column(columnDefinition = "json")
    private String configJson;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @Builder.Default
    @OneToMany(mappedBy = "node", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Port> ports = new ArrayList<>();
}
