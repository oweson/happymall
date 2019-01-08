package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mmall.common.ServerResponse;
import com.mmall.dao.ShippingMapper;
import com.mmall.pojo.Shipping;
import com.mmall.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("iShippingService")
public class ShippingServiceImpl implements IShippingService {


    @Autowired
    private ShippingMapper shippingMapper;

    /**
     * 1 添加收货地址
     */
    @Override
    public ServerResponse add(Integer userId, Shipping shipping) {
        /**用户的id是从后端拿到的，session哪里拿到的，防止恶心用户攻击，进行设置*/
        shipping.setUserId(userId);
        int rowCount = shippingMapper.insert(shipping);
        if (rowCount > 0) {
            Map result = Maps.newHashMap();
            /**添加成功后返回收货地址的id给前端,让用户看到添加成功了*/
            result.put("shippingId", shipping.getId());
            return ServerResponse.createBySuccess("新建地址成功", result);
        }
        return ServerResponse.createByErrorMessage("新建地址失败");
    }

    /**
     * 2 删除收货地址
     */
    @Override
    public ServerResponse<String> del(Integer userId, Integer shippingId) {
        int resultCount = shippingMapper.deleteByShippingIdUserId(userId, shippingId);
        if (resultCount > 0) {
            return ServerResponse.createBySuccess("删除地址成功");
        }
        return ServerResponse.createByErrorMessage("删除地址失败");
    }

    /**
     * 3 更新收货地址
     */
    @Override
    public ServerResponse update(Integer userId, Shipping shipping) {
        /**防止传入假的userid,更新别人的地址，必须在登录用户的session中拿到userid*/
        shipping.setUserId(userId);
        int rowCount = shippingMapper.updateByShipping(shipping);
        if (rowCount > 0) {
            /**数据库的底层总有这那样的原因存在，所以要进行受影响行数的判断*/
            return ServerResponse.createBySuccess("更新地址成功");
        }
        return ServerResponse.createByErrorMessage("更新地址失败");
    }

    /**
     * 4 查看收货地址
     */
    @Override
    public ServerResponse<Shipping> select(Integer userId, Integer shippingId) {
        /**防止越权看别人的，修改审查元素；
         * 保证这个用户的收货地址一定是指向这个用户的*/
        Shipping shipping = shippingMapper.selectByShippingIdUserId(userId, shippingId);
        if (shipping == null) {
            return ServerResponse.createByErrorMessage("无法查询到该地址");
        }
        //todo 提示信息
        return ServerResponse.createBySuccess("查找地址成功", shipping);
    }

    /**
     * 5 查看收货地址的列表
     */
    @Override
    public ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Shipping> shippingList = shippingMapper.selectByUserId(userId);
        PageInfo pageInfo = new PageInfo(shippingList);
        return ServerResponse.createBySuccess(pageInfo);
    }


}
