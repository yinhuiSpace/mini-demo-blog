package com.mito.comment.service.impl;

import cn.hutool.core.util.IdUtil;
import com.mito.comment.pojo.po.Board;
import com.mito.comment.mapper.BoardMapper;
import com.mito.comment.service.BoardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author root
 * @since 2024-06-08
 */
@Service
public class BoardServiceImpl extends ServiceImpl<BoardMapper, Board> implements BoardService {

    @Override
    public void add(Board board) {
        board.setId(IdUtil.getSnowflake().nextId());
        save(board);
    }
}
