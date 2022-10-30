function login(){
	let signin_id = document.querySelector("#signin_id").value
	let signin_pw = document.querySelector("#signin_pw").value
	
	$.ajax({
		url : '/mamin/member/login',
		data : {"m_id":signin_id, "m_password":signin_pw},
		success : function(re){
			if(re === '0'){	//아이디가 없다
				alert('존재하지 않는 아이디입니다.')
				location.reload()
			}else if(re === '1'){	//로그인 성공
				alert('로그인 성공')
				location.href = '/mamin/view/index.jsp'
			}else if(re === '2'){	//비밀번호 틀림
				alert('패스워드가 다릅니다.')
				location.reload()
			}
		}
	})
	
}

