package com.mito.comment.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mito.client.BlogClient;
import com.mito.client.UserClient;
import com.mito.client.pojo.po.Blog;
import com.mito.client.pojo.po.User;
import com.mito.comment.constants.CommentConstants;
import com.mito.comment.exception.SystemException;
import com.mito.comment.pojo.po.Comment;
import com.mito.comment.mapper.CommentMapper;
import com.mito.comment.pojo.vo.CommentListVo;
import com.mito.comment.pojo.vo.CommentQueryListVo;
import com.mito.comment.pojo.vo.CommentQueryVo;
import com.mito.comment.pojo.vo.CommentVo;
import com.mito.comment.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mito.common.enums.RestResultEnum;
import com.mito.common.utils.BeanCopyUtil;
import com.mito.common.utils.DateTimeClient;
import com.mito.common.utils.SecurityContextUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author root
 * @since 2024-06-05
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    UserClient userClient;

    @Resource
    BlogClient blogClient;

    @Override
    public CommentListVo commentList(String commentType,Integer pageNum, Integer pageSize, Long articleId) {

        //根据文章id，分页查询所有根评论
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        //评论类型
        wrapper.eq(Comment::getType,commentType);
        //文章id
        wrapper.eq(CommentConstants.TYPE_ARTICLE.equals(commentType),Comment::getArticleId,articleId);
        //根评论id为-1
        wrapper.eq(Comment::getRootId,-1);
        //分页查询
        Page<Comment> page = page(new Page<Comment>(pageNum, pageSize), wrapper);

        List<CommentVo> commentVos = toCommentVos(page.getRecords());

        for (CommentVo commentVo : commentVos) {
            commentVo.setChildren(getChildren(Long.parseLong(commentVo.getId())));
        }

        CommentListVo commentListVo = new CommentListVo();

        commentListVo.setRows(commentVos)
                .setTotal(page.getTotal());

        return commentListVo;
    }

    @Override
    public void addComment(Comment comment) {
        String content = comment.getContent();
        if (!StringUtils.hasText(content)){
            throw new SystemException(RestResultEnum.INVALID_PARAM);
        }

        Snowflake snowflake = IdUtil.getSnowflake();

        long id = snowflake.nextId();
        comment.setId(id);

        save(comment);
    }

    @Override
    public CommentQueryListVo getPage(Long pageNum, Long pageSize, String content) {

        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();

        wrapper.like(StringUtils.hasText(content),Comment::getContent,content);

        Page<Comment> page = page(Page.of(pageNum, pageSize), wrapper);

        List<CommentQueryVo> queryVos = page.getRecords().stream().map(new Function<Comment, CommentQueryVo>() {
            @Override
            public CommentQueryVo apply(Comment comment) {

                CommentQueryVo commentQueryVo = BeanCopyUtil.copyBean(comment, CommentQueryVo.class);
                LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();

                queryWrapper.eq(Comment::getRootId,comment.getId());
                commentQueryVo.setChildren(count(queryWrapper));

                User toUser = userClient.getOneById(comment.getToCommentUserId());
                if (toUser!=null){
                    commentQueryVo.setToCommentUsername(toUser.getUsername());
                }

                commentQueryVo.setUsername(userClient.getOneById(comment.getCreateBy()).getUsername());

                Blog blog = blogClient.getOne(comment.getArticleId());
                if (blog!=null){
                    commentQueryVo.setTitle(blog.getTitle());
                }

                return commentQueryVo;
            }
        }).collect(Collectors.toList());

        CommentQueryListVo queryListVo = new CommentQueryListVo();
        queryListVo.setTotal(page.getTotal())
                .setRows(queryVos);

        return queryListVo;
    }

    @Override
    public void deleteById(Long id) {

        removeById(id);
    }

    private List<CommentVo> getChildren(Long id){

        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getRootId,id);
        wrapper.orderByAsc(Comment::getCreateTime);

        List<Comment> comments = list(wrapper);

        return toCommentVos(comments);
    }

    private List<CommentVo> toCommentVos(List<Comment> comments){

        return comments.stream().map(new Function<Comment, CommentVo>() {
            @Override
            public CommentVo apply(Comment comment) {

                CommentVo commentVo = BeanCopyUtil.copyBean(comment, CommentVo.class);

                //根据用户id，查处用户名
                User user = userClient.getOneById(comment.getCreateBy());
                if (comment.getToCommentUserId()!=-1){
                    //查出所属评论用户名
                    User parentUser = userClient.getOneById(comment.getToCommentUserId());
                    commentVo.setToCommentUsername(parentUser.getUsername());
                }


                commentVo.setUsername(user.getUsername())
                        .setAvatar(user.getAvatar());

                return commentVo;
            }
        }).collect(Collectors.toList());
    }
}
