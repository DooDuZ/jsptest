

//비아 - 0. 아이콘 [fontawesome]
let sicon = '<i class="fas fa-check-circle"></i>'
let bicon = '<i class="fas fa-ban"></i>'


//비아 - 1. col3 div 모두 가져오기
let col3 = document.querySelectorAll(".col3")	//class가 col3이면 모두 호출 [배열 저장]



//비아 -  -------------- 아이디 --------------
function mevent1(){			//아이디를 입력[keyup]하면 이벤트 발생 = 함수 실행
	let mid = document.querySelector("#id").value	//1. 입력받은 아이디 호출
	let midj = /^[a-z0-9]{5,20}$/					// 소문자/숫자 5~20글자 패턴
	if( midj.test(mid) ){	//정규표현식이 동일하면
		$.ajax({			//아이디 중복체크 [비동기식]
			url : "/mamin/member/signup",
			data : {"type":1, "mid" : mid},
			success : function(re) {
				if(re==='true'){ col3[0].innerHTML = bicon+" 사용 중인 아이디" }
				else { col3[0].innerHTML = sicon }
			}
		})
	}else{ col3[0].innerHTML = bicon+' 소문자/숫자 조합 5~20글자' }	//정규표현식이 다르면	
	
}

//비아 -  -------------- 비밀번호 --------------
function mevent2(){
	let mpassword = document.querySelector("#pw").value
	let mpasswordj = /^[a-zA-Z0-9]{8,20}$/
	
	if( mpasswordj.test(mpassword) ){ 
		col3[1].innerHTML = sicon
		mevent3()		//맞춰놓고 위에 것만 수정해도 체크가 안되므로 한번 더 체크
	}
	else{ col3[1].innerHTML = bicon+" 영대소문자/숫자 조합 8~20글자" }
}

//비아 -  -------------- 비밀번호 확인 --------------
function mevent3(){
	let mpassword = document.querySelector("#pw").value
	let mpasswordconfirm = document.querySelector("#pwconfirm").value
	let mpasswordj = /^[a-zA-Z0-9]{8,20}$/
	
	if( !mpasswordj.test(mpasswordconfirm) ){ col3[1].innerHTML = bicon+' 영대소문자/숫자 조합 8~20글자' }	//정규표현식이 다르면
	else if( mpasswordconfirm != mpassword ){ col3[1].innerHTML = bicon+' 비밀번호가 서로 다릅니다.' }	//두 비밀번호가 다르면
	else{ 		//정규표현식 맞고 두 비밀번호 맞으면
		col3[1].innerHTML = sicon
		mevent2()		//맞춰놓고 위에 것만 수정해도 체크가 안되므로 한번 더 체크
	}	
}

//비아 -  -------------- 닉네임 --------------
function mevent4(){
	let m_nick = document.querySelector("#m_nick").value
	if(m_nick!=''){ col3[2].innerHTML = sicon }
	else { col3[2].innerHTML = bicon+' 닉네임을 입력해주세요.' }
}

//비아 -  -------------- 이메일 --------------
function mevent5(){
	let memail = document.querySelector("#email").value
	let memailj = /^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\.[a-zA-Z-9-]+$/
	if(memailj.test(memail)){ 
		$.ajax({
			url : "/mamin/member/signup",
			data : {"type":2, "memail" : memail},
			success : function(re) {
				if(re === 'true') col3[3].innerHTML = bicon+' 사용 중인 이메일입니다.'
				else col3[3].innerHTML = sicon
			}
		})
	}
	else { col3[3].innerHTML = bicon+' 이메일 형식으로 입력해주세요.' }
}

//비아 -  -------------- 자기소개 --------------
function mevent6(){
	let profile = document.querySelector("#profile").value
	if(profile!=''){ col3[4].innerHTML = sicon }
	else { col3[4].innerHTML = bicon+' 자기소개를 입력해주세요.' }
}

//비아 -  ----------- 캐릭터 선택 버튼 -----------
let characterbtns = document.querySelectorAll('.characterbtn')
let cimg = document.querySelector('.cimg')
var prev = null;
for (var i = 0; i < characterbtns.length; i++) {
    characterbtns[i].addEventListener('change', function() {
        (prev) ? console.log(prev.value): null;
        if (this !== prev) {
            prev = this;
        }
        if(this.value == '아가양') cimg.src = '../img/member/'+this.value+'.jpg'
        else cimg.src = '../img/member/'+this.value+'.png'
    });
}

//비아 -  -------------- form 전송 --------------
function formsubmit(){
	for(let i=0; i<col3.length; i++){
		if(col3[i].innerHTML !== sicon){
			alert('입력이 안된 정보가 있습니다.')
			return false
		}
	}
	
	//2. 이용약관 체크박스 검토
	//tag객체명.checked : 체크가 되어있으면 true / 아니면 false
	if(!document.querySelector('#confirm1').checked){	//체크가 안되어 있으면
		alert('이용약관에 동의해주세요.')
		return false
	}
	if(!document.querySelector('#confirm2').checked) {	//체크가 안되어 있으면
		alert(' 개인정보 수집 동의해주세요.')
		return false
	}
	
	//signupform이라는 class를 가지고 있는 tag 호출
	document.querySelector('.signupform').submit()	//해당 form 전송 //폼객체.submit()
}

