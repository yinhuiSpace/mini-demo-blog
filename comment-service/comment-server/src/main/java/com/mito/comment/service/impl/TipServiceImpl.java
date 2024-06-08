package com.mito.comment.service.impl;

import com.mito.comment.pojo.po.Tip;
import com.mito.comment.mapper.TipMapper;
import com.mito.comment.service.TipService;
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
public class TipServiceImpl extends ServiceImpl<TipMapper, Tip> implements TipService {



    @Override
    public Tip tip() {

        TipMapper tipMapper = getBaseMapper();

        return tipMapper.getTipByRandom();
    }
}
