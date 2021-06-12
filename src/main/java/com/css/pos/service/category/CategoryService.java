package com.css.pos.service.category;

import java.util.List;

import com.css.pos.dto.category.CategoryDto;
import com.css.pos.service.common.CommonService;

public interface CategoryService extends CommonService<CategoryDto, String>{
	public CategoryDto find(String id);
	public List<CategoryDto> list();
	
}
