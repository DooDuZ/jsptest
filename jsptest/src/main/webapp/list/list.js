/**
 * 
 */
 
 function getlist(){
	let listtable = document.querySelector('.listtable');
	
	$.ajax({
		url : '/jsptest/list',
		success : (result)=>{
			let list = JSON.parse(result);
			
			html = `<tr><th>글번호</th><th>글제목</th><th>작성자</th><th>작성일</th><th>조회수</th></tr>`
			
			for(let i = 0 ; i<list.length ; i++){
				html += `<tr><td>${list[i].cNo}</td>
						<td onclick="view(${list[i].cNo})">${list[i].title}</td>
						<td>${list[i].writer}</td>
						<td>${list[i].date}</td>
						<td>${list[i].view}</td>
						</tr>`
			}			
			listtable.innerHTML = html;
		}
	})	
}

getlist();

function view(cNo){
	$.ajax({
		url : '/jsptest/list',
		data : {"cNo" : cNo},
		type : 'POST',
		success : (result) => {
			if(result==='true'){
				location.href = '../view/view.html';
			}else{
				alert('[관리자 문의] 서버 오류');
				location.reload();
			}
			
		}		
	})	
}