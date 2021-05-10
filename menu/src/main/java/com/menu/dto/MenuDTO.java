package com.menu.dto;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Alias("menu")
@Getter
@Setter
@ToString
public class MenuDTO {
	String menu_id;
	String menu_nm;
	int sort_how;
	String up_menu_id;
	
	
	public MenuDTO() {}

	public MenuDTO(String menu_id, String menu_nm, int sort_how, String up_menu_id) {
		super();
		this.menu_id = menu_id;
		this.menu_nm = menu_nm;
		this.sort_how = sort_how;
		this.up_menu_id = up_menu_id;
	}
	
	public MenuDTO(String menu_id) {
		super();
		this.menu_id=menu_id;
	}
	
	
}
