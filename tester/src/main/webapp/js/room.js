

// 20221030[지웅] userslot 출력
let m_id = document.querySelector('.H_idbox').innerHTML;
// 화면 공유를 위한 소캣 생성 [ 채팅 소켓과 사실상 동일할 수 있음 ]
let websocket =  null;
if(m_id !== 'null'){
	websocket = new WebSocket('ws://localhost:8080/tester/room/RoomSocket/'+m_id);
	// 2에서 구현된 기능을 클라이언트 소켓에 대입
	websocket.onopen = (e) => {onopen(e)};
	websocket.onclose = (e) => {onclose(e)};
	websocket.onmessage = (e) => {onmessage(e)};
	websocket.onerror = (e)=>{onerror(e)};
}else{
	alert('로그인 후 이용해주세요.');
	location.href = '/tester/view/login.jsp';
}

//20221029[지웅] websocket 기본 function에 대입할 기능 구현
function onopen(e){}
function onerror(e){ alert(e); }
function onclose(e){ alert('저 갑니다!'); }
function onmessage(obj){
	let parsing = JSON.parse(obj.data);
	console.log(parsing);
	console.log(parsing.data);
	if(parsing.function_name=='addplayer'){	// 20221031 낮 언젠가... 지웅 추가
		addPlayer(parsing.data);
	}else if(parsing.function_name=='exit'){ // 20221031 23:49 지웅 추가
		alert('남은 자리가 없어요ㅠㅠ')
		exit();
	}
}

function send(object){
	websocket.send(JSON.stringify(object));
}

// 20221029[지웅] 플레이어 입장 / 슬롯에 표시
function addPlayer(array){
	let blankslot =[false,false,false,false];
	// OnOpen 에서 입장한 유저 정보와 해당 유저의 slotNo response 받은 후 맞는 위치에 데이터 입력 
	for(let i = 0 ; i<array.length ; i++){
		let object = array[i];
		blankslot[object.s_no-1] = true;
		let win_rate;
		if(object.total!==0){
			win_rate = (Number(object.wins) / Number(object.total)) * 100 + '%';
		}else{
			win_rate = '전적 없음';
		}
		document.querySelector(`.r_slot${object.s_no}`).innerHTML = 
				`<td class="r_p_img">
					<img src="/tester/img/member/${object.m_img}">
				</td>
				<td class="r_name_box">${object.m_nick}</td>
				<td class="r_winrate">${win_rate}</td>
				<td class="r_ready_box">Ready</td>`;
	}
	for(let i = 0 ; i<blankslot.length ; i++){
		if(!blankslot[i]){
			document.querySelector(`.r_slot${i+1}`).innerHTML = 
				`<td class="r_p_img"></td>
				<td class="r_name_box"></td>
				<td class="r_winrate"></td>
				<td class="r_ready_box">입장대기</td>`;
		}
	}
}

 
 // 20221029[지웅] chatdisplay test용 function 
function enterKey(){
	if(window.event.keyCode == 13){
		sendMessage();
	}
}
 function sendMessage(){
	document.querySelector('.chatDisplay').innerHTML += `<div>${document.querySelector('.c_inputbox').value}</div>`;
	document.querySelector('.c_inputbox').value = '';
	document.querySelector('.chatDisplay').scrollTop = document.querySelector('.chatDisplay').scrollHeight;	
}


// 20221031
function exit(){
	location.href = 'index.jsp';
}




