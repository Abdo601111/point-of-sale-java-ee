package com.css.pos.service.common;

import java.util.List;


public interface CommonService <T,E>{
	public List<T> list(E id);
	public int save(T type);
	public int delete(E id);
}
