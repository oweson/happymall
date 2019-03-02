package com.mmall.pojo;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Integer id;
    /**
     * 1 父分类的id
     */
    private Integer parentId;

    private String name;

    private Boolean status;

    private Integer sortOrder;

    private Date createTime;

    private Date updateTime;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Category category = (Category) o;

        return !(id != null ? !id.equals(category.id) : category.id != null);

    }

    //todo 这里我们仅仅比较了id，我们规定只要id相同就是相同的；、、
    //todo 工作中保证判断的因子相同否则莫名的错误；两个方法都重写；
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}