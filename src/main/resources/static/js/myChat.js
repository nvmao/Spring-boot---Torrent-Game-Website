

let url = $('#contextPathHolder').attr('data-contextPath')
let loginUser
let stompClient
let mouseDown = false
let isClick = false

function connectToChat(userName) {
    let socket = new SockJS(url+'/chat')
    stompClient = Stomp.over(socket)
    stompClient.connect({},function (frame) {
        console.log('connected to: '+frame)
        stompClient.subscribe('/topic/messages/' + userName,function (response) {
            let  data = JSON.parse(response.body)

            if(data.content == "--typing"){
                sendTypingMessage(data.fromUser)
            }
            else{
                sendReceiveMessage(data.fromUser,data.content)
            }
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
function sendTypingSocket(toUser) {
    stompClient.send(`/app/chat/${toUser.userName}/typing`,{},JSON.stringify({
        content:'--typing',
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
    else{

        let chatList = document.getElementById("chatList_"+fromUser.userName)

        let receiveMessageHtml = `<div class="box-chat-response" style="margin-left: 2px">
                <div class="ui comments" style="padding: 0">
                    <div class="comment" style="padding: 0">
                        <div class="content" style="width: auto">
                            <a class="ui image avatar">
                                <img src="${fromUser.avatar}">
                            </a>
                            <div class="metadata">
                                <span class="date">${timeSince(new Date())}</span>
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
}

function sendTypingMessage(fromUser) {

    let chatAction = document.getElementById("chat-action_"+fromUser.userName)

    chatAction.innerText='typing...'
    chatAction.className += " ui green label "

    setTimeout(()=>{
        chatAction.innerText=''
        chatAction.className=''
    },3000)

}

function sendMessage(to) {

    $.get(url+'/socket/users/'+to,(response)=>{
        let toUser = response

        let messageInput = document.getElementById("text_"+to)
        let chatList = document.getElementById("chatList_"+to)
        let message = messageInput.value

        message.trim()
        if(message.length==0){
            return
        }

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
                                                <span class="date">${timeSince(new Date())}</span>
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

function renderChatHistory(from,to,page,autoScroll) {

    return new Promise(resolve => {
        let chatList = document.getElementById("chatList_"+to)

        $.get(url+'/chat-messages/'+from+'/'+to+'?page='+page,(data)=>{

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
                                                <span class="date">${timeSince(Date.parse(message.createdAt))}</span>
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
                    chatList.insertAdjacentHTML('afterbegin',sendMessageHtml)
                    if(autoScroll){
                        scrollToBottom(chatList)
                    }
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
                                <span class="date">${timeSince(Date.parse(message.createdAt))}</span>
                            </div>
                            <div class="text" style="background-color: grey;border-radius: 25px;padding: 8px;width: auto">
                                ${message.content}
                            </div>

                        </div>
                    </div>
                </div>
            </div>`

                    chatList.insertAdjacentHTML('afterbegin',receiveMessageHtml)
                    if(autoScroll){
                        scrollToBottom(chatList)
                    }
                }
            })

            $.get(url+`/chat-messages/max-page/${from}/${to}`,(maxPage)=>{
                if(page < maxPage){
                    let loadMoreHtml = `<a class="ui tiny basic blue button" id="load-more_${to}" data-value="${page+1}" style="margin-bottom: 3px">load more</a>`
                    chatList.insertAdjacentHTML('afterbegin',loadMoreHtml)

                    let loadMoreBtn = document.getElementById("load-more_"+to)
                    loadMoreBtn.addEventListener("click",()=>{

                        loadMoreBtn.classList.add("loading")
                        setTimeout(()=>{
                            renderChatHistory(from,to,page+1,false)
                            loadMoreBtn.remove()
                        },1000)

                    })
                }
            })

            resolve()
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

    // usersTemplateHtml = `
    //                     <div id="friend_${user.userName}" class="item" onclick="openChatBox('${user.userName}','${user.avatar}',${50})" >
    //                             ${countUnread != 0 ? `<div class=" mini circular left ui teal label">${countUnread}</div>`:''}
    //
    //                         <img class="ui avatar image" src="${user.avatar}" style="margin-right: 0">
    //                         <div class="content" style="margin-left: 0;margin-right: 2rem">
    //                             <div class="header">${user.userName}</div>
    //                             ${user.status ? '<div style="color: green">online</div>':'<div style="color: red">offline</div>'}
    //
    //                         </div>
    //                     </div>`

    // ${user.status ? ' <div class="floating left ui green label">on</div>':' <div class="floating ui red label">off</div>'}
    usersTemplateHtml=`

                        <a id="friend_${user.userName}" onclick="openChatBox('${user.userName}','${user.avatar}',${50})" 
                            class="ui ${user.status? 'blue':'grey'} image horizontal label" style="display: block;padding: 10px;margin: 10px">

                            <div class="ui grid">
                                <div class="six wide column" >
                                    <img src="${user.avatar}" >
                                </div>
                                <div class="ten wide column" style="padding-left: 0 !important;margin-left: 0 !important;">
                                    ${user.userName}
                                </div>
                            </div>
                            
                                ${countUnread != 0 ? `<div class="floating mini ui teal label">${countUnread}</div>`:''}
                               
                               
                                
<!--                            
                            </a>`

    friendList.insertAdjacentHTML('beforeend',usersTemplateHtml)
    $('friendHolder').dropdown()
}

async function fetchAllUsers() {

    $.get(url+'/socket/fetchAllUsers', function(response){

        let users = response
        let usersTemplateHtml=''
        let friendList = document.getElementById("friendList")
        if(friendList === null) return
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

    <div id="boxChatHeader_${userName}" class="ui black inverted segment box-chat-header" style="padding: 10px;">

        <div class="box-chat-exit-btn " onclick="closeChatBox('${userName}')">
            <i class="large close icon"></i>
        </div>

        <div class="ui horizontal list">
            <div class="item" >
                <img class="ui avatar image" src="${avatar}">
                <div class="content" >
                    <div class="ui small header" style="color:white">${userName.substring(0,15)} <span  id="chat-action_${userName}"></span> </div>
                </div>
            </div>
        </div>


    </div>

    <div id="chatList_${userName}" class="ui segment box-chat-content" style="padding: 10px">
        
    </div>

    <div id="boxChatFooter_${userName}" class="ui inverted inverted segment box-chat-bottom" style="padding: 10px">
    
        <div class="ui search">
            <div class="ui icon send">
                <input autofocus id="text_${userName}" class="prompt" type="text" placeholder="text..." style="width: 65%;height: 40px;padding: 10px;">
                    <span>
                        <label for="file-input_${userName}">
                            <i class="large image icon" ></i>
                        </label>
                        <input type="file" id="file-input_${userName}" accept="image/x-png,image/gif,image/jpeg" style="display: none" >
                    </span>
                    <i class="large meh icon" id="pop-icon_${userName}"></i>
                    <i id="sendBtn_${userName}" onclick="sendMessage('${userName}')" class="large send icon" ></i>
                
            </div>
            
         </div>
             

    </div>

        <div class="ui popup top left hidden" id="emoji-content_${userName}" style="min-width: 250px;width: 250px;padding: 2px">
            <div class="emoji-container" >
                <div class="ui grid" id="emoji-container_${userName}">
                
                </div>
            </div>
        </div>

</div>`

    let chatContainer = document.getElementById("chat")

    chatContainer.insertAdjacentHTML('beforeend',boxChat)

    let input = document.getElementById("text_"+userName);
    input.focus()

    input.addEventListener("keyup",function () {
        $.get(url+'/socket/users/'+userName,(response)=> {
            sendTypingSocket(response)
        })
    })

    input.addEventListener("keyup", function(event) {
        if (event.keyCode === 13) {
            event.preventDefault();
            // Trigger the button element with a click
            document.getElementById("sendBtn_"+userName).click();
        }
    });

    await renderChatHistory(loginUser.userName,userName,1,true)
    console.log("after render")

    rePositionChatBox()
    dynamicBackgroundChatBox(userName,avatar)

    ////////////////////
    // image upload
    let fileInput = document.getElementById("file-input_"+userName)

    fileInput.addEventListener("change",()=>{
        let photo = fileInput.files[0]
        let form = document.getElementById("form-info")
        let csrfValue = form.querySelector("input").value

        let formData = new FormData(form)
        formData.append("photo", photo);
        formData.append("_csrf",csrfValue)

        $.ajax({
            type: "POST",
            url: url+`/chat-messages/upload/${loginUser.userName}/${userName}`,
            data: formData,
            contentType: false,
            processData: false,
            enctype : 'multipart/form-data',
            beforeSend: function() {

            },
            success: function(response) {
                input.value += `<img class="ui small image" src="${response}" style="width: 100%">`
                document.getElementById("sendBtn_"+userName).click();
            },
            error: function(msg) {
            }
        });


    })


    ///////////////////////////////////////////////////////////////////////////
    // render emoij

    let emojiContainer = document.getElementById("emoji-container_"+userName)

    for(let i = 128540;i<=128580;i++){
        let cl = `emoji_${userName}`
        let emoijHtml =
            `
            <div  class="three wide column" style="margin: 0" >
                <div class="${cl}" >
                    <p style="font-size:20px">&#${i};</p>
                </div>
            </div>
            `
        emojiContainer.insertAdjacentHTML('beforeend',emoijHtml)
    }
    for(let j=129296;j<129327;j++){
        let cl = `emoji_${userName}`
        let emoijHtml =
            `
            <div  class="three wide column" style="margin: 0" >
                <div class="${cl}" >
                    <p style="font-size:20px">&#${j};</p>
                </div>
            </div>
            `
        emojiContainer.insertAdjacentHTML('beforeend',emoijHtml)
    }

    let emoji = document.querySelectorAll(".emoji_"+userName)

    emoji.forEach(e=>{
        e.addEventListener('click',()=>{
            mouseDown = false
            isClick = true
            let emojiText = e.querySelector('p').innerText
            input.value += emojiText
        })
        e.addEventListener('mousedown',()=>{
            mouseDown = true
            isClick = false
            setTimeout(()=>{
                if(mouseDown == true && isClick==false){
                    let emojiText = e.querySelector('p').innerText
                    input.value += `<p style="font-size:100px">${emojiText}</p>`
                    document.getElementById("sendBtn_"+userName).click();
                }
            },2000)
        })
        e.addEventListener('mouseup',()=>{
            mouseDown = false
        })
    })

    $('#pop-icon_'+userName)
        .popup({
            popup : $('#emoji-content_'+userName),
            on :'click'
        })

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

// function timeSince(timeStamp) {
//     var now = new Date(),
//         secondsPast = (now.getTime() - timeStamp) / 1000;
//     if (secondsPast < 60) {
//         return parseInt(secondsPast) + 's ago';
//     }
//     if (secondsPast < 3600) {
//         return parseInt(secondsPast / 60) + 'm ago';
//     }
//     if (secondsPast <= 86400) {
//         return parseInt(secondsPast / 3600) + 'h ago';
//     }
//     if (secondsPast > 86400) {
//         return timeConverter(timeStamp)
//     }
// }
function timeSince(date) {

    var seconds = Math.floor((new Date() - date) / 1000);

    var interval = Math.floor(seconds / 31536000);

    if (interval > 1) {
        return interval + " years";
    }
    interval = Math.floor(seconds / 2592000);
    if (interval > 1) {
        return interval + " months";
    }
    interval = Math.floor(seconds / 86400);
    if (interval > 1) {
        return interval + " days";
    }
    interval = Math.floor(seconds / 3600);
    if (interval > 1) {
        return interval + " hours";
    }
    interval = Math.floor(seconds / 60);
    if (interval > 1) {
        return interval + " minutes";
    }
    return Math.floor(seconds) + " seconds";
}

 function rePositionChatBox() {
    let chatBoxes = document.querySelectorAll('.box-chat')

    let xPos = 50;
    //
    // if(chatBoxes.length > 4){
    //     await closeChatBox(chatBoxes[0].getAttribute('id'))
    //     chatBoxes = document.querySelectorAll('.box-chat')
    // }

    for(let i = 0; i < chatBoxes.length;i++){
        let chatBox = chatBoxes[i]

        chatBox.setAttribute("style", "right:"+xPos+"px;")
        xPos+=320;

    }



}

function dynamicBackgroundChatBox(userName,avatar) {

    let boxChatHeader = document.querySelector('#boxChatHeader_'+userName)
    let boxChatFooter = document.querySelector('#boxChatFooter_'+userName)
    let chatList = document.querySelector("#chatList_"+userName)

    let fac = new FastAverageColor();

    fac.getColorAsync(avatar)
        .then(function(color) {

            boxChatHeader.setAttribute("style",
                `padding: 10px;background: linear-gradient(145deg, #222222,${color.hex}) !important;`)
            boxChatFooter.setAttribute("style",
                `padding: 10px;background: linear-gradient(145deg, #222222,${color.hex}) !important;`)
            chatList.setAttribute("style",
                `padding: 10px;background: linear-gradient(145deg, #222222,${color.hex}) !important;`)

        }).catch(function(e) {
    });
}