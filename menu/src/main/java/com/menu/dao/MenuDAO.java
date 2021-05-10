package com.menu.dao;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.menu.dto.MenuDTO;

public interface MenuDAO {
	
	public abstract List<MenuDTO> menuList(SqlSessionTemplate sesstion);
	
}
