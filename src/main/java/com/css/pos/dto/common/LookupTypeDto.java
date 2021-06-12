/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.css.pos.dto.common;

/**
 *
 * @author Sayed
 */
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class LookupTypeDto {
     @XmlElement(name="code")
     private String code ="";
     
     @XmlElement(name="name_ar")
     private String nameAr="";
     
     @XmlElement(name="name_en")
     private String nameEn="";

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNameAr() {
		return nameAr;
	}

	public void setNameAr(String nameAr) {
		this.nameAr = nameAr;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	@Override
	public String toString() {
		return nameEn +" | "+nameAr;
	}
     


}