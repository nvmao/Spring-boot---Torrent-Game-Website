
let url = $('#contextPathHolder').attr('data-contextPath');
let stompClient


let player
let enemy
let playerScore=0
let enemyScore=0
let gameOver=false

init()

function init(){

    let curl = new URL(window.location.href);
    let searchParams = new URLSearchParams(curl.search);
    
    let playerName = searchParams.get("from")
    let enemyName = searchParams.get("to")

    connectToPlayGame(playerName)

    fetchPlayers(playerName,enemyName)

}

function randomQuestion() {

    let whatColorDom = document.getElementById("what-color")
    let colorBoxesDom = document.querySelectorAll(".color-box")


    let answerColor = randColor()
    whatColorDom.innerText =`RGB(${answerColor.r},${answerColor.g},${answerColor.b})`

    let answer = Math.floor(Math.random()*3)

    let clickBox1 = function () {
        clickRightColor(answerColor)
        removeClick(clickBox1,clickBox2)
    }
    let clickBox2 = function () {
        clickWrongColor()
        removeClick(clickBox1,clickBox2)
    }

    for(let i = 0;i<colorBoxesDom.length;i++){

        let rColor = randColor()

        if(i===answer){
            colorBoxesDom[i].setAttribute("style",
                `background: rgb(${answerColor.r},${answerColor.g},${answerColor.b}) ;`)
            colorBoxesDom[i].addEventListener("click",clickBox1)
        }
        else{
            colorBoxesDom[i].setAttribute("style",
                `background: rgb(${rColor.r},${rColor.g},${rColor.b}) ;`)
            colorBoxesDom[i].addEventListener("click",clickBox2)
        }


    }

}

function hideBox() {
    let colorBoxesDom = document.querySelectorAll(".color-box")
    colorBoxesDom.forEach(colorBox=>{
        colorBox.hidden=true
    })
}

function removeClick(clickBox1,clickBox2) {
    let colorBoxesDom = document.querySelectorAll(".color-box")
    colorBoxesDom.forEach(colorBox=>{
        colorBox.removeEventListener("click",clickBox1)
        colorBox.removeEventListener("click",clickBox2)
    })
}

function fetchEnemyScore(score) {
    enemyScore = score
    let enemyScoreDom = document.getElementById("enemy-score")
    enemyScoreDom.innerText=`${enemyScore}`
}

function clickWrongColor() {
    let playerScoreDom = document.getElementById("player-score")
    playerScore--
    playerScoreDom.innerText = `${playerScore}`
    sendScoreSocket()
    loadNextQuestion({r:0,g:0,b:0})
}

function clickRightColor(answerColor) {

    let playerScoreDom = document.getElementById("player-score")
    playerScore++
    playerScoreDom.innerText = `${playerScore}`
    sendScoreSocket()
    loadNextQuestion(answerColor)

}

function loadNextQuestion(color){
    let colorBoxesDom = document.querySelectorAll(".color-box")

    colorBoxesDom.forEach(colorBox=>{
        colorBox.setAttribute("style",
            `background: rgb(${color.r},${color.g},${color.b}) ;`)
        document.querySelector("body").setAttribute("style",`background: rgb(${color.r},${color.g},${color.b}) ;`)
    })

    setTimeout(()=>{
        randomQuestion()
    },1200)


}

let randColor = function getRandomColor() {
    let r = Math.floor(Math.random() * 255)
    let g = Math.floor(Math.random() * 255)
    let b = Math.floor(Math.random() * 255)

    return {r,g,b}
}

////////////////////////
function connectToPlayGame(userName) {
    let socket = new SockJS(url+'/playgame')
    stompClient = Stomp.over(socket)
    stompClient.connect({},function (frame) {
        console.log('connected to: '+frame)
        stompClient.subscribe('/topic/playgame/' + userName,function (response) {
            let data = JSON.parse(response.body)
            if(data.message=='ready'){
                enemy = data.player
                fetchEnemy(enemy)
                dynamicBackground()
            }
            if(data.message=='score'){
                fetchEnemyScore(data.score)
            }
            if(data.message=="time"){
                let time = data.score

                let timeDom =document.getElementById("time")
                    //game over
                    if(time<=0){
                        let winner = 'NO ONE'
                        if(playerScore>enemyScore){
                            winner = player.userName
                        }
                        else if(playerScore<enemyScore){
                            winner = enemy.userName
                        }

                        timeDom.innerText='Time Up => '+winner+' win'
                        removeClick()
                        hideBox()
                        whoWin()
                    }
                    else{
                        timeDom.innerText=time
                    }
            }

        })
    })
}


function dynamicBackground() {
    let enemyDom = document.querySelectorAll(".player-box")[1]
    let playerDom = document.querySelectorAll(".player-box")[0]

    let fac = new FastAverageColor();

    fac.getColorAsync(enemy.avatar)
        .then(function(color) {

            enemyDom.setAttribute("style",
                `;background: linear-gradient(145deg, #222222,${color.hex}) !important;`)


        }).catch(function(e) {
    });
    fac.getColorAsync(player.avatar)
        .then(function(color) {

            playerDom.setAttribute("style",
                `;background: linear-gradient(145deg, #222222,${color.hex}) !important;`)


        }).catch(function(e) {
    });
}

function fetchPlayers(playerName,enemyName) {
    let playerDom = document.querySelectorAll(".player-box")[0]

    $.get(url+'/socket/loginUser',(response)=>{

        if(response==''){
            window.location=url+"/login"
        }

        player = response

        let playerHtml = `<img src="${player.avatar}" class="ui avatar image">
                    <span style="font-size: 20px;color:white">${player.userName} (you)</span>
                    <h1 class="ui header center aligned" id="player-score"
                    style="font-size: 60px;margin-top: 0">0</h1>    
                        `

        playerDom.innerHTML = playerHtml

        setTimeout(()=>{
            sendReadySocket(enemyName)
        },2000)
    })

}

function fetchEnemy(enemy) {

    let enemyDom = document.querySelectorAll(".player-box")[1]

    let playerHtml = `<img src="${enemy.avatar}" class="ui avatar image">
                <span style="font-size: 20px;color:white">${enemy.userName} (enemy)</span>
                <h1 class="ui header center aligned" id="enemy-score"
                style="font-size: 60px;margin-top: 0">0</h1>    
                    `

    enemyDom.innerHTML = playerHtml


    randomQuestion()
    document.getElementById("board").hidden=false

}

function sendScoreSocket() {
    stompClient.send('/app/playgame/score/'+enemy.userName,{},JSON.stringify({
        player:player,
        score:playerScore,
        message:'score'
    }))
}

function sendReadySocket(enemyName) {
    stompClient.send('/app/playgame/ready/'+enemyName,{},JSON.stringify({
        player:player,
        score:0,
        message:'ready'
    }))
}



