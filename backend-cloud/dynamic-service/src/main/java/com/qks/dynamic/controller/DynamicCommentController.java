package com.qks.dynamic.controller;

import com.qks.work.entity.po.DynamicComment;
import com.qks.work.entity.vo.PageVO;
import com.qks.work.entity.vo.ResVO;
import com.qks.work.exception.ServiceException;
import com.qks.dynamic.service.DynamicCommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CompetitionCollectComtroller
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-15 13:03
 */
@RestController
@RequestMapping("/api/dynamic/comment")
public class DynamicCommentController {

    @Resource
    private DynamicCommentService dynamicCommentService;

    /**
     * 获取某一条动态下的评论
     * 一次 20 条
     *
     * @param dynamicId
     * @return
     * @throws ServiceException
     */
    @GetMapping("/list/{dynamicId}")
    public ResVO<PageVO<List<DynamicComment>>> getDynamicCommentList(
            @PathVariable("dynamicId") String dynamicId,
            @RequestBody Integer currentPage) throws ServiceException {
        return dynamicCommentService.getDynamicCommentList(dynamicId, currentPage);
    }

    /**
     * 获取某个评论的子评论
     * 一次 20 条
     *
     * @param parentId
     * @return
     */
    @GetMapping("/list/sub/{parentId}")
    public ResVO<PageVO<List<DynamicComment>>> getSubDynamicComment(
            @PathVariable("parentId") String parentId,
            @RequestBody Integer currentPage) throws ServiceException {
        return dynamicCommentService.getSubDynamicComment(parentId, currentPage);
    }

    /**
     * 给一条动态添加新的评论
     *
     * @param dynamicComment
     * @return
     * @throws ServiceException
     */
    @PostMapping
    public ResVO<Map<String, Object>> addComment(@RequestBody DynamicComment dynamicComment) throws ServiceException {
        return dynamicCommentService.addComment(dynamicComment);
    }

    /**
     * 给一条动态评论添加新的子评论
     *
     * @param dynamicComment
     * @return
     * @throws ServiceException
     */
    @PostMapping("/sub")
    public ResVO<Map<String, Object>> addSubComment(@RequestBody DynamicComment dynamicComment) throws ServiceException {
        return dynamicCommentService.addSubComment(dynamicComment);
    }

    /**
     * 删除一条评论
     *
     * @param dynamicComment
     * @return
     * @throws ServiceException
     */
    @DeleteMapping()
    public ResVO<Map<String, Object>> deleteComment(@RequestBody DynamicComment dynamicComment) throws ServiceException {
        return dynamicCommentService.deleteComment(dynamicComment);
    }

}
