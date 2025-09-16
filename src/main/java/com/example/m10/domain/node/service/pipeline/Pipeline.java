package com.example.m10.domain.node.service.pipeline;

import com.example.m10.domain.node.domain.entity.common.Node;
import com.example.m10.domain.node.service.common.define.CommonRunNode;
import com.example.m10.domain.node.service.common.define.NodeOutput;
import com.example.m10.domain.node.service.common.define.RunContext;
import com.example.m10.domain.node.service.kind.last.LLMNodeService;
import com.example.m10.domain.node.service.kind.middle.DocumentNodeService;
import com.example.m10.domain.node.service.pipeline.dto.PipelineStart;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class Pipeline { //선형 체인 구조
    private final TraversalService traversalService;
    private final ApplicationContext applicationContext;

    public List<NodeOutput> run(PipelineStart start) {
        List<Node> ordered = traversalService.traverse(start.startOutPortId());
        if (ordered.isEmpty()) return List.of();

        List<NodeOutput> outs = new ArrayList<>();

        for (Node node : ordered) {
            CommonRunNode runner = resolveRunner(node);
            if (runner == null) continue;

            RunContext ctxObj = new RunContext(
                    start.projectId(), node, start.attrs()
            );

            outs.add(runner.run(ctxObj));
        }
        return outs;
    }

    private CommonRunNode resolveRunner(Node node) {
        return switch (node.getName()) {
            case DOCUMENT -> applicationContext.getBean(DocumentNodeService.class);
            case LLM -> applicationContext.getBean(LLMNodeService.class);
            default -> null;
        };
    }

}
