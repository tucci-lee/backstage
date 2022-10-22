package com.tuccicode.backstage.app.system.dto.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author tucci.lee
 */
@Data
@Accessors(chain = true)
public class SysResVO {
    private Long id;
    private String name;
    private Integer type;
    private String url;
    private Long pid;
    private String resChar;
    private Integer seq;
    private Long createTime;
    private Long updateTime;
    private List<SysResVO> children;

    /**
     * 递归加载树节点
     *
     * @param nodes    节点
     * @param parentId 父级id
     * @return 树节点
     */
    public static List<SysResVO> tree(List<SysResVO> nodes, Long parentId) {
        // 获取所有父节点
        List<SysResVO> parentNodes = new ArrayList<>();
        for (SysResVO node : nodes) {
            if (node.getPid().equals(parentId)) {
                parentNodes.add(node);
            }
        }
        if (CollectionUtils.isEmpty(parentNodes)) {
            return parentNodes;
        }

        // 排序
        parentNodes.sort(Comparator.comparingInt(SysResVO::getSeq));
        // 递归加载子节点
        for (SysResVO parentNode : parentNodes) {
            List<SysResVO> childrenNodes = SysResVO.tree(nodes, parentNode.getId());
            if (!CollectionUtils.isEmpty(childrenNodes)) {
                parentNode.setChildren(childrenNodes);
            }
        }

        return parentNodes;
    }
}
