package com.mmall.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/13 0013 15:49
 */
@Controller
public class TestController {
    @RequestMapping("/ppx.do")
    @ResponseBody
    /**如果传递来的是Null,就走默认的值；*/
    public String testSth(@RequestParam(value = "id",defaultValue = "1") Integer id,@RequestParam(value = "ppx", defaultValue = "ppxName") String name){
        return "hello,world.我不开心？？？？？？？？？？？？？？？？";

    }
}
