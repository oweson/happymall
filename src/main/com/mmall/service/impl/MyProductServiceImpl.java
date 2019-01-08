package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CategoryMapper;
import com.mmall.dao.MyProductMapper;
import com.mmall.pojo.Category;
import com.mmall.pojo.Product;
import com.mmall.service.MyProductService;
import com.mmall.util.DateTimeUtil;
import com.mmall.util.PropertiesUtil;
import com.mmall.vo.ProductDetailVo;
import com.mmall.vo.ProductListVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/5 0005 14:38
 */
//@SuppressWarnings(MyCategoryServiceImpl.class)
@Service
public class MyProductServiceImpl implements MyProductService {
    @Autowired
    private MyProductMapper myProductMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 1 分页的显示商品
     */
    @Override
    public ServerResponse<PageInfo> MyListProduct(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> products = myProductMapper.mylistProduct();
/**有的字段不需要返回，需要组装集合对象*/
        ArrayList<ProductListVo> productListVos = Lists.newArrayList();
        for (Product product : products) {
            ProductListVo productListVo = assempleProductVo(product);
            productListVos.add(productListVo);
            /**进行对象的组装*/
        }
        PageInfo pageInfo = new PageInfo(products);
        pageInfo.setList(productListVos);
        return ServerResponse.createBySuccess(pageInfo);


    }

    private ProductListVo assempleProductVo(Product product) {
        ProductListVo productListVo = new ProductListVo();
        productListVo.setId(product.getId());
        productListVo.setName(product.getName());
        productListVo.setCategoryId(product.getCategoryId());
        productListVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix", "http://img.happymmall.com/"));
        productListVo.setMainImage(product.getMainImage());
        productListVo.setPrice(product.getPrice());
        productListVo.setSubtitle(product.getSubtitle());
        productListVo.setStatus(product.getStatus());
        return productListVo;

    }

    /**
     * 2 商品的搜索
     */
    @Override
    public ServerResponse<PageInfo> mySearchProduct(String productName, Integer productId,
                                                    Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (StringUtils.isNotBlank(productName)) {
            productName = new StringBuilder().append("%").append("productName").append("%").toString();
        }
        List<Product> productsSearch = myProductMapper.mySearchProductList(productName, productId);
        ArrayList<ProductListVo> searchResult = Lists.newArrayList();
        for (Product search : productsSearch) {
            ProductListVo productListVo = assempleProductVo(search);
            /**组装集合对象*/
            searchResult.add(productListVo);
        }
        PageInfo pageInfo = new PageInfo(productsSearch);
        pageInfo.setList(searchResult);
        return ServerResponse.createBySuccess(pageInfo);


    }


    /**
     * 3 新增或者更新产品
     */
    @Override
    public ServerResponse saveOrUpdate(Product product) {
        /**不为空这是更新*/
        if (product != null) {
            /**分割子图*/
            if (StringUtils.isNotBlank(product.getSubImages())) {

                String[] split = product.getSubImages().split(",");
                if (split.length > 0) {
                    /**取出第一个子图付给主图*/
                    product.setMainImage(split[0]);
                }
                int i = myProductMapper.updateByPrimaryKey(product);
                if (i > 0) {
                    ServerResponse.createBySuccess("更新非常的成功");
                } else {
                    ServerResponse.createBySuccess("更新十分的失败");
                }
                int insert = myProductMapper.insert(product);
                if (insert > 0) {
                    return ServerResponse.createBySuccess("新郑产品十分的成功");
                } else {
                    return ServerResponse.createBySuccess("新郑产品十分的失败");
                }


            }
        }

        return ServerResponse.createByErrorMessage("新增或者更新产品的参数十分的不正确");
    }

    /**
     * 4 设置产品的销售状态
     */
    @Override
    public ServerResponse<String> setStatus(Integer productId, Integer status) {
        if (productId == null || status == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());

        }
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int i = myProductMapper.updateByPrimaryKeySelective(product);
        if (i > 0) {
            return ServerResponse.createBySuccess("修改产品的状态十分的成功");
        }
        return ServerResponse.createByErrorMessage("修改产品的状态非常的失败");

    }

    /**
     * 5 得到某个产品的详情
     */
    @Override
    public ServerResponse<ProductDetailVo> getOneProductDetails(Integer productId) {
        if (productId == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = myProductMapper.selectByPrimaryKey(productId);
        if (product == null) {
            return ServerResponse.createByErrorMessage("商品也已经十分的不存在，或者下架了");
        }
        ProductDetailVo productDetailVo = assemalOneProductDetails(product);
        return ServerResponse.createBySuccess(productDetailVo);

    }

    private ProductDetailVo assemalOneProductDetails(Product product) {
        /**自己定义的用来封装对象的vo对象*/
        ProductDetailVo productDetailVo = new ProductDetailVo();
        productDetailVo.setId(product.getId());
        productDetailVo.setSubtitle(product.getSubtitle());
        productDetailVo.setPrice(product.getPrice());
        productDetailVo.setMainImage(product.getMainImage());
        productDetailVo.setSubImages(product.getSubImages());
        productDetailVo.setCategoryId(product.getCategoryId());
        productDetailVo.setDetail(product.getDetail());
        productDetailVo.setName(product.getName());
        productDetailVo.setStatus(product.getStatus());
        productDetailVo.setStock(product.getStock());

        productDetailVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix", "http://img.happymmall.com/"));
/**category找不到说明就是id为0，就是根节点；为0*/
        Category category = categoryMapper.selectByPrimaryKey(product.getCategoryId());
        if (category == null) {
            productDetailVo.setParentCategoryId(0);
            //默认根节点
        } else {
            /**不为null,就是子分类的节点；设置父目录的节点；*/
            productDetailVo.setParentCategoryId(category.getParentId());
        }
/**用工具类对时间进行转换；*/
        productDetailVo.setCreateTime(DateTimeUtil.dateToStr(product.getCreateTime()));
        productDetailVo.setUpdateTime(DateTimeUtil.dateToStr(product.getUpdateTime()));
        return productDetailVo;
    }


}
