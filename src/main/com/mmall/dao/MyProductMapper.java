package com.mmall.dao;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/5 0005 14:39
 */
public interface MyProductMapper {
    List<Product> mylistProduct();

    List<Product> mySearchProductList(@Param("productName") String productName,
                                      @Param("productId") Integer productId);
/**翻个的可以用生成的根据主键来查询来代替*/
    // Product getOneProductDetails(Integer productId);

    //int MyUpdateOneProductStatus(@Param("productId") Integer productId,
    // @Param("status") Integer status);

    /**
     * 新增或者增加，一个方法公用，判断id是否存在
     * 上面是自己写的
     */
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> selectList();

    List<Product> MyPageTest();

    List<Product> selectByNameAndProductId(@Param("productName") String productName, @Param("productId") Integer productId);

    List<Product> selectByNameAndCategoryIds(@Param("productName") String productName, @Param("categoryIdList") List<Integer> categoryIdList);


}
