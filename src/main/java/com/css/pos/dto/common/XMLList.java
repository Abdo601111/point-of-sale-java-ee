/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.css.pos.dto.common;
import java.util.List;
import javax.xml.bind.annotation.*;
/**
 *
 * @author pc
 */

@XmlRootElement(name="root")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLList {

    @XmlElementWrapper(name="lookup_lists")
    @XmlElement(name="lookup_list")
    private List<LookupTypeDto> lists;

	public List<LookupTypeDto> getLists() {
		return lists;
	}
    

}
