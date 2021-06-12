package com.css.pos.dal.common;

import java.util.List;

public interface CommonDal<T,E> {
	public List<T> list(E id);
	public int save(T type);
	public int delete(E id);
}
