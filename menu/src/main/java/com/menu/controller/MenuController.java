package com.menu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.menu.dto.MenuDTO;
import com.menu.service.MenuService;

@RestController
public class MenuController {

	@Autowired
	private MenuService mservice;
	
	@RequestMapping("/")
	public ModelAndView selectAllFromMenu() {
		ModelAndView mav = new ModelAndView("/test1");
		List<MenuDTO> menuList = mservice.menuList();
		System.out.println(menuList);
		mav.addObject("list",menuList);
		return mav;
	}
}
