let url = $('#contextPathHolder').attr('data-contextPath');
let loginUser
let stompClient

function connectToChat(userName) {
    let socket = new SockJS(url+'/chat')
    stompClient = Stomp.over(socket)
    stompClient.connect({},function (frame) {
        console.log('connected to: '+frame)
        stompClient.subscribe('/topic/messages/' + userName,function (response) {
            let  data = JSON.parse(response.body)
            sendReceiveMessage(data.fromLogin,data.message)
        })
    })
}

$.get(url+'/socket/loginUser',(response)=>{
        loginUser = response
        connectToChat(loginUser.userName)
})

function sendMsgSocket(text,to) {
    stompClient.send('/app/chat/'+to,{},JSON.stringify({
        fromLogin: loginUser.userName,
        message:text
    }))
}

function  sendReceiveMessage(from,message) {

    let chatList = document.getElementById("chatList_"+from)

    let sendMessageHtml = `<div class="box-chat-response">
                <div class="ui comments">
                    <div class="comment">
                        <div class="content">
                            <a class="ui image avatar">
                                <img src="/uploads/img/cat.jpg">
                            </a>
                            <div class="metadata">
                                <span class="date">2 days ago</span>
                            </div>
                            <div class="text" style="background-color: grey;border-radius: 25px;padding: 8px">
                                ${message}
                            </div>

                        </div>
                    </div>
                </div>
            </div>`

    chatList.insertAdjacentHTML('beforeend',sendMessageHtml)
    scrollToBottom(chatList)
}

function sendMessage(to) {

    let messageInput = document.getElementById("text_"+to)
    let chatList = document.getElementById("chatList_"+to)
    let message = messageInput.value
    messageInput.value = ''

    sendMsgSocket(message,to)

    let sendMessageHtml = `<div class="box-chat-send">
                <div class="ui grid">
                    <div class="four wide column">
                    </div>
                    <div class="twelve wide column">
                        <div class="ui comments">
                            <div class="comment">
                                <div class="content">
                                    <div class="ui grid">
                                        <div class="eight wide column">
                                        </div>
                                        <div class="eight wide column">
                                            <div class="metadata">
                                                <span class="date">1 days ago</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="text" style="background-color: deepskyblue;border-radius: 25px;padding: 8px">
                                        ${message}
                                    </div>

                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>`
    chatList.insertAdjacentHTML('beforeend',sendMessageHtml)

    scrollToBottom(chatList)

}


function fetchAllUsers() {

    $.get(url+'socket/fetchAllUsers',(response)=>{

        let users = response
        let usersTemplateHtml=''
        let xPos = 5;
        users.forEach((user)=>{
            if(user.id != loginUser.id){
                usersTemplateHtml = usersTemplateHtml +`
                        <div class="item" onclick="openChatBox('${user.userName}','${user.avatar}',${xPos})" >
                            <img class="ui avatar image" src="${user.avatar}" style="margin-right: 0">
                            <div class="content" style="margin-left: 0;margin-right: 2rem">
                                <div class="header">${user.userName}</div>
                                ${user.status ? '<i class="ui dot mini green circle icon"></i>':''}
                                    
                            </div>
                        </div>`
                xPos+=20;
            }
        })

        $('#friendList').html(usersTemplateHtml)
        $('friendHolder').dropdown()
    })

}

function openChatBox(userName,avatar,xPos) {

    closeChatBox(userName)

    let boxChat = `<div id="boxChat_${userName}" class="box-chat"  style="right: ${xPos}%">

        <div class="ui black inverted segment box-chat-header" style="padding: 10px">

            <div class="box-chat-exit-btn " onclick="closeChatBox('${userName}')">
                <i class="large close icon"></i>
            </div>

            <div class="ui horizontal list">
                <div class="item">
                    <img class="ui avatar image" src="${avatar}">
                    <div class="content">
                        <div class="ui small header" style="color:white">Chat with ${userName}</div>
                    </div>
                </div>
            </div>


        </div>

        <div id="chatList_${userName}" class="ui segment box-chat-content" style="padding: 10px">
        
            <div class="box-chat-response">
               
            </div>
            <div class="box-chat-send">

            </div>
        </div>

        <div  class="ui inverted segment box-chat-bottom" style="padding: 10px">
            <div class="ui search">
                <div class="ui icon send">
                    <input id="text_${userName}" class="prompt" type="text" placeholder="text..." style="height: 40px;padding: 10px;">
                    <i id="sendBtn_${userName}" onclick="sendMessage('${userName}')" class="large send icon"></i>
                </div>
            </div>
        </div>

    </div>`

    let chatContainer = document.getElementById("chat")

    chatContainer.insertAdjacentHTML('beforeend',boxChat)

    let input = document.getElementById("text_"+userName);

    input.addEventListener("keyup", function(event) {
        if (event.keyCode === 13) {
            event.preventDefault();
            // Trigger the button element with a click
            document.getElementById("sendBtn_"+userName).click();
        }
    });


    //render chat from database
}

function scrollToBottom(chatList) {
    chatList.scrollTop=chatList.scrollHeight
}

function closeChatBox(userName) {
    let boxChat = $(`#boxChat_${userName}`)

    boxChat.transition('fly right',function () {
        boxChat.remove()
    })

}