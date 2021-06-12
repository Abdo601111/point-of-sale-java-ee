package com.css.pos.dal.category;

import java.util.List;
import java.util.Map;

import com.css.pos.dal.common.CommonDal;
import com.css.pos.dto.category.ProductDto;

public interface ProductDal extends CommonDal<ProductDto, String>{
	public ProductDto find(String id);
	public List<ProductDto> list();
	public List<ProductDto> getAvailableProducts(Integer searchByCode, String barcode, String company);
	public List<ProductDto> search4productAdvanced(Object[][] criterias);
}
