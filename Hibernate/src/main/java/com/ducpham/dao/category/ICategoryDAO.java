package com.ducpham.dao.category;

import com.ducpham.dao.BaseDAO;
import com.ducpham.dto.search.CategorySearch;
import com.ducpham.model.Category;

public interface ICategoryDAO extends BaseDAO<Category, CategorySearch> {
    boolean existsByCode(String code);

    boolean existsByCodeAndNotId(Long id, String code);

}
