/**
 * 파일 불러오기, 수정, 삭제
 */
 var goMenuList = function(){
	window.location.href = "http://localhost:8080/";
}

$(document).ready(function(){	
	var menu_id = $( "#menu_id" ).val();
	getfile();
	function getfile(){
	$.getJSON( "/nowFileShow" + encodeURI( menu_id ),
		function( data ){
		showUploadResult( data )
		})
	};
//첨부파일 목록, 버튼 동적요소 만들기.
	function showUploadResult( uploadResultArr ){
		if( !uploadResultArr || uploadResultArr.length == 0 ){ return; }
		var nowFileUL = $( ".nowFile ul" );
		var uploadHTML = $( "#showHTML" );
		var str = "";
		var strH = "";
		//현재 등록된 파일 목록.
		$( uploadResultArr ).each(function( i, obj ){
			var fileCallPath = obj.file_path + "/" + obj.file_nm;
			str += '<li style="cursor:pointer" data-file_path="'+ obj.file_path + '"'
			str += ' data-file_nm="' + obj.file_nm + '">'
			str += '<div>'
			str += '<a href= "../static/uploadfiles/' + fileCallPath + '"download>' + obj.file_nm + '</a>'
			str += '</div></li>';
		 })
		 //HTML파일 IFRAME 만들기
		$( uploadResultArr ).each(function( i, obj ){
			var ext = obj.file_nm;
			if( ext.substring( ext.length - 4 ) == "html" ){
			var	fileCallPath = obj.file_path + "/" + obj.file_nm;
				strH += "<iframe name ='iframe' id='iframe' style = 'width : 900px' src = '../static/uploadfiles/" + fileCallPath + "'>";
				strH += "</iframe>"
			}
		})
		nowFileUL.append( str ); 
		uploadHTML.append( strH );
	}//showresult 끝
	setTimeout( getCssJs, 100 )
	function getCssJs(){
	$.getJSON( "/nowFileShow" + encodeURI( menu_id ),
		function( data ){
		showUploadCssJs( data )
		})
	};
	function showUploadCssJs( uploadResultArr ){
		if( !uploadResultArr || uploadResultArr.length == 0 ){ return; }
			//css 적용 버튼
		$( uploadResultArr ).each(function( i, obj ){
			var ext = obj.file_nm;
			if( ext.substring( ext.length - 3 ) == "css" ){
				var	fileCallPath = obj.file_path + "/" + obj.file_nm;
				var $head = $( "#iframe" ).contents().find( "head" );
				
				$head.append($("<link/>", {
					rel : "stylesheet",
					href : "/static/uploadfiles/"+fileCallPath,
					type: "text/css"
				}))
			}
		})
		//js 적용버튼
		$( uploadResultArr ).each(function( i, obj ){
			var ext = obj.file_nm;
			if( ext.substring( ext.length - 2 ) == "js" ){
				var	fileCallPath = obj.file_path + "/" + obj.file_nm;
				var $head = $( "#iframe" ).contents().find( "head" );
				$head.append( $( "<script/>", {
					src : "/static/uploadfiles/"+fileCallPath,
					type: "text/javascript"
				}))
			}
		})
	}
})
//메뉴 삭제 및 파일 폴더 삭제
var menuDelete = function( uuid, menu_id ){
	var yn = confirm( "정말삭제 하시겠습니까?" ) 
	if( yn ){
	//폴터,파일삭제
		$.ajax({ 
			type : 'DELETE',
			url : '/upload/folderDelete',
			data : JSON.stringify({ file_path : uuid }),
			contentType : "application/json;charset=utf-8",
			success : 
			function(){//메뉴 DB삭제
				$.ajax({ 
					type : 'DELETE',
					url : '/deleteMenu',
					data : JSON.stringify( { menu_id : menu_id }, { uuid : uuid } ),
					contentType : "application/json;charset=utf-8",
					success :
					function(){
						alert( "성공" )
					},
					error :
					function(){
						alert( "실패" )
					}
				});
				alert( "삭제됨" );
				window.location.href = "http://localhost:8080/";},
			error :
			function(){
				alert( "실패" )
				window.location.reload();
			}			
		})
	}	
}