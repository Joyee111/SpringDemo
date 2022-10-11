package com.example.serious.demo.controller;

import com.example.serious.demo.service.Impl.ansyTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@RestController
@Api(tags = "测试接口")
@RequestMapping("/test")
public class ansyTestController {

    @Autowired
    ansyTestService ansyTestService;

    @PostMapping("/testApi")
    @ApiOperation("添加用户的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "李四"),
            @ApiImplicitParam(name = "address", value = "用户地址", defaultValue = "深圳", required = true)
    }
    )
    public  Integer methodA( String username, @RequestParam(required = true) String address) throws Exception{
        long start = System.currentTimeMillis();
        Future<Integer> future1 =ansyTestService.methodB();
        long end = System.currentTimeMillis();
        return 1;
    }


}
