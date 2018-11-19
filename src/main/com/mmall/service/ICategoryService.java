package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;

import java.util.List;


public interface ICategoryService {
    /**
     * 1 所有的分类
     */
    ServerResponse<PageInfo> getCategory();

    /**
     * 2 添加分类，注意parentid是区分一级还是二级分类
     */
    ServerResponse addCategory(String categoryName, Integer parentId);

    /**
     * 3 更新分类
     */
    ServerResponse updateCategoryName(Integer categoryId, String categoryName);

    //todo
    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);

    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);
    /** 6 删除分类*/
    ServerResponse delete(Integer cid);

}
