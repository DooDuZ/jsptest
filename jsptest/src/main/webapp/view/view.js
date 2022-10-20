/**
 * 
 */
 
function getboard(){
	let section_content = document.querySelector('#section_content');
	$.ajax({
		url : '/jsptest/view',
		success : (result)=>{
			let board = JSON.parse(result);
			section_content.innerHTML = `<div>제목 : ${board.title} 조회수 : ${board.view}</div>
						<div>작성자 : ${board.writer} 작성일 : ${board.date}</div>
						<div>본문 : ${board.content}</div>`;
		}		
	})
	
}

getboard();
