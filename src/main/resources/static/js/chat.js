const url = 'http://localhost:8080'
let stompClient
let selectedUser;
let newMessages = new Map()

function connectToChat(userName) {
    console.log("connecting to chat ...")
    let socket = new SockJS(url+'/chat')
    stompClient = Stomp.over(socket)
    stompClient.connect({},function (frame) {
        console.log('connected to: '+frame)
        stompClient.subscribe('/topic/messages/' + userName,function (response) {
            let data = JSON.parse(response.body)
            if(selectedUser == data.fromLogin){
                render(data.message,data.fromLogin)
            }
            else{
                newMessages.set(data.fromLogin,data.message)
                $('#userNameAppender_'+data.fromLogin).append(`<span id="newMessage_${data.fromLogin}" style="color:green">+1</span>`)
            }

        })
    })
}

function sendMsg(from,text) {
    stompClient.send('/app/chat/'+selectedUser,{},JSON.stringify({
        fromLogin: from,
        message:text
    }))
}

function registration() {
    let userName = document.getElementById('userName').value
    $.get(url+'/registration/'+userName,function (response) {

        connectToChat(userName)

    }).fail(function (error) {
        if(error.status===400){
            alert("login is already busy!")
        }
    })
}

function selectUser(userName){
    console.log('selecting users: ' + userName)
    selectedUser = userName

    let element = document.getElementById('newMessage_'+userName)
    if(element){
        element.parentNode.removeChild(element)
        console.log('yeah')
        render(newMessages.get(userName),userName)
    }

    $('#selectedUserId').html('')
    $('#selectedUserId').append('Chat with: ' + userName)


}


fetchAll()

function fetchAll() {
    let userName = document.getElementById('userName').value
    $.get(url+'/fetchAllUsers',function (response) {
        let users = response;
        let usersTemplateHtml = "";
        for(let i=0;i<users.length;i++){
            usersTemplateHtml = usersTemplateHtml +`<a href="#" onclick="selectUser('${users[i]}')"><li class="clearfix">
                <img alt="avatar" height="55px"
                     src="https://rtfm.co.ua/wp-content/plugins/all-in-one-seo-pack/images/default-user-image.png"
                     width="55px"/>
                <div class="about">
                    <div id="userNameAppender_${users[i]}" class="name">${users[i]}</div>
                    <div class="status">
                        <i class="fa fa-circle online"></i>
                    </div>
                </div>
            </li></a>`
        }
        $('#userList').html(usersTemplateHtml)
    })
}