package com.css.pos.dal.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;
import com.css.pos.dto.category.ProdAttrDto;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.css.pos.common.util.POSConstants;
import com.css.pos.domain.Category;
import com.css.pos.domain.LookupData;
import com.css.pos.domain.Product;
import com.css.pos.domain.ProductAttribute;
import com.css.pos.dto.category.ProductDto;
@Repository
public class ProductDalImpl implements ProductDal {
	@Autowired
	private SessionFactory sessionfactory;
	public SessionFactory getSessionfactory() {
		return sessionfactory;
	}

	public void setSessionfactory(SessionFactory sessionfactory) {
		this.sessionfactory = sessionfactory;
	}
	@Override
	@Transactional
	public int save(ProductDto pro) {
		try {
			Product entity = new Product();
			entity.setId(pro.getId());
			entity.setName(pro.getName());
			entity.setImage(pro.getLogo());
			entity.setCode(pro.getCode());
			entity.setReference(pro.getReference());
			entity.setPricebuy(pro.getPricebuy());
			entity.setPricesell(pro.getPricesell());

			entity.setIsscale(pro.isIsscale()?1:0);
			entity.setIscom(0);
			Category cat= new Category();
			cat.setId(pro.getCategoryId());
			entity.setCategoryBean(cat);
			sessionfactory.getCurrentSession().saveOrUpdate(entity);
			//create list of product attributes and save it
			
			if(pro.getAttributes()!= null && !pro.getAttributes().isEmpty()) {
				List<ProductAttribute> paEntities = new ArrayList<>();
				for(ProdAttrDto pa: pro.getAttributes()) {
					sessionfactory.getCurrentSession().saveOrUpdate(new ProductAttribute(pa.getId(), 1, pa.getValue(),
							new LookupData(pa.getAttributeId()), entity));
				}
			}else {
				//delete all existing att. for that prdo. if any
			}
			
			
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	@Transactional
	public int delete(String id) {
		try {
			Product entity = new Product();
			entity.setId(id);
			sessionfactory.getCurrentSession().delete(entity);
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public ProductDto find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDto> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<ProductDto> list(String catId) {
		List<ProductDto> prods = null;
		try {
			Criteria criteria = sessionfactory.getCurrentSession().createCriteria(Product.class)
			.add(Restrictions.eq("categoryBean.id", catId));
			List<Product> entities = criteria.list();
			if(entities != null && !entities.isEmpty()) {
				ProductDto product = null;
				for (Product p : entities) {
					product = new ProductDto();
					product.setId(p.getId());
					product.setCategoryId(catId);
					product.setCode(p.getCode());
					product.setIsscale(p.getIsscale()==1);
					product.setLogo(p.getImage());
					product.setName(p.getName());
					product.setPricebuy(p.getPricebuy());
					product.setPricesell(p.getPricesell());
					product.setReference(p.getReference());
					
					if(prods == null) prods = new ArrayList<>();
					if(p.getAttributes() != null && !p.getAttributes().isEmpty()) {
						if(product.getAttributes() == null)
							product.setAttributes(new ArrayList<>());
						for(ProductAttribute pa:p.getAttributes()) 
							product.getAttributes().add(new ProdAttrDto(pa.getId(), product.getId(), pa.getLookupData().getId(), pa.getValue()));
					}
					prods.add(product);
				} 
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return prods;
	}

	@Override
	@Transactional
	public List<ProductDto> getAvailableProducts(Integer searchBy, String key, String company) {
		List<ProductDto> prods = null;
		try {
			Criteria criteria = sessionfactory.getCurrentSession().createCriteria(Product.class);
//			criteria.add(Restrictions.eq("categoryBean.company.id", company));
			if(searchBy.equals(POSConstants.SEARCH_BY_CODE))
				criteria.add(Restrictions.like("code", key+"%"));
			if(searchBy.equals(POSConstants.SEARCH_BY_NAME))
				criteria.add(Restrictions.like("name", key+"%"));
			
			List<Product> entities = criteria.list();
			if(entities != null && !entities.isEmpty()) {
				ProductDto product = null;
				for (Product p : entities) {
					product = new ProductDto();
					product.setId(p.getId());
					product.setCategoryId(p.getCategoryBean().getId());
					product.setCode(p.getCode());
					product.setIsscale(p.getIsscale()==1);
					product.setLogo(p.getImage());
					product.setName(p.getName());
					product.setPricebuy(p.getPricebuy());
					product.setPricesell(p.getPricesell());
					product.setReference(p.getReference());
					
					if(prods == null) prods = new ArrayList<>();
					if(p.getAttributes() != null && !p.getAttributes().isEmpty()) {
						if(product.getAttributes() == null)
							product.setAttributes(new ArrayList<>());
						for(ProductAttribute pa:p.getAttributes()) 
							product.getAttributes().add(new ProdAttrDto(pa.getId(), product.getId(), pa.getLookupData().getId(), pa.getValue()));
					}
					prods.add(product);
				} 
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return prods;
	}

	@Override
	@Transactional
	public List<ProductDto> search4productAdvanced(Object[][] criterias) {
		//https://docs.jboss.org/hibernate/orm/3.5/reference/en/html/querycriteria.html
		List<ProductDto> prods = null;
		try {
			Criteria criteria = sessionfactory.getCurrentSession().createCriteria(Product.class);
			 
			boolean nameEx = false, codeEx = false, priceEx = false;
			for(int index =0;index<3;index++) {  // {name, code, priceSell}
				if(criterias[index][0] !=null) {
					switch (index) {
					case 0:  //name
						nameEx = true;
						break;
					case 1:  //code
						codeEx = true;
						break;
					case 2:  //priceSell
						priceEx = true;
						break;
					}
				}
			}
			
			if(!nameEx && !codeEx && priceEx) {
				switch((Byte)criterias[2][1]) {
				case 1:
					criteria.add(Restrictions.gt("pricesell",criterias[2][0]));
					break;
				case -1:
					criteria.add(Restrictions.lt("pricesell",criterias[2][0]));
					break;
				case 0:
					criteria.add(Restrictions.eq("pricesell",criterias[2][0]));
					break;
				}
			}else if(!nameEx && codeEx && priceEx) {
				if(new Integer(criterias[1][1].toString()) == 0) { //AND
					criteria.add(Restrictions.like("code",criterias[1][0]));
					
					switch((Byte)criterias[2][1]) {
					case 1:
						criteria.add(Restrictions.gt("pricesell",criterias[2][0]));
						break;
					case -1:
						criteria.add(Restrictions.lt("pricesell",criterias[2][0]));
						break;
					case 0:
						criteria.add(Restrictions.eq("pricesell",criterias[2][0]));
						break;
					}
					
				}else { //OR
					switch((Byte)criterias[2][1]) {
					case 1:
						criteria.add(Restrictions.or(Restrictions.like("code",criterias[1][0]),
								Restrictions.gt("pricesell",criterias[2][0])));
						break;
					case -1:
						criteria.add(Restrictions.or(Restrictions.like("code",criterias[1][0]),
								Restrictions.lt("pricesell",criterias[2][0])));
						break;
					case 0:
						criteria.add(Restrictions.or(Restrictions.like("code",criterias[1][0]),
								Restrictions.eq("pricesell",criterias[2][0])));
						break;
					}	
				}
			}else if(nameEx && codeEx && priceEx) {
				if(new Integer(criterias[0][1].toString()) == 0
				&& new Integer(criterias[1][1].toString()) == 0		) { //AND- AND
					criteria.add(Restrictions.like("code",criterias[1][0]));
					criteria.add(Restrictions.like("name",criterias[0][0]));
					switch((Byte)criterias[2][1]) {
					case 1:
						criteria.add(Restrictions.gt("pricesell",criterias[2][0]));
						break;
					case -1:
						criteria.add(Restrictions.lt("pricesell",criterias[2][0]));
						break;
					case 0:
						criteria.add(Restrictions.eq("pricesell",criterias[2][0]));
						break;
					}
					
				}else if(new Integer(criterias[0][1].toString()) == 0
						&& new Integer(criterias[1][1].toString()) == 1		){ //AND-OR
					criteria.add(Restrictions.like("name",criterias[0][0]));
					switch((Byte)criterias[2][1]) {
					case 1:
						criteria.add(Restrictions.or(Restrictions.like("code",criterias[1][0]),
								Restrictions.gt("pricesell",criterias[2][0])));
						break;
					case -1:
						criteria.add(Restrictions.or(Restrictions.like("code",criterias[1][0]),
								Restrictions.lt("pricesell",criterias[2][0])));
						break;
					case 0:
						criteria.add(Restrictions.or(Restrictions.like("code",criterias[1][0]),
								Restrictions.eq("pricesell",criterias[2][0])));
						break;
					}	
					
				}else if(new Integer(criterias[0][1].toString()) == 1
						&& new Integer(criterias[1][1].toString()) == 0		){//OR-AND
					criteria.add(Restrictions.or(Restrictions.like("code",criterias[1][0]),
							Restrictions.like("name",criterias[0][0])));;
					switch((Byte)criterias[2][1]) {
					case 1:
						criteria.add(Restrictions.gt("pricesell",criterias[2][0]));
						break;
					case -1:
						criteria.add(Restrictions.lt("pricesell",criterias[2][0]));
						break;
					case 0:
						criteria.add(Restrictions.eq("pricesell",criterias[2][0]));
						break;
					}	
				}else if(new Integer(criterias[0][1].toString()) == 1
						&& new Integer(criterias[1][1].toString()) == 1		) { //OR-OR
					
					switch((Byte)criterias[2][1]) {
					case 1:
						criteria.add(Restrictions.or(Restrictions.like("code",criterias[1][0]),
									 Restrictions.like("name",criterias[0][0]),
									 Restrictions.gt("pricesell",criterias[2][0])));
						break;
					case -1:
						criteria.add(Restrictions.or(Restrictions.like("code",criterias[1][0]),
								 Restrictions.like("name",criterias[0][0]),
								 Restrictions.lt("pricesell",criterias[2][0])));
						break;
					case 0:
						criteria.add(Restrictions.eq("pricesell",criterias[2][0]));criteria.add(Restrictions.or(Restrictions.like("code",criterias[1][0]),
								 Restrictions.like("name",criterias[0][0]),
								 Restrictions.eq("pricesell",criterias[2][0])));
						break;
					}	
				}
			}else if(!nameEx && codeEx && !priceEx) {
				criteria.add(Restrictions.gt("code",criterias[1][0]));
				
			}
			/**
			 * Remaining 4 cases 
			 * N	C	p
			 * t	f	t
			 * t	t	f
			 */
			List<Product> entities = criteria.list();
			if(entities != null && !entities.isEmpty()) {
				ProductDto product = null;
				for (Product p : entities) {
					product = new ProductDto();
					product.setId(p.getId());
					product.setCategoryId(p.getCategoryBean().getId());
					product.setCode(p.getCode());
					product.setIsscale(p.getIsscale()==1);
					product.setLogo(p.getImage());
					product.setName(p.getName());
					product.setPricebuy(p.getPricebuy());
					product.setPricesell(p.getPricesell());
					product.setReference(p.getReference());
					
					if(prods == null) prods = new ArrayList<>();
					if(p.getAttributes() != null && !p.getAttributes().isEmpty()) {
						if(product.getAttributes() == null)
							product.setAttributes(new ArrayList<>());
						for(ProductAttribute pa:p.getAttributes()) 
							product.getAttributes().add(new ProdAttrDto(pa.getId(), product.getId(), pa.getLookupData().getId(), pa.getValue()));
					}
					prods.add(product);
				} 
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return prods;
	}
}
