package com.menu.service;


import java.util.ArrayList;
import java.util.List;

import com.menu.dto.FileDTO;
import com.menu.dto.MenuDTO;
import com.menu.dto.Search;

public interface MenuService {
	
	public List<MenuDTO> menuList();
	public List<MenuDTO> upMenuList();
	public void addMenu(MenuDTO mdto);
	public MenuDTO detailMenu(MenuDTO mdto);
	
	public int deleteMenu(MenuDTO mdto);
	
	public ArrayList<MenuDTO> menuDepth(MenuDTO mdto);
	
	
	public ArrayList<FileDTO> fileSelect(int menu_id); 
	public int fileDelete(FileDTO fdto);
	public void menuUpdate(MenuDTO mdto);
}
