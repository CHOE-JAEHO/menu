$( document ).ready( function(){
	var menu_id = $( "#menu_id" ).val();
	var uuid = $( "#uuid" ).val();
	getfile();
	function getfile(){
		$.getJSON( "/nowFileShow" + encodeURI( menu_id ),
				function( data ){
				showNowResult( data )
		})
	};
	var strArr =[ 0 ];	
	function showNowResult( uploadResultArr ){
		if( !uploadResultArr || uploadResultArr.length == 0 ){ return; }
		var nowFileUL = $( ".nowFile ul" );
		var str = "";
		$( uploadResultArr ).each( function( i, obj ){
			strArr[ i ] = obj.file_nm;
			var fileCallPath = obj.file_path + "/" + obj.file_nm;
			str += '<li style="cursor:pointer" data-file_path="'+ obj.file_path + '"'
			str += ' data-file_nm="' + obj.file_nm + '">'
			str += '<div>'
			str += '<a href= "static/uploadfiles/' + fileCallPath + '" download>' + obj.file_nm + '</a>'
			str += '<input type = "button" value = "삭제"  onclick = "fileDelete(\'' + obj.file_nm + '\',\'' + obj.file_path + '\')">'
			str += '</div></li>';
		 })
		nowFileUL.append( str ); 
	}//showresult 끝
	
	var formObj = $( "form[role='updateMenuFile']" );//role이라는 값이 form이면 form태그가 저장되는 곳.	
	$("input[type = 'submit']").on( "click", function( e ){
		e.preventDefault();	
		var str="";
		$( ".uploadResult ul li" ).each( function( i, obj ){
			var jobj = $( obj );
			str += "<input type='text' name= 'fileList[" + i + "].file_nm' value = '" + jobj.data( "file_nm" ) + "'>";
			str += "<input type='text' name= 'fileList[" + i + "].file_path' value = '" + jobj.data( "file_path" ) + "'>";
		})
		var menu_nm = $( "#menu_nm" ).val();
		if( menu_nm == '' || null || undefined || 0 || NaN ){
			alert( "이름을 입력 해주세요." )
		}else{
			formObj.append( str ).submit();//form 태그의 name에 있는 것을 싹 다 넘겨라 submit을 누르면.	
		}
	})
			
	$( "input[type = 'file']" ).change(function( e ){//change이벤트로 input의 file대신 new ForData로 파일창을 띄움.
		var formData = new FormData();
		var inputFile = $( "input[name = 'uploadFile']" );
		var files = inputFile[ 0 ].files;//올린 파일의 정보를 files에 담음.
			
		for(var i = 0; i < files.length; i++){
			formData.append( "uploadFile", files[ i ] );//form에 append한 데이터를 전송함.
		}
		formData.append( "uuid", uuid )
		$.ajax({
			url : "/upload/fileUpdate",
			data : formData,
			datatype : "json",
			//processData와 contentType은 파일업로드시 false가 되어야함.
			processData : false,
			contentType : false,
			type : 'POST',
			success :
			function( data ){
				alert( "업로드됨" );
				showUploadResult( data );
			},
	 		error :
	 		function(){
				window.location.reload();
			}
		})
	})	
	function showUploadResult( uploadResultArr ){
		if( !uploadResultArr || uploadResultArr.length == 0 ){
			return;
		}
		var uploadUL = $( ".uploadResult ul" );
		var str = "";
		$( uploadResultArr ).each( function( i, obj ){//index i 있어야 정상적으로 동작.
			if( fileDupleCheck( strArr, obj.file_nm )){
			str += "<li data-file_path = '" + obj.file_path + "'";
			str += "data-uuid = '" + obj.uuid + "' data-file_nm='" + obj.file_nm + "'"
			str += "><div>";
			str += "<span>" + obj.file_nm + "</span>";
			str += "</div></li>";
			}
		})
		uploadUL.append( str );
	}
})//ready 끝

var fileDupleCheck= function(strArr, file_nm){
	for(var i = 0; i < strArr.length; i++){
		if(strArr[i] == file_nm){
			return false;
		}
	}
	return true;
}

// file삭제 함수
var fileDelete = function(file_nm, file_path){
	var yn = confirm("정말삭제하시겠습니까?") 
	if( yn ){
		// 서버(db) 반영.		   
		//ajax사용/
		$.ajax({//json형태의 form데이터를 가지고,url로 가서 동작함. 
			type : 'DELETE',
			url : '/upload/fileDelete',
			data : JSON.stringify({file_nm : file_nm
				    			, file_path : file_path}),
			contentType : "application/json;charset=utf-8",
			success :
			function(){
				alert("삭제됨");
				window.location.reload();
			},
 	 		error :
 	 		function(){
				alert("실패")
				window.location.reload();
			}
		})
	}
}			
