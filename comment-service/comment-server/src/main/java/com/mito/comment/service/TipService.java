package com.mito.comment.service;

import com.mito.comment.pojo.po.Tip;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author root
 * @since 2024-06-08
 */
public interface TipService extends IService<Tip> {

    Tip tip();
}
