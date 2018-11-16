package com.mmall.controller.backend;

import com.mmall.common.ServerResponse;
import com.mmall.service.CountNumber;
import com.mmall.vo.CountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/10/12 0012 17:18
 */
@Controller
@RequestMapping(" ")
public class CountController {
    @Autowired
    CountNumber countNumber;

    /**
     * 1 统计的实现
     */
    @RequestMapping("/count.do")
    @ResponseBody
    public ServerResponse<CountVo> count() {
        ServerResponse<CountVo> count = countNumber.count();
        return count;
    }
}
