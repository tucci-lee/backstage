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
public class SysDeptVO {
    private Long id;
    private String name;
    private Long pid;
    private Integer seq;
    private String manager;
    private String managerPhone;
    private Long createTime;
    private Long updateTime;
    private List<SysDeptVO> children;

    /**
     * 递归加载树节点
     *
     * @param nodes    节点
     * @param parentId 父级id
     * @return 树节点
     */
    public static List<SysDeptVO> tree(List<SysDeptVO> nodes, Long parentId) {
        // 获取所有父节点
        List<SysDeptVO> parentNodes = new ArrayList<>();
        for (SysDeptVO node : nodes) {
            if (node.getPid().equals(parentId)) {
                parentNodes.add(node);
            }
        }
        if (CollectionUtils.isEmpty(parentNodes)) {
            return parentNodes;
        }

        // 排序
        parentNodes.sort(Comparator.comparingInt(SysDeptVO::getSeq));
        // 递归加载子节点
        for (SysDeptVO parentNode : parentNodes) {
            List<SysDeptVO> childrenNodes = SysDeptVO.tree(nodes, parentNode.getId());
            if (!CollectionUtils.isEmpty(childrenNodes)) {
                parentNode.setChildren(childrenNodes);
            }
        }

        return parentNodes;
    }
}
