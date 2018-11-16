package com.mmall.service.impl;

import com.mmall.common.ServerResponse;
import com.mmall.dao.CategoryMapper;
import com.mmall.pojo.Category;
import com.mmall.service.MyICategoryservice;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/8/25 0025 10:38
 */
@Service("myICategoryService")
public class MyCategoryServiceImpl implements MyICategoryservice {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(MyCategoryServiceImpl.class);

    @Autowired
    private CategoryMapper categoryMapper;

    public ServerResponse addCategory(String categoryName, Integer parentId) {

        if (parentId == null && StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("添加品类失败.....");
        }
        /**到了这里参数合法*/
        Category category = new Category();
        category.setName(categoryName);

        category.setParentId(parentId);

        category.setStatus(true);
        int insert = categoryMapper.insert(category);
        if (insert > 0) {
            return ServerResponse.createBySuccess("添加品类成功");
        }
        return ServerResponse.createByErrorMessage("添加品类失败.....");

    }

    /**
     * 2 更新的service方法
     */

    public ServerResponse updateCategoryName(String categoryName, Integer categoryId) {
        if (categoryId == null && StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("更新品类失败.....");
        }
        /**到了这里参数合法*/
        Category category = new Category();
        category.setName(categoryName);
/**根据分类的id更新分类的名字*/
        category.setId(categoryId);
        /**通过id有选择的更新，值更新这个id下面的name*/
        int i = categoryMapper.updateByPrimaryKeySelective(category);
        if (i > 0) {
            /**不要忘记了return*/
            return ServerResponse.createBySuccess("更新品类成功.....");

        }
        return ServerResponse.createByErrorMessage("封信品类失败...");


    }

    /**
     * 3 查询当前的子节点，不递归
     */
    public ServerResponse<List<Category>> getChildrenParelelCategoryId(Integer categoryId) {
        List<Category> categories = categoryMapper.selectCategoryIdChildren(categoryId);
        if (CollectionUtils.isEmpty(categories)) {
            logger.info("出现错误，找不到.....");

        }
        return ServerResponse.createBySuccess(categories);

    }
}
