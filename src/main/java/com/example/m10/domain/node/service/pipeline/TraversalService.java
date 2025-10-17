package com.example.m10.domain.node.service.pipeline;

import com.example.m10.domain.node.domain.entity.common.Edge;
import com.example.m10.domain.node.domain.entity.common.Node;
import com.example.m10.domain.node.domain.entity.common.Port;
import com.example.m10.domain.node.domain.repository.common.EdgeRepository;
import com.example.m10.domain.node.domain.repository.common.PortRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TraversalService {

    private final EdgeRepository edgeRepository;
    private final PortRepository portRepository;

    //선형 그래프 순회 로직
    @Transactional(readOnly = true)
    public List<Node> traverse(UUID startOutPortId){
        List<Node> ordered = new ArrayList<>();
        if(startOutPortId == null) return ordered;

        UUID cursor = startOutPortId;
        Set<UUID> visitedOut = new HashSet<>();

        while (cursor != null && visitedOut.add(cursor)){
            Node node = portRepository.findByOutPortId(cursor)
                    .flatMap(edgeRepository::findByFromPort) //outPortId를 통해 Edge 반환
                    .map(Edge::getToPort) //Edge 엔티티의 inPort 가져오기
                    .map(Port::getNode) //Port 엔티티와 연결된 Node 가져오기
                    .orElse(null); //에러가 발생한다면 안전하게 null로 감싸기
            if(node == null) break;
            ordered.add(node);

            cursor = portRepository.findOutPortIdByNodeId(node.getId()) //노드 아이디로 OutPortId 가져오기
                    .orElse(null);
        }

        return ordered;
    }

    @Transactional(readOnly = true)
    public boolean hasConnections(Long nodeId){
        UUID outPortId = portRepository.findOutPortIdByNodeId(nodeId)
                .orElse(null);
        return outPortId !=null && edgeRepository.existsByFromPort_OutPortId(outPortId);
    }
}
