let count_ready;
let r_sno = document.querySelector('.r_sno');
let player_list = []; // gameBoard에서 사용할 전역번수 추가
let m_nick = ''// 11/03 장군 현재 플레이어 닉네임 저장 변수

 getM_nick();
	
function getM_nick(){ /// 11/03 장군 현재 플레이어 닉네임 호출 함수
	
	$.ajax({
		url:"/mamin/member/mypage",
		success:function(re){
			let json = JSON.parse(re);
			
			 m_nick = json.m_nick
			 
		}
	})	
	
	
}	
	
	

function readyCounter(){
	let count_r = 0; // ready 수 체크
	for(let i = 1 ; i<=4 ; i++){
		if(document.querySelector(`.r_ready_box${i}`).innerHTML === 'Ready'){
			count_r++;
		}
	}
	count_ready = count_r;
}
// 20221030[지웅] userslot 출력
let m_id = document.querySelector('.H_idbox').innerHTML;
// 화면 공유를 위한 소캣 생성 [ 채팅 소켓과 사실상 동일할 수 있음 ]
let websocket = null;
if (m_id !== 'null') {
	websocket = new WebSocket('ws://localhost:8080/mamin/room/RoomSocket/' + m_id);
	// 2에서 구현된 기능을 클라이언트 소켓에 대입
	websocket.onopen = (e) => { onopen(e) };
	websocket.onclose = (e) => { onclose(e) };
	websocket.onmessage = (e) => {onmessage(e) };
	websocket.onerror = (e) => { onerror(e) };
} else {
	alert('로그인 후 이용해주세요.');
	location.href = '/mamin/view/login.jsp';
}

//20221029[지웅] websocket 기본 function에 대입할 기능 구현
function onopen(e) { console.log(e)}
function onerror(e) { alert(e); }
function onclose(e) { 
	readyCounter();
	alert('저 갑니다!'); }
function onmessage(obj) {
	let parsing = JSON.parse(obj.data);
	console.log(parsing);
	if(parsing.function_name=='addplayer'){	// 20221031 낮 언젠가... 지웅 추가
		addPlayer(parsing.data);
	}else if(parsing.function_name=='exit'){ // 20221031 23:49 지웅 추가
		alert('남은 자리가 없어요ㅠㅠ')
		exit();
	}else if(parsing.function_name=='ready'){	// 1101 지웅 추가
		ready(parsing.data);
	}else if(parsing.function_name=='start_game'){ // 1101 지웅 추가
		start_game();
	}else if(parsing.function_name=='duplicated'){		// 1102 지웅 추가
		duplicated();
	}else if(parsing.type =="open"){//플레이어 입장
		document.querySelector(".chatDisplay").innerHTML+=`<div>${parsing.m_nick}님이 들어왔습니다.</div>`
	}else if(parsing.type =="close"){//플레이어 퇴장
		if(document.querySelector('.stateGame').innerHTML=="0"){// 11/06 장군 추가 게임 진행중이 아닐때
			document.querySelector(".chatDisplay").innerHTML+=`<div>${parsing.m_nick}님이 나갔습니다.</div>`
		}else{// 11/06 장군 추가 게임 진행중일 때 퇴장시
			invalidGameover(parsing.m_nick);
		}
		
	}else if(parsing.type =="message"){// 채팅 메세지 받을때
		document.querySelector(".chatDisplay").innerHTML+=`<div>${parsing.m_nick}: ${parsing.content}</div>`	
	}else if(parsing.function_name=='display_dice'){	// 1102 지웅 추가
		display_dice(parsing.data1, parsing.data2);
	}else if(parsing.function_name == "levelUp_land"){	// 1103 지웅 추가 //1104 비아 수정
		levelUp_land(parsing.data, parsing.data2, parsing.data3);
	}else if(parsing.object_name == 'nation'){			// 1104 비아 추가
		updateNationInfo(parsing.index, parsing.p_no, parsing.n_level)
		setHouse(parsing.index, parsing.n_level) // 1105 수현 추가
	}else if(parsing.object_name == 'player'){			// 1104 비아 추가
		updatePlayerInfo(parsing.index, parsing.cash)
	}else if(parsing.function_name == "updateNationLevel"){	// 1103 지웅 추가 //1104 비아 수정
		updateNationLevel(parsing.n_index, parsing.p_index)
	}else if(parsing.function_name == 'updatePlayerPosition'){	//비아 추가
		updatePlayerPosition(parsing.playerNo, parsing.n_no)
	}else if(parsing.Info_update == 'player'){	// 1105 수현 추가
		takeMoneyInfo(parsing.giveIndex, parsing.takeIndex , parsing.give, parsing.take)
	}else if(parsing.function_name == 'end_turn'){
		console.log('test');
		end_turn();
	}
	/*
	if(document.querySelector('.stateGame').innerHTML != ''){
		
	}
	*/	
}
function send(object) {
	websocket.send(JSON.stringify(object));
}

// 20221029[지웅] 플레이어 입장 / 슬롯에 표시
function addPlayer(array){
	readyCounter(); // 서버에서 OnClose 발생 시 유저 퇴장 -> js onclose작동 안하는듯... 유저 변화시마다 ready체크
	
	let blankslot =[false,false,false,false];
	// OnOpen 에서 입장한 유저 정보와 해당 유저의 slotNo response 받은 후 맞는 위치에 데이터 입력 
	
	for(let i = 0 ; i<array.length ; i++){
		let object = array[i];
		
		player_list[i] = object;
		
		if(object.m_id === m_id){
			r_sno.innerHTML = object.s_no;
		}	
		
		blankslot[object.s_no-1] = true;
		let win_rate;
		if(object.total!==0){
			win_rate =Math.round( (Number(object.wins) / Number(object.total)) * 100 )+ '%';//1106 장군 추가 승률 소수점 반올림
		}else{
			win_rate = '전적 없음';
		}
		
		// 지웅 수정
			//ready 상태 표시 위해 변수 생성 후 innerHTML로 대입
			
		let slotHTML = `<td class="r_p_img">
					<img src="/mamin/img/member/${object.m_img}">
				</td>
				<td class="r_name_box">${object.m_nick}</td>
				<td class="r_winrate">${win_rate}</td>`;
		// 가져온 ready 변수 상태에 따라 다른 값 대입
		if(object.ready===true){
			slotHTML += `<td class="r_ready_box${object.s_no}" onclick="readybtn(${object.s_no})">Ready</td>`
		}else{
			slotHTML += `<td class="r_ready_box${object.s_no}" onclick="readybtn(${object.s_no})">wait</td>`
		}
		
		document.querySelector(`.r_slot${object.s_no}`).innerHTML = slotHTML;

	}
	for(let i = 0 ; i<blankslot.length ; i++){
		if(!blankslot[i]){
			document.querySelector(`.r_slot${i+1}`).innerHTML = 
				`<td class="r_p_img"></td>
				<td class="r_name_box"></td>
				<td class="r_winrate"></td>
				<td class="r_ready_box${i+1}">wait</td>`;
		}
	}	
}


// 20221029[지웅] chatdisplay test용 function 
function enterKey() {
	if (window.event.keyCode == 13) {
		sendMessage();
	}
}
function sendMessage() {// 11/03 장군 채팅 메세지 보내기
	let msg={
		m_nick :m_nick,
		content:document.querySelector('.c_inputbox').value,
		type : 'message'	// 1104 지웅 추가 -> onmessage에 else있는 경우 오류 발생, type 추가 후 else if로 제어
	}
	send(msg);	
	document.querySelector('.c_inputbox').value = '';
	document.querySelector('.chatDisplay').scrollTop = document.querySelector('.chatDisplay').scrollHeight;
}


// 20221031
function exit() {
	location.href = 'index.jsp';
}


function readybtn( s_no ){
	if(s_no == r_sno.innerHTML){
		let object = {
			function_name : 'ready',
			data : s_no,
			roomdata : `ready${s_no}` 
		}
		send(object);
	}else{
		alert('자신의 Ready만 누를 수 있습니다.');
	}	
}

function ready(s_no){
	if(document.querySelector(`.r_ready_box${s_no}`).innerHTML != 'Ready'){
		document.querySelector(`.r_ready_box${s_no}`).innerHTML = 'Ready';
	}else{
		document.querySelector(`.r_ready_box${s_no}`).innerHTML = 'wait';
	}
	readyCounter();	
}

function open_game(){
	if(r_sno.innerHTML==1){
		let object = {
			function_name : 'start_game'
		}
		if(count_ready==player_list.length && count_ready>=2){ // 1106 장군수정 레디한 플레이어수가 2명이상이고 현재 접속한 플레이어가 모두 레디했으면
			send(object);
		}else if(player_list.length==1 ){
			alert('플레이어가 두명 이상이어야 게임을 시작할수있어요!')//1106 장군 수정
		}else{alert('아직 준비하지 않은 플레이어가 있습니다.')}//1106 장군수정
	}else{
		alert('1번 플레이어만 누를 수 있어요!');
	}
}

function start_game(){
	document.querySelector('.stateGame').innerHTML = 1;
	$('#pagebox').load('gameBoard.jsp');
}


//1102 23:19 지웅 추가

function duplicated(){
	alert('이미 참여중인 아이디입니다.');
	location.href = 'index.jsp';
	
	
}
	

function invalidGameover(m_nick){// 11/06 장군 게임중 한명이 나갔을때 실행 함수
		let m_nickOut = m_nick; //나간 플레이어의 닉네임
		let playerArray = JSON.stringify(player_list)//플레이어리스트 JSON문자열로 반환
		
		$.ajax({
			url:"/mamin/game/GameControll",
			traditional : true,
			data:{
				"m_nickOut":m_nickOut,
				"playerArray":playerArray
			},
			success:function(re){
					
			alert("게임중 나간 플레이어가 있어 승리로 기록됩니다.(3초후 홈으로 이동)")
				setTimeout(function(){location.href="index.jsp"},3000)
			}
		})
	
	
}
