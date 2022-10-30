/**
 * 
 */
 
 function throwdices(){
	let diceNum = Math.floor(Math.random()*6)+1;
	let diceNum2 = Math.floor(Math.random()*6)+1;
	
	displaydice(diceNum, diceNum2);

	let object = {
		casting : `displaydice(${diceNum}, ${diceNum2})`
	}
	send(object);

}

 let websocket =  new WebSocket('ws://localhost:8080/maminTest/test/SocketTest');
 
 // new WebSocket('ws://localhost:8080/Homework/test/SocketTest/');
 websocket.onopen = (e) =>{};
 websocket.onclose = (e) =>{};
 websocket.send = (e)=>{ send(e) };
 websocket.onmessage = (e) =>{}; 
 
 function onopen(){}
 function onclose(){}
 function send(object){ 
	console.log(object);
	console.log(JSON.stringify(object));
	websocket.send(JSON.stringify(object));	
 }
 function onmessage(){}
 
 function displaydice(diceNum, diceNum2){
	let dice1 = document.querySelector('.dice1');
	let dice2 = document.querySelector('.dice2');
	
	dice1.innerHTML = diceNum;
	dice2.innerHTML = diceNum2;
}

