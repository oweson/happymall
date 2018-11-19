package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CategoryMapper;
import com.mmall.dao.UserMapper;
import com.mmall.pojo.Category;
import com.mmall.service.ICategoryService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService {


    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 1 查找分类，分页
     */
    @Override
    public ServerResponse<PageInfo> getCategory() {
        PageHelper.startPage(0, 2);
        List<Category> categories = categoryMapper.selectAllCategory();
        PageInfo pageInfo = new PageInfo(categories);
        return ServerResponse.createBySuccess(pageInfo);


    }

    /**
     * 1 对两个参数进行null判断；
     */
    public ServerResponse addCategory(String categoryName, Integer parentId) {
        if (parentId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("添加品类参数错误");
        }
        /**设置新的插入对象，接受数据进行更新*/
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        /**这个分类默认是可用的*/
        category.setStatus(true);
        /**插入数据并且返回受影响的行数；*/
        int rowCount = categoryMapper.insert(category);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("添加品类成功");
        }
        return ServerResponse.createByErrorMessage("添加品类失败");
    }

    /**
     * 2 更新分类信息；
     */
    public ServerResponse updateCategoryName(Integer categoryId, String categoryName) {
        if (categoryId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("更新品类参数错误");
        }
        Category category = new Category();
        /**只是更新着两个字段*/
        category.setId(categoryId);
        category.setName(categoryName);

        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        /**判断返回受影响的行数；是否成功；*/
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("更新品类名字成功");
        }
        return ServerResponse.createByErrorMessage("更新品类名字失败");
    }

    /**
     * 3 查找当前分类的同一级别的分类；
     * 传入0就是所有的一级分类，二级分类放入parentid就是一级分类的id；
     */
    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId) {
        /**对传入的id进行查询，并且进行Null判断；*/
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        if (CollectionUtils.isEmpty(categoryList)) {
            logger.info("未找到当前分类的子分类");
        }
        return ServerResponse.createBySuccess(categoryList);
    }


    /**
     * 4 递归查询本节点的id及孩子节点的id
     *
     * @param categoryId
     * @return
     */
    public ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId) {
        Set<Category> categorySet = Sets.newHashSet();
        /**调用下面的递归算法；*/
        findChildCategory(categorySet, categoryId);


        List<Integer> categoryIdList = Lists.newArrayList();
        /**递归算法中是直接查询的没有进行null判断*/
        if (categoryId != null) {
            //如果不为null，进行便利，添加到集合；
            for (Category categoryItem : categorySet) {
                categoryIdList.add(categoryItem.getId());
            }
        }
        return ServerResponse.createBySuccess(categoryIdList);
    }

//todo 蒙逼

    /**
     * 递归算法,算出子节点
     */
    private Set<Category> findChildCategory(Set<Category> categorySet, Integer categoryId) {
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category != null) {
            categorySet.add(category);
        }
        //查找子节点,递归算法一定要有一个退出的条件
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        for (Category categoryItem : categoryList) {
            findChildCategory(categorySet, categoryItem.getId());
        }
        return categorySet;
    }

    @Override
    public ServerResponse delete(Integer cid) {
        int i = categoryMapper.deleteByPrimaryKey(cid);
        if (i > 0) {
            return ServerResponse.createBySuccess("删除成功");

        }
        return ServerResponse.createByErrorMessage("删除失败");
    }
}