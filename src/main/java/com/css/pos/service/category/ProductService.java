package com.css.pos.service.category;

import java.util.List;

import com.css.pos.dto.category.ProductDto;
import com.css.pos.service.common.CommonService;

public interface ProductService extends CommonService<ProductDto, String> {
	public ProductDto find(String id);
	public List<ProductDto> list();
	public List<ProductDto> getAvailableProducts(Integer searchByCode, String barcode, String selectedBranch);
	public List<ProductDto> search4productAdvanced(ProductDto selectedProduct, byte priceCriteria, byte nameCriteria,
			byte codeCriteria);
}
