package com.menu.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.menu.dto.FileDTO;
import com.menu.dto.MenuDTO;
import com.menu.dto.Search;
import com.menu.service.MenuService;

@RestController
public class MenuController {
	private static final Logger Logger = LoggerFactory.getLogger(UploadController.class);
	@Autowired
	private MenuService mservice;
	
	@RequestMapping("/")
	public ModelAndView selectAllFromMenu() {
		ModelAndView mav = new ModelAndView("/listMenu");
		List<MenuDTO> menuList = mservice.menuList();
		mav.addObject("list", menuList);
		return mav;
	}
	
	@RequestMapping("/AddMenuWindow")
	public ModelAndView AddMenuWindow() {
		ModelAndView mav = new ModelAndView("/addMenu");
		List<MenuDTO> upMenuList = mservice.upMenuList();
		mav.addObject("list", upMenuList);
		return mav;
	}
	
	@PostMapping("/addMenu")
	public ModelAndView addMenu(MenuDTO mdto) {
		ModelAndView mav = new ModelAndView("/listMenu");
		mservice.addMenu(mdto);
		List<MenuDTO> menuList = mservice.menuList();
		mav.addObject("list", menuList);
		return mav;
	}
	
	@RequestMapping("/detailMenu")
	public ModelAndView goDetailMenu(MenuDTO mdto) {
		ModelAndView mav = new ModelAndView("/detailMenu");	
		mav.addObject("depth", mservice.menuDepth(mdto));
		mav.addObject("detail", mservice.detailMenu(mdto));
		return mav;
	}
	
	@RequestMapping("/updateMenuWindow")
	public ModelAndView updateMenuWindow(MenuDTO mdto) {
		ModelAndView mav = new ModelAndView("/updateMenu");	
		mav.addObject("depth", mservice.menuDepth(mdto));
		mav.addObject("detail", mservice.detailMenu(mdto));
		return mav;
	}
	
	@RequestMapping("/updateMenuFile")
	public ModelAndView updateMenu(MenuDTO mdto) {
		ModelAndView mav = new ModelAndView("/detailMenu");
		mservice.menuUpdate(mdto);
		mav.addObject("result", "success");
		mav.addObject("depth", mservice.menuDepth(mdto));
		mav.addObject("detail", mservice.detailMenu(mdto));
		return mav;
	}
	
	@RequestMapping("/deleteMenu")
	public ResponseEntity<String> deleteMenu(@RequestBody MenuDTO mdto) {
		int result = mservice.deleteMenu(mdto);
		if( result == 1 ) {
			return new ResponseEntity<> ("success", HttpStatus.OK);
		}
		return new ResponseEntity<> ("error", HttpStatus.OK);
	}
	
	@RequestMapping("/nowFileShow{menu_id}")
	public ResponseEntity<ArrayList<FileDTO>> fileSelectt(@PathVariable("menu_id")int menu_id){
		return new ResponseEntity<> (mservice.fileSelect(menu_id), HttpStatus.OK);
	}	
}
