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
	
	private int d1_id;
	private int d2_id;
	private int d3_id;
	private int d4_id;
	private int d5_id;
	private int d6_id;
	
	private String d1_nm;
	private String d2_nm;
	private String d3_nm;
	private String d4_nm;
	private String d5_nm;
	private String d6_nm;
	}