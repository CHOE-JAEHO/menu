package com.menu.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.menu.dao.MenuDAO;
import com.menu.dto.FileDTO;
import com.menu.dto.MenuDTO;
import com.menu.service.MenuService;

@RestController
@RequestMapping("/upload")
public class UploadController {
	private static final Logger Logger = LoggerFactory.getLogger(UploadController.class);
@Autowired
MenuDAO mdao;
@Autowired
private MenuService mservice;
	@RequestMapping( "/uploadFormAction" )
	public ResponseEntity< ArrayList< FileDTO > > Upload( MultipartFile[] uploadFile, MenuDTO mdto ){		
		ArrayList< FileDTO > Flist = new ArrayList<>();
		String uploadFolder = "/home/cjh/git/menu/menu/src/main/resources/static/uploadfiles";
		UUID uuid = UUID.randomUUID();
		mdto.setUuid( uuid.toString() );
		for( MultipartFile files : uploadFile ) {
			String uploadFolderPath = uuid.toString();
			File uploadPath = new File( uploadFolder, uploadFolderPath );
			if( uploadPath.exists() == false ) {
				uploadPath.mkdirs();
			}
			FileDTO fdto = new FileDTO();
			String uploadFileName = files.getOriginalFilename();
			fdto.setFile_nm( uploadFileName );
			try {
				File saveFile = new File( uploadPath, uploadFileName );
				files.transferTo( saveFile );
				Runtime.getRuntime().exec( "chmod -R 777 " + uploadPath );
				fdto.setFile_path( uploadFolderPath );
				Flist.add( fdto );
			}catch( Exception e ) {
				e.getMessage();
			}
		}//파일 업로드 끝.
		return new ResponseEntity<>( Flist, HttpStatus.OK );
	}
	
	@RequestMapping("/fileDelete")
	public ResponseEntity<String> fileDelete( @RequestBody FileDTO fdto ) {
		String uploadFolder = "/home/cjh/git/menu/menu/src/main/resources/static/uploadfiles/";	
		String fileDeletePath = uploadFolder + fdto.getFile_path();
		String deleteFileName = fdto.getFile_nm();
		File deleteFile = new File( fileDeletePath, deleteFileName );
		int result = 0;
		if( deleteFile.exists() && deleteFile.delete() ) {
			result = mservice.fileDelete( fdto );
		}
		if( result == 1 ) {
			return new ResponseEntity<>( "success", HttpStatus.OK );
		}
		return new ResponseEntity<>( "error", HttpStatus.INTERNAL_SERVER_ERROR );
	}
	
	@RequestMapping( "/fileUpdate" )
	public ResponseEntity< ArrayList< FileDTO > > update( MultipartFile[] uploadFile
												   , MenuDTO mdto
												   , @RequestParam("uuid")String uuid				
												   ){
		ArrayList< FileDTO > Flist = new ArrayList<>();
		String uploadFolder = "/home/cjh/git/menu/menu/src/main/resources/static/uploadfiles";
		for( MultipartFile files : uploadFile ) {
			String uploadFolderPath = uuid.toString();
			File uploadPath = new File( uploadFolder, uploadFolderPath );
			if( uploadPath.exists() == false ) {
				 uploadPath.mkdirs();
			}
			FileDTO fdto = new FileDTO();
			String uploadFileName = files.getOriginalFilename();
			fdto.setFile_nm( uploadFileName );
			try {
				File saveFile = new File(uploadPath, uploadFileName);
				files.transferTo( saveFile );
				Runtime.getRuntime().exec( "chmod -R 777 " + uploadPath );
				fdto.setFile_path( uploadFolderPath );
				Flist.add( fdto );
			}catch( Exception e ) {
				e.getMessage();
			}
		}//파일 UPDATE 끝.
		return new ResponseEntity<>( Flist, HttpStatus.OK );
	}
	
	@RequestMapping( "/folderDelete" )
	public  ResponseEntity<String> folderDelete( @RequestBody FileDTO fdto ) {
		//폴터 찾아 안에 파일 지우고, 폴더 지우기. uuid 필요함.
		String uploadFolder = "/home/cjh/git/menu/menu/src/main/resources/static/uploadfiles/";
		String deletePath = uploadFolder+fdto.getFile_path();
		File deletefolder = new File( deletePath );
		try {
			while( deletefolder.exists() ) {
				File[] folderList = deletefolder.listFiles();
				for(int j = 0; j < folderList.length; j++) {
					folderList[ j ].delete();
					System.out.println( "파일 삭제 성공" );
				}
				if( folderList.length == 0 && deletefolder.isDirectory() ) {
					deletefolder.delete();
					System.out.println( "메뉴 폴더 삭제 성공" );
				}
			}
			return new ResponseEntity< String >( "success", HttpStatus.OK );
		}catch( NoSuchElementException e ) {
			e.getMessage();
		}
		return new ResponseEntity< String >( "error", HttpStatus.INTERNAL_SERVER_ERROR );
	}	
}
