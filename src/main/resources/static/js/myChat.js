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
            sendReceiveMessage(data.fromUser,data.content)
        })
    })
}

$.get(url+'/socket/loginUser',(response)=>{
        loginUser = response
        connectToChat(loginUser.userName)
})
fetchAllUsers()


function sendMsgSocket(text,toUser) {
    stompClient.send('/app/chat/'+toUser.userName,{},JSON.stringify({
        content:text,
        createdAt:new Date().toISOString(),
        fromUser:loginUser,
        toUser:toUser
    }))
}

function  sendReceiveMessage(fromUser,message) {

    let chatBox = document.getElementById('boxChat_'+fromUser.userName)
    if(chatBox === null){
        document.getElementById('friend_'+fromUser.userName).click()
    }

    let chatList = document.getElementById("chatList_"+fromUser.userName)

    let receiveMessageHtml = `<div class="box-chat-response" style="margin-left: 2px">
                <div class="ui comments" style="padding: 0">
                    <div class="comment" style="padding: 0">
                        <div class="content" style="width: auto">
                            <a class="ui image avatar">
                                <img src="${fromUser.avatar}">
                            </a>
                            <div class="metadata">
                                <span class="date">${timeSince(new Date())} ago</span>
                            </div>
                            <div class="text" style="background-color: grey;border-radius: 25px;padding: 8px;width: auto">
                                ${message}
                            </div>

                        </div>
                    </div>
                </div>
            </div>`

    chatList.insertAdjacentHTML('beforeend',receiveMessageHtml)
    scrollToBottom(chatList)
}

function sendMessage(to) {

    $.get(url+'/socket/users/'+to,(response)=>{
        let toUser = response

        let messageInput = document.getElementById("text_"+to)
        let chatList = document.getElementById("chatList_"+to)
        let message = messageInput.value
        messageInput.value = ''

        sendMsgSocket(message,toUser)

        let sendMessageHtml = `<div class="box-chat-send">
                <div class="ui grid">
                    <div class="four wide column">
                    </div>
                    <div class="twelve wide column" style="padding-bottom: 2px;" >
                        <div class="ui comments">
                            <div class="comment">
                                <div class="content">
                                    <div class="ui grid">
                                        <div class="eight wide column">
                                        </div>
                                        <div class="eight wide column">
                                            <div class="metadata">
                                                <span class="date">${timeSince(new Date())} ago</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="text" style="background-color: deepskyblue;border-radius: 25px;padding: 8px;">
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

    })

}

function renderChatHistory(from,to) {

    let chatList = document.getElementById("chatList_"+to)

    $.get(url+'/chat-messages/'+from+'/'+to,(data)=>{

        data.forEach(message=>{
            if(message.fromUser.userName === from){
                let sendMessageHtml = `<div class="box-chat-send">
                <div class="ui grid">
                    <div class="four wide column">
                    </div>
                    <div class="twelve wide column" style="padding-bottom: 2px;" >
                        <div class="ui comments">
                            <div class="comment">
                                <div class="content">
                                    <div class="ui grid">
                                        <div class="eight wide column">
                                        </div>
                                        <div class="eight wide column">
                                            <div class="metadata">
                                                <span class="date">${timeSince(Date.parse(message.createdAt))} ago</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="text" style="background-color: deepskyblue;border-radius: 25px;padding: 8px;">
                                        ${message.content}
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
            else{
                let receiveMessageHtml = `<div class="box-chat-response" style="margin-left: 2px">
                <div class="ui comments" style="padding: 0">
                    <div class="comment" style="padding: 0">
                        <div class="content" style="width: auto">
                            <a class="ui image avatar">
                                <img src="${message.fromUser.avatar}">
                            </a>
                            <div class="metadata">
                                <span class="date">${timeSince(Date.parse(message.createdAt))} ago</span>
                            </div>
                            <div class="text" style="background-color: grey;border-radius: 25px;padding: 8px;width: auto">
                                ${message.content}
                            </div>

                        </div>
                    </div>
                </div>
            </div>`

                chatList.insertAdjacentHTML('beforeend',receiveMessageHtml)
                scrollToBottom(chatList)

            }
        })

    })


}

function fetchCountUnreadChat(user) {
    return new Promise(resolve => {
        $.get(url+`/chat-messages/countUnread/${user.userName}/${loginUser.userName}`,(response)=>{
            resolve(response)
        })
    })

}

async function addAddBoxClick(user,usersTemplateHtml,friendList){
    let countUnread = await fetchCountUnreadChat(user)
    usersTemplateHtml = `
                        <div id="friend_${user.userName}" class="item" onclick="openChatBox('${user.userName}','${user.avatar}',${50})" >
                                ${countUnread != 0 ? `<div class=" mini circular left ui teal label">${countUnread}</div>`:''}

                            <img class="ui avatar image" src="${user.avatar}" style="margin-right: 0">
                            <div class="content" style="margin-left: 0;margin-right: 2rem">
                                <div class="header">${user.userName}</div>
                                ${user.status ? '<div style="color: green">online</div>':'<div style="color: red">offline</div>'}
                                    
                            </div>
                        </div>`
    friendList.insertAdjacentHTML('beforeend',usersTemplateHtml)
    $('friendHolder').dropdown()
}

async function fetchAllUsers() {

    $.get(url+'socket/fetchAllUsers',async function(response){

        let users = response
        let usersTemplateHtml=''
        let friendList = document.getElementById("friendList")
        friendList.innerHTML=''

        users.forEach((user)=>{
            if(user.id != loginUser.id){
                addAddBoxClick(user,usersTemplateHtml,friendList)
            }
        })

    })

}

async function openChatBox(userName,avatar,xPos) {

    let boxChat = document.getElementById('boxChat_'+userName)
    if(boxChat !== null){
        console.log('wait')
        await closeChatBox(userName)
    }
    boxChat = `<div id="boxChat_${userName}" class="ui raised box-chat"  style="right: ${xPos}px">

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
                <input autofocus id="text_${userName}" class="prompt" type="text" placeholder="text..." style="height: 40px;padding: 10px;">
                <i id="sendBtn_${userName}" onclick="sendMessage('${userName}')" class="large send icon"></i>
            </div>
        </div>
    </div>

</div>`

    let chatContainer = document.getElementById("chat")

    chatContainer.insertAdjacentHTML('beforeend',boxChat)

    let input = document.getElementById("text_"+userName);
    input.focus()

    input.addEventListener("keyup", function(event) {
        if (event.keyCode === 13) {
            event.preventDefault();
            // Trigger the button element with a click
            document.getElementById("sendBtn_"+userName).click();
        }
    });

    renderChatHistory(loginUser.userName,userName)
    rePositionChatBox()


}

function scrollToBottom(chatList) {
    chatList.scrollTop=chatList.scrollHeight
}

function closeChatBox(userName) {
    let boxChat = $(`#boxChat_${userName}`)
    return new Promise(resolve => {
        boxChat.transition('fly right',function () {
            boxChat.remove()
            rePositionChatBox()
            resolve()
        })
    })
}

function timeSince(timeStamp) {
    var now = new Date(),
        secondsPast = (now.getTime() - timeStamp) / 1000;
    if (secondsPast < 60) {
        return parseInt(secondsPast) + 's';
    }
    if (secondsPast < 3600) {
        return parseInt(secondsPast / 60) + 'm';
    }
    if (secondsPast <= 86400) {
        return parseInt(secondsPast / 3600) + 'h';
    }
    if (secondsPast > 86400) {
        day = timeStamp.getDate();
        month = timeStamp.toDateString().match(/ [a-zA-Z]*/)[0].replace(" ", "");
        year = timeStamp.getFullYear() == now.getFullYear() ? "" : " " + timeStamp.getFullYear();
        return day + " " + month + year;
    }
}

function rePositionChatBox() {
    let chatBoxes = document.querySelectorAll('.box-chat')

    let xPos = 50;
    chatBoxes.forEach(chatBox=>{
        chatBox.setAttribute("style", "right:"+xPos+"px;")
        xPos+=320;
    })

}