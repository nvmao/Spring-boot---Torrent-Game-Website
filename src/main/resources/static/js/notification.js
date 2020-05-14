
let stompClientNotif

connectToLoginUser()

function connectToLoginUser() {
    $.get(url+'/socket/loginUser',(response)=>{
        loginUser = response
        connectToNotification(loginUser.userName)
    })
}


function connectToNotification(userName) {
    let socket = new SockJS(url+'/notification')
    stompClientNotif = Stomp.over(socket)
    stompClientNotif.connect({},function (frame) {
        console.log('connected to: '+frame)
        stompClientNotif.subscribe('/topic/notification/' + userName,function (response) {
            console.log(response)
        })
    })
}

function sendNotificationSocket(text,toUser) {
    stompClientChat.send('/app/notification/'+toUser.userName,{},JSON.stringify({
        content:text,
        createdAt:new Date().toISOString(),
        fromUser:loginUser,
        toUser:toUser
    }))
}

function  sendCommentNotification() {

    let usersOnPost = document.querySelectorAll(".user-comment")
    let usersMap = new Map()

    usersOnPost.forEach(usersDom=>{
        usersMap.set(usersDom.innerText,usersDom.innerText)
    })

    usersMap.forEach((user)=>{
        $.get(url+'/socket/users/'+user,(response)=> {
            sendNotificationSocket(`${loginUser.userName} has also comment with you`,response)
        })
    })


}
