package com.mito.blog.pojo.vo;

import com.mito.common.result.ListVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class BlogReviewListVo extends ListVo<BlogReviewVo> {
}
