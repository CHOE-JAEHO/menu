package com.menu.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.menu.dao.MenuDAO;
import com.menu.dto.FileDTO;
import com.menu.dto.MenuDTO;

@Service
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	private MenuDAO mdao;
	
	@Override
	public List<MenuDTO> menuList() {
		return mdao.menuList();
	}
	
	public List<MenuDTO> upMenuList(){
		return mdao.upMenuList();
	}
	
	@Transactional
	public void addMenu( MenuDTO mdto ) {
		if( mdto.getFileList() == null ) {
			UUID uuid = UUID.randomUUID();
			mdto.setUuid( uuid.toString() );
		}
		mdao.getMyleft( mdto );
		mdao.updateRgt( mdto );
		mdao.updateLft( mdto );		
		mdao.addMenu( mdto );
		try {
			mdto.getFileList()
			    .forEach(files -> {
			    	files.setMenu_id( mdto.getMenu_id() );
			    	mdao.addFile( files );
			    });
		}catch( NullPointerException e ) {
			e.getMessage();
		}
	}
	public MenuDTO detailMenu( MenuDTO mdto ) {
		return mdao.detailMenu( mdto );
	}
	
	public int deleteMenu( MenuDTO mdto ) {
		mdao.allFileDelete( mdto );
		MenuDTO m2 = new MenuDTO();
		m2 = mdao.getLftRgt( mdto );
		if(m2.getRgt() - m2.getLft() == 1) {
			mdao.getNodes( mdto );
			int result = mdao.deleteMenu( mdto );
			mdao.updateRgtDel( mdto );
			mdao.updateLftDel( mdto );
			return result;
		}else {	
			mdao.getNodes( mdto );
			int result = mdao.deleteParentMenu( mdto );
			mdao.updateParentRgtLftDel( mdto );	
			mdao.updateParentRgtDel( mdto );
			mdao.updateParentLftDel( mdto );
			return result;
		}
	}
	public ArrayList< MenuDTO > menuDepth( MenuDTO mdto ) {
		return mdao.menuDepth( mdto );
	}
	
	@Override
	public ArrayList< FileDTO > fileSelect( int menu_id ) {
		return mdao.fileSelect( menu_id );
	}
	
	public int fileDelete ( FileDTO fdto ) {
		return mdao.fileDelete( fdto );
	}
	
	public void menuUpdate ( MenuDTO mdto ) {
		mdao.menuUpdate( mdto );
		try {
			mdto.getFileList()
			    .forEach(files -> {
			    	files.setMenu_id( mdto.getMenu_id() );
			    	mdao.addFile( files );
			    });
		}catch( NullPointerException e ) {
			e.getMessage();
		}
	}
}
