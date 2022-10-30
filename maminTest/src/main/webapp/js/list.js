
getBoardList()//페이지 접속시 1번 실행

function getBoardList(){  //전체 게시물 출력 함수
	
	$.ajax({
		url:"/mamin/board/boardCRUD",
		data:{"type":1},
		type:"get",
		success:function(re){
			let json = JSON.parse(re)
			console.log(json)
			let html=""
			for(let i = 0; i<json.length;i++){
				let b = json[i]
				html+=`<tr>
							<td>${b.b_no}</td>
							<td onclick="viewload(${b.b_no})">${b.b_title}</td>
							<td>${b.m_id}</td>
							<td>${b.b_date}</td>
							
					  </tr>`
					  	
			}
			
			document.querySelector(".btable").innerHTML+=html
		}
		
		
	})
	
	
}

function viewload(b_no){ //세션에 게시글 번호 저장 함수
	$.ajax({
		url:"/mamin/board/viewload",
		type:"get",
		data:{"b_no":b_no},
		success:function(re){
			location.href = "../view/view.jsp"
			
		}
		
	})
	
	
}