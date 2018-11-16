package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mmall.common.ServerResponse;
import com.mmall.dao.MyShippingMapper;
import com.mmall.dao.ShippingMapper;
import com.mmall.pojo.Shipping;
import com.mmall.service.MyShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.ServletContextResourcePatternResolver;

import java.util.List;
import java.util.Map;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/4 0004 16:20
 */
@Service
public class MyShippingServiceImpl implements MyShippingService {
    /**
     * 注入自己的mapper接口
     */
    @Autowired
    private MyShippingMapper myShippingMapper;


    /**
     * 1 添加收货地址
     */
    @Override
    public ServerResponse add(Integer userId, Shipping shipping) {
        shipping.setUserId(userId);
        int insert = myShippingMapper.insert(shipping);
        if (insert < 0) {
            return ServerResponse.createByErrorMessage("添加收货地址失败！！！");
        }
        /**成功了*/
        Map result = Maps.newHashMap();
        /**前端需要生成的sjippingId*/
        result.put("shippingId", shipping.getId());
        return ServerResponse.createBySuccess("添加收货地址十分成功", result);

    }

    @Override
    /** 2 根据用户的id和用户下的收货地址的id进行删除*/
    public ServerResponse del(Integer userId, Integer shippingId) {
        int i = myShippingMapper.deleteByShippingIdUserId(userId, shippingId);
        if (i > 0) {
            return ServerResponse.createBySuccess("删除非常的成功");
        }
        return ServerResponse.createByErrorMessage("删除十分失败");
    }

    @Override
    /**3 根据用户的id,和对象进行更新，用户的id从session中拿到的*/
    public ServerResponse update(Integer userId, Shipping shipping) {
        /**防止传入假的userid,更新别人的地址，必须充登录用户的sessionn中拿到userid*/
        shipping.setUserId(userId);

        int i = myShippingMapper.updateShipping(shipping);
        if (i > 0) {
            return ServerResponse.createBySuccessMessage("更新非常的成功");
        }
        return ServerResponse.createByErrorMessage("更新呢非诚的失败");
    }

    @Override
    /**4 查询根据自己的用户的id，去查询自己下面的收货地址详情，id是自己的，防止虚假的*/
    public ServerResponse selectDetails(Integer userId, Integer shippingId) {
        Shipping shipping = myShippingMapper.selectByShippingIdUserId(userId, shippingId);
        if (shipping == null) {
            return ServerResponse.createByErrorMessage("十分找不到这个收货地址");
        }
        /**查询单个收货地址的详情*/
        return ServerResponse.createBySuccess("更新地址十分得的成功", shipping);
    }

    @Override
    public ServerResponse<PageInfo> showShippingList(Integer userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Shipping> shippings = myShippingMapper.selectShippingList(userId);
        return ServerResponse.createBySuccess(new PageInfo(shippings));
    }
}
