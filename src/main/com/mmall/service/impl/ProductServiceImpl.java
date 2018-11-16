package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CategoryMapper;
import com.mmall.dao.ProductMapper;
import com.mmall.pojo.Category;
import com.mmall.pojo.Product;
import com.mmall.service.ICategoryService;
import com.mmall.service.IProductService;
import com.mmall.util.DateTimeUtil;
import com.mmall.util.PropertiesUtil;
import com.mmall.vo.ProductDetailVo;
import com.mmall.vo.ProductListVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geely
 */
@Service("iProductService")
public class ProductServiceImpl implements IProductService {


    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ICategoryService iCategoryService;

    @Override
    public ServerResponse<PageInfo> getProductPageTest(int pageNum, int pageSize) {
        PageHelper.startPage(1, 2);
        List<Product> products = productMapper.MyPageTest();

        return ServerResponse.createBySuccess(new PageInfo(products));
    }

    public ServerResponse<PageInfo> getProductListByMe(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> productList = productMapper.selectList();

        PageInfo pageInfo = new PageInfo(productList);
        //pageInfo.setList(productList);
        return ServerResponse.createBySuccess(pageInfo);

    }

    /**
     * 1 通用用户操作，保存或者更新操作
     */
    public ServerResponse saveOrUpdateProduct(Product product) {
        /**对传入的商品进行null判断*/
        if (product != null) {
            /**对商品的子图进行判断*/
            if (StringUtils.isNotBlank(product.getSubImages())) {
                /**得到图片的集合*/
                String[] subImageArray = product.getSubImages().split(",");
                /**判断图片的数组里面是有内容的*/
                if (subImageArray.length > 0) {
                    /**吧图片里面的数组图片集合的第一个进行设置为商品的主图*/
                    product.setMainImage(subImageArray[0]);
                }
            }
/**商品的id存在，说明是更新操作*/
            if (product.getId() != null) {
                /**进行更新并且返回受影响的行数*/
                int rowCount = productMapper.updateByPrimaryKey(product);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccess("更新产品成功");
                }
                return ServerResponse.createBySuccess("更新产品失败");
            } else {
                /**到了这里了就是商品的id不存在，那就是插入商品操作，进行插入并且返回受用想的行数*/
                int rowCount = productMapper.insert(product);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccess("新增产品成功");
                }
                return ServerResponse.createBySuccess("新增产品失败");
            }
        }
        /**到了这里就是更新或者插入失败返回提示信息给前端；*/
        return ServerResponse.createByErrorMessage("新增或更新产品参数不正确");
    }

    /**
     * 2 更新商品的销售状态，上下架，在售。。。。。
     */
    public ServerResponse<String> setSaleStatus(Integer productId, Integer status) {
        /**对传入的商品的id和状态进行null判断*/
        if (productId == null || status == null) {
            /**参数不合理，进行失败状态码的返回和失败详情的返回*/
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        /**仅仅更新着两个字段，有条件的更新*/
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        /**对数据进行更新并且返回受影响的行数；*/
        int rowCount = productMapper.updateByPrimaryKeySelective(product);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("修改产品销售状态成功");
        }
        return ServerResponse.createByErrorMessage("修改产品销售状态失败");
    }

    /**
     * 2 得到商品的详情信息
     */
    public ServerResponse<ProductDetailVo> manageProductDetail(Integer productId) {
        if (productId == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product == null) {
            return ServerResponse.createByErrorMessage("产品已下架或者删除");
        }
        ProductDetailVo productDetailVo = assembleProductDetailVo(product);
        return ServerResponse.createBySuccess(productDetailVo);
    }

    /**
     * 3 返回商品的列表
     */
    private ProductDetailVo assembleProductDetailVo(Product product) {
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


    /**
     * 4 得到商品的所有的列表
     */
    //?????/?
    public ServerResponse<PageInfo> getProductList(int pageNum, int pageSize) {
        //startPage--start
        //填充自己的sql查询逻辑
        //pageHelper-收尾
        /**开始页码，和每一页的容量*/
        PageHelper.startPage(pageNum, pageSize);
        /**进行查询商品信息返回的是一个商品的集合*/
        List<Product> productList = productMapper.selectList();
/**声明guava工具类的list集合,存放数据*/
        List<ProductListVo> productListVoList = Lists.newArrayList();
        /**吧所有的商品的信息都进行封装成Vo对象*/
        for (Product productItem : productList) {
            ProductListVo productListVo = assembleProductListVo(productItem);
            productListVoList.add(productListVo);
        }
        /**收尾进行分页处理*/
        PageInfo pageResult = new PageInfo(productList);
        /**返回我们需要的分页结果*/
        /**通过放射设置数据*/
        pageResult.setList(productListVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    private ProductListVo assembleProductListVo(Product product) {
        /**因为返回的商品信息，有些字段是不需要的，所以封装Vo对象进行接收需要的字段*/
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
     * 5 分页的搜索，商品
     */
    public ServerResponse<PageInfo> searchProduct(String productName, Integer productId, int pageNum, int pageSize) {
        /**进行分页，*/
        PageHelper.startPage(pageNum, pageSize);
        if (StringUtils.isNotBlank(productName)) {
            /**查询包含商品名字的所有商品*/
            productName = new StringBuilder().append("%").append(productName).append("%").toString();
        }
        List<Product> productList = productMapper.selectByNameAndProductId(productName, productId);
        List<ProductListVo> productListVoList = Lists.newArrayList();
        for (Product productItem : productList) {
            ProductListVo productListVo = assembleProductListVo(productItem);
            productListVoList.add(productListVo);
        }
        /**代码复用上一次的代码；*/
        /**吧得到的商品信息进行封装*/
        /**根据集合进行分页的处理*/
        PageInfo pageResult = new PageInfo(productList);
        /**不是吧查询到的所有数据给前端，但是还需要分页
         * */
        pageResult.setList(productListVoList);
        /**吧列表重置就是需要先显示的结果*/
        return ServerResponse.createBySuccess(pageResult);

    }


    public ServerResponse<ProductDetailVo> getProductDetail(Integer productId) {
        if (productId == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product == null) {
            return ServerResponse.createByErrorMessage("产品已下架或者删除");
        }
        /**删除就是修改状态，查到商品状态可能已经下架，进行判断*/
        if (product.getStatus() != Const.ProductStatusEnum.ON_SALE.getCode()) {
            return ServerResponse.createByErrorMessage("产品已下架或者删除");
        }
        ProductDetailVo productDetailVo = assembleProductDetailVo(product);
        return ServerResponse.createBySuccess(productDetailVo);
    }


    public ServerResponse<PageInfo> getProductByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize, String orderBy) {
        if (StringUtils.isBlank(keyword) && categoryId == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        /**大的分类id下面有很对的子分类id查过来放进去*/
        List<Integer> categoryIdList = new ArrayList<Integer>();

        if (categoryId != null) {
            Category category = categoryMapper.selectByPrimaryKey(categoryId);
            if (category == null && StringUtils.isBlank(keyword)) {
                //没有该分类,并且还没有关键字,这个时候返回一个空的结果集,不报错
                PageHelper.startPage(pageNum, pageSize);
                List<ProductListVo> productListVoList = Lists.newArrayList();
                PageInfo pageInfo = new PageInfo(productListVoList);
                return ServerResponse.createBySuccess(pageInfo);
            }
            categoryIdList = iCategoryService.selectCategoryAndChildrenById(category.getId()).getData();
        }
        if (StringUtils.isNotBlank(keyword)) {
            keyword = new StringBuilder().append("%").append(keyword).append("%").toString();
        }

        PageHelper.startPage(pageNum, pageSize);
        //排序处理
        if (StringUtils.isNotBlank(orderBy)) {
            if (Const.ProductListOrderBy.PRICE_ASC_DESC.contains(orderBy)) {
                /**c传入的数据格式price_asc,进行分割
                 * 然后在拼接*/
                String[] orderByArray = orderBy.split("_");
                PageHelper.orderBy(orderByArray[0] + " " + orderByArray[1]);
            }
        }
        List<Product> productList = productMapper.selectByNameAndCategoryIds(StringUtils.isBlank(keyword) ? null : keyword, categoryIdList.size() == 0 ? null : categoryIdList);

        List<ProductListVo> productListVoList = Lists.newArrayList();
        for (Product product : productList) {
            ProductListVo productListVo = assembleProductListVo(product);
            productListVoList.add(productListVo);
        }

        PageInfo pageInfo = new PageInfo(productList);
        pageInfo.setList(productListVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }


}
