/**
 * 
 */

 
function addContent(){
	let title = document.querySelector('.title').value;
	let content = document.querySelector('.content').value;
	let writer = document.querySelector('.writer').value;
	let password = document.querySelector('.password').value;
 	
 	let object = {
		'title' : title,
		'content' : content,
		'writer' : writer,
		'password' : password
	} 
 	
 	
	$.ajax({
		url : '/jsptest/write',
		data : object,
		success : (result)=>{
			if(result==='true'){
				alert('글 등록 성공');
				location.href = '../list/list.html';
			}else{
				alert('글 등록 실패');
			}
		}		
	})
}


/*

function getlist(){
	let listtable = document.querySelector('.listtable');
	$.ajax({
		url : 
		
	})
	
}
*/