package com.menu.dto;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class MenuDTO {
	private int menu_id;
	private String menu_nm;
	private int up_menu_id;
	private int lft;
	private int rgt;
	private String up_menu_nm;
	private String uuid;
	private ArrayList<FileDTO> fileList;
	
	private String path;
	}