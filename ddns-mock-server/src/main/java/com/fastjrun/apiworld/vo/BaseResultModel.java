/*
 * Copyright (C) 2021 Fastjrun, Inc. All Rights Reserved.
 */
package com.fastjrun.apiworld.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("返回值结构")
public abstract class BaseResultModel {
    @ApiModelProperty("返回值状态码(0000为成功)")
    protected Integer code;
    @ApiModelProperty("返回值消息")
    protected String message = "";
}
