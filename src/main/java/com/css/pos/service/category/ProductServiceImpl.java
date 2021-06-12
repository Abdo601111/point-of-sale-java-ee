package com.css.pos.service.category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.pos.common.util.POSConstants;
import com.css.pos.dal.category.ProductDal;
import com.css.pos.dto.category.CategoryDto;
import com.css.pos.dto.category.ProdAttrDto;
import com.css.pos.dto.category.ProductDto;
@Component
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDal dal;
	

	public ProductDal getDal() {
		return dal;
	}

	public void setDal(ProductDal dal) {
		this.dal = dal;
	}

	@Override
	public int save(ProductDto prod) {
		if(prod.getId() == null) {
			prod.setId(UUID.randomUUID().toString());
		}
		if(prod.getAttributes() != null && !prod.getAttributes().isEmpty()){
			for(int index = 0; index <prod.getAttributes().size(); index++) {
				if(prod.getAttributes().get(index).getId()== null) {
					prod.getAttributes().get(index).setId(UUID.randomUUID().toString());
				}
			}
		}
		return dal.save(prod);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return dal.delete(id);
	}

	@Override
	public ProductDto find(String id) {
		// TODO Auto-generated method stub
		return dal.find(id);
	}

	@Override
	public List<ProductDto> list() {
		// TODO Auto-generated method stub
		return dal.list();
	}

	@Override
	public List<ProductDto> list(String company) {
		// TODO Auto-generated method stub
		List<ProductDto> result=null;
		try {
			result = dal.list(company);
			if(result != null)
			for(ProductDto p:result){
				if(p.getLogo() == null) {
					p.setLogo(POSConstants.DEFAULT_PROD_LOGO_PATH);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<ProductDto> getAvailableProducts(Integer searchByCode, String barcode, String company) {
		// TODO Auto-generated method stub
		return dal.getAvailableProducts(searchByCode, barcode, company);
	}

	@Override
	public List<ProductDto> search4productAdvanced(ProductDto selectedProduct, byte priceCriteria, byte nameCriteria,
			byte codeCriteria) {
		try {
			Object[][] criterias = new Object[3][2];
			if(selectedProduct.getName() != null && !"".equals(selectedProduct.getName().trim())) {
				criterias[0][0] = selectedProduct.getName().trim()+"%";
				criterias[0][1] = nameCriteria;
			}else {
				criterias[0][0] = null;
				criterias[0][1] = null;
			}
			if(selectedProduct.getCode() != null && !"".equals(selectedProduct.getCode().trim())) {
				criterias[1][0] = selectedProduct.getCode().trim()+"%";
				criterias[1][1] = codeCriteria;
			}else {
				criterias[1][0] = null;
				criterias[1][1] = null;
			}
			if(selectedProduct.getPricesell()!= new Short("0")) {
				criterias[2][0] = selectedProduct.getPricesell();
				criterias[2][1] = priceCriteria;
			}else {
				criterias[2][0] = null;
				criterias[2][1] = null;
			}
			return dal.search4productAdvanced(criterias);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
