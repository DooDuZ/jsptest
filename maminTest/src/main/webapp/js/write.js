

/* 썸머노트 실행 */
$(document).ready(function() {
  //$('#summernote').summernote( {설정객체} );
  $('#summernote').summernote( {
	
		plasceholder : '내용 입력 해주세요' , 
		maxHeight : null , 
		minHeight : 300
	});
  
}); 

  	
  	
  
  
  






function bwrite(){///글등록 함수 김장군
	let form = document.querySelector("#write")
	let formdata = new FormData(form)
		
	$.ajax({
		url:"/mamin/board/boardCRUD",
		data : formdata , 							
		type : 'POST' , 
		contentType : false , 									
		processData : false , 
		success : function( re ){
			if(re==="true"){
				alert("글 등록 성공")
				location.href="list.jsp"
				
			}else{
				alert("글등록 실패")
			}
		}
			
	})

	
}