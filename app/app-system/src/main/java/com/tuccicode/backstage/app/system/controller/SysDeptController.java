package com.tuccicode.backstage.app.system.controller;

import com.tuccicode.backstage.app.aspect.Operate;
import com.tuccicode.backstage.app.system.dto.body.SysDeptCreateBody;
import com.tuccicode.backstage.app.system.dto.body.SysDeptUpdateBody;
import com.tuccicode.backstage.app.system.service.SysDeptAppService;
import com.tuccicode.concise.dto.Response;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tucci.lee
 */
@RestController
@RequestMapping("sys/dept")
public class SysDeptController {

    private final SysDeptAppService sysDeptAppService;

    public SysDeptController(SysDeptAppService sysDeptAppService) {
        this.sysDeptAppService = sysDeptAppService;
    }

    /**
     * 查询部门树
     */
    @RequiresPermissions(value = {"sys:dept:tree", "sys:user:list"}, logical = Logical.OR)
    @GetMapping("tree")
    public Response tree() {
        return sysDeptAppService.tree();
    }

    /**
     * 添加部门
     */
    @Operate("添加部门")
    @RequiresPermissions(value = {"sys:dept:create"})
    @PostMapping
    public Response create(@Validated @RequestBody SysDeptCreateBody body) {
        return sysDeptAppService.create(body);
    }

    /**
     * 删除部门
     */
    @Operate("删除部门")
    @RequiresPermissions(value = {"sys:dept:delete"})
    @DeleteMapping("{id}")
    public Response delete(@PathVariable Long id) {
        return sysDeptAppService.delete(id);
    }

    /**
     * 修改部门
     */
    @Operate("修改部门")
    @RequiresPermissions(value = {"sys:dept:update"})
    @PutMapping
    public Response update(@Validated @RequestBody SysDeptUpdateBody body) {
        return sysDeptAppService.update(body);
    }

}
