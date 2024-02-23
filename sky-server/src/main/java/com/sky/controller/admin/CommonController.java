package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.channels.MulticastChannel;

/**
 * 通用接口
 */
@Slf4j
@RestController
@Api("文件上传")
@RequestMapping("/admin/common")
public class CommonController {

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("upload")
    //TODO 图片上传OSS
    public Result upload(MulticastChannel file){

        return Result.success() ;
    }
}
