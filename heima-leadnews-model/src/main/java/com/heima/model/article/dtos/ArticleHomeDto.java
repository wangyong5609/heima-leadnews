package com.heima.model.article.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "文章首页")
public class ArticleHomeDto {
    @ApiModelProperty(value = "最大时间", required = true)
    Date maxBehotTime;
    @ApiModelProperty(value = "最小时间", required = true)
    Date minBehotTime;
    @ApiModelProperty(value = "加载类型", required = true)
    Integer loaddir;
    @ApiModelProperty(value = "分页size", required = true)
    Integer size;
    @ApiModelProperty(value = "频道ID", required = true)
    String tag;
}