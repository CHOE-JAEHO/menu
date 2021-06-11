/**
 * 
 */
$(document).ready(function(){
	var formObj = $("form[role='addMenu']");//role이라는 값이 form이면 form태그가 저장되는 곳.

	$("input[type = 'submit']").on("click", function(e){
		e.preventDefault();//submit 해제
		
		var str="";
		var str2="";
		$(".uploadResult ul li").each(function(i, obj){
			var jobj = $(obj);
			str += "<input type='text' name= 'fileList[" + i + "].file_nm' value = '" + jobj.data( "file_nm" ) + "'>";
			str += "<input type='text' name= 'fileList[" + i + "].file_path' value = '" + jobj.data( "file_path" ) + "'>";
			str2 = "<input type='text' name= 'uuid' class = 'uuid' value = '" + jobj.data( "file_path" ) + "'>";
		})
		
		var menu_nm = $( "#menu_nm" ).val();
		if( menu_nm == '' || null || undefined || 0 || NaN ){
			alert( "이름을 입력 해주세요." )
		}else{
			formObj.append( str ).append( str2 ).submit();//form 태그의 name에 있는 것을 싹 다 넘겨라 submit을 누르면.	
		}
	})
		
	$( "input[type = 'file']" ).change(function( e ){//change이벤트로 input의 file대신 new ForData로 파일창을 띄움.
		var formData = new FormData();
		var inputFile = $( "input[name = 'uploadFile']" );
		var files = inputFile[ 0 ].files;//올린 파일의 정보를 files에 담음.
		for(var i = 0; i < files.length; i++){
			formData.append( "uploadFile", files[ i ] );//form에 append한 데이터를 전송함.
		}
		var uuid = $( ".uuid" ).val();
		console.log( uuid )
		if( uuid ){
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
		}else{
			$.ajax({
				url : "/upload/uploadFormAction",
				data : formData,
				datatype : "json",
				//processData와 contentType은 파일업로드시 false가 되어야함.
				processData : false,
				contentType : false,
				type : 'POST',
				success : function( data ){
					alert( "업로드됨" );
					console.log( data );
					showUploadResult( data );
				},
				error : function(){
					window.location.href = "localhost:8080/goAddMenu/"
				}
			})
		}
	})	
	function showUploadResult( uploadResultArr ){
		if( !uploadResultArr || uploadResultArr.length == 0 ){
			return;
		}
		var uploadUL = $( ".uploadResult ul" );
		var str = "";
		$( uploadResultArr ).each(function( i, obj ){//index i 있어야 정상적으로 동작.
			str += "<li data-file_path = '" + obj.file_path + "'";
			str += "data-uuid = '" + obj.uuid + "' data-file_nm='" + obj.file_nm + "'"
			str += "><div>";
			str += "<span>" + obj.file_nm + "</span>";
			str += "<input type = 'hidden' class = 'uuid' value= " + obj.file_path + ">";
			str += "</div></li>";
		})
		uploadUL.append( str );
	}
	
})