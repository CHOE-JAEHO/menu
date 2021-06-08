package com.menu.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.menu.dto.FileDTO;
import com.menu.dto.MenuDTO;
import com.menu.dto.Search;
@Mapper
public interface MenuDAO {
	
	public List<MenuDTO> menuList();
	public List<MenuDTO> upMenuList();
	public MenuDTO detailMenu(MenuDTO mdto);
	public  ArrayList<MenuDTO> menuDepth(MenuDTO mdto);
	
	public MenuDTO getMyleft(MenuDTO mdto);
	public void updateRgt(MenuDTO mdto);
	public void updateLft(MenuDTO mdto);
	public void addMenu(MenuDTO mdto);
	
	//--파일삭제 (끝 노드)
	public MenuDTO getLftRgt(MenuDTO mdto);
	public void allFileDelete(MenuDTO mdto);
	public MenuDTO getNodes(MenuDTO mdto);
	public int deleteMenu(MenuDTO mdto);
	public void updateRgtDel(MenuDTO mdto);
	public void updateLftDel(MenuDTO mdto);
	
	//--파일삭제 (자식 있는 부모노드)
	public int deleteParentMenu(MenuDTO mdto);
	public void updateParentRgtLftDel(MenuDTO mdto);
	public void updateParentRgtDel(MenuDTO mdto);
	public void updateParentLftDel(MenuDTO mdto);
	
	public void addFile (FileDTO fdto);
	public ArrayList<FileDTO> fileSelect(int menu_id);
	public int fileDelete(FileDTO fdto);
	public int menuUpdate(MenuDTO mdto);
	
}
