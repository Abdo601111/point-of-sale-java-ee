package com.css.pos.dal.category;

import java.util.List;

import com.css.pos.dal.common.CommonDal;
import com.css.pos.dto.category.CategoryDto;

public interface CategoryDal extends CommonDal<CategoryDto, String>{
	public CategoryDto find(String id);
	public List<CategoryDto> list();
}
