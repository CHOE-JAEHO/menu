package com.menu.service;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.menu.dao.MenuDAO;
import com.menu.dto.MenuDTO;


@Service
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	private MenuDAO mdao;
	
	@Autowired
	private SqlSessionTemplate session;

	@Override
	public List<MenuDTO> menuList() {
		
		return mdao.menuList(session);
	}

}
