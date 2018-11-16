package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;

import java.util.List;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/8/25 0025 10:38
 */
public interface MyICategoryservice {
    ServerResponse addCategory(String categoryName, Integer parentId);

    ServerResponse updateCategoryName(String categoryName, Integer categoryId);

    public ServerResponse<List<Category>> getChildrenParelelCategoryId(Integer categoryId);
}
