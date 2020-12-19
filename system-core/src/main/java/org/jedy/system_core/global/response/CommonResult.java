package org.jedy.system_core.global.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResult {

    //    @ApiModelProperty(value = "응답 메시지")
    protected String message;

    //http status
    protected int status;


}