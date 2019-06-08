package top.sun520.dao;

import com.mmall.dao.CategoryMapper;
import com.mmall.pojo.Category;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/4/27 0027 16:03
 */
public class CategoryMapperTest extends BaseClass {
    @Autowired
    CategoryMapper categoryMapper;

    @Test
    public void selectCategoryChildrenByParentId() {
        List<Category> categories = categoryMapper.selectCategoryChildrenByParentId(100001);
        for (Category category : categories) {
            System.out.println(categories);

        }

    }

    /**
     * 根据一级父分类的id查询它下面的子分类集合
     */
    @Test
    public void selectCategoryIdChildren() {
        List<Category> categories = categoryMapper.selectCategoryIdChildren(100001);
        for (Category category : categories) {
            System.out.println(categories);

        }

    }

    @Test
    public void selectAllCategory() {
        List<Category> categories = categoryMapper.selectAllCategory();
        for (Category category : categories) {
            System.out.println(categories);

        }
    }
}
