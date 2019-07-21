package com.mmall.dao;

import com.mmall.pojo.Category;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    /**
     * 根据一级父分类的id查询它下面的子分类集合
     */
    List<Category> selectCategoryChildrenByParentId(Integer parentId);

    List<Category> selectCategoryIdChildren(Integer parentId);

    List<Category> selectAllCategory();
}