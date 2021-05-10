package com.menu.dao;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.menu.dto.MenuDTO;

@Repository
public class MenuDAOImpl implements MenuDAO{

	@Override
	public List<MenuDTO> menuList(SqlSessionTemplate session) {
		System.out.println("12");
		return session.selectList("MenuMapper.menuList");
	}
	
	
}
