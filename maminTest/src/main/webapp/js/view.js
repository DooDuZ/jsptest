


$.ajax({    //페이지 접속시 개별글 내용 출력 김장군
	url:"/mamin/board/boardCRUD",
	type:"get",
	data:{"type":2},
	success:function(re){
		let json = JSON.parse(re)
		console.log(json)
		document.querySelector('.b_no').innerHTML = json.b_no;
		document.querySelector('.b_title').innerHTML = json.b_title;
		document.querySelector('.b_content').innerHTML = json.b_content;
		document.querySelector('.m_id').innerHTML = json.m_id;
		if( json.b_file !== null ){	// null , undefined , 0 , false
				let filelink = '<a href="/mamin/board/filedown?b_file='+json.b_file+'">'+json.b_file+'</a>'
				// ' ' : 전체 문자열 처리
				// " " : 전체 문자열내 문자열 구분  
				document.querySelector('.b_file').innerHTML = filelink;
			}
		if(json.btnaction){//세션 아이디와 게시글 아이디가 일치하면
		////삭제,수정 버튼 활성화
			document.querySelector(".btnbox").innerHTML=`<button onclick="boardDelete(${json.b_no})">삭제</button><button><a href="../view/boardupdate.jsp">수정</a></button>`
		}
	}
	
	
	
})

/////글 삭제 함수 김장군
function boardDelete(b_no){
	$.ajax({
		url:"/mamin/board/boardCRUD",
		type:"delete",
		data:{"b_no":b_no},
		success: function(re){
			
			if(re==="true"){
				alert("삭제되었습니다")
				location.href="list.jsp"
			}else{alert("삭제실패")}
			
		}
		
	})
	
}






