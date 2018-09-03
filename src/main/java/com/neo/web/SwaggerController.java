package com.neo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neo.annotation.ResInfo;
import com.neo.util.RespDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/swagger")
@Api()
public class SwaggerController {
    
    private final static Logger logger = LoggerFactory.getLogger(SwaggerController.class);

    @ApiOperation(value="快速登陆系统")
    @ApiImplicitParams({
        @ApiImplicitParam(name="mobile",value="手机号",required=true,dataType="String"),
        @ApiImplicitParam(name="verifyCode",value="验证码",required=true,dataType="String")
    })
    @RequestMapping("/fastEntry")
    @ResInfo(authc = ResInfo.Authc.FREE_PASS)
    public RespDTO fastEntry( String mobile,String verifyCode) {
        logger.info("/seeker/fastEntry,mobile:{},verifyCode:{}", mobile,verifyCode);
        try {
            return RespDTO.success(null);
        }catch (Exception e) {
            logger.error("/seeker/fastEntry fail,", e);
            return RespDTO.fail(e.getMessage());
        }
    }
    
}
