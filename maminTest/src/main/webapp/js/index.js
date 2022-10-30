/**
 * 
 */
 
 let m_id = document.querySelector('.H_idbox').innerHTML;
 
 function checkRoom(){
	if(m_id === 'null'){
		alert('로그인 후 이용해주세요.');
		location.href = '/mamin/view/login.jsp';
		return;
	}	
	$.ajax({
		url : '/mamin/room/playerCounter',
		success : (result)=>{
			let slot_no = Number(result);
			if(slot_no>=1 && slot_no<=4){
				enterRoom();
			}else if(slot_no === -1){
				alert('이미 참여중인 아이디에요!');
				location.reload();
			}else{
				alert('빈 자리가 없어요.');
				location.reload();
			}
		}
	})
}

function enterRoom(){	
	location.href = '/mamin/view/room.jsp';	
}