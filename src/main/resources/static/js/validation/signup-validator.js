
checkExistUser()

let existUserName = true

function checkExistUser(){

    let field = document.getElementById("username")

    field.addEventListener("keyup",()=>{
        setTimeout(()=>{
            $.get(url+`/socket/users/${field.value}`,(user)=>{

                let found = !(user == null || user == '')

                if(found){
                    document.getElementById("username-field").classList.add("error")
                }
                else{
                    document.getElementById("username-field").classList.remove("error")
                }

                existUserName = !found

            })
        },1000)
    })

}

// exist username

// match password
$.fn.form.settings.rules.reEnterPassword = function(value) {

    let password = document.getElementById("password")

    return (value == password.value)
}

$.fn.form.settings.rules.existUsername = function(value) {

    return  existUserName

}

$('.ui.form')
    .form({
        fields: {

            username: {
                identifier: 'userName',
                rules: [
                    {
                        type   : 'empty',
                        prompt : 'Please enter a username'
                    },
                    {
                        type   : 'existUsername',
                        prompt : 'Username have taken'
                    },
                    {
                        type: 'regExp',
                        value:'/^[a-zA-Z0-9_-]{2,12}$/',
                        prompt:'Username only use 0-9, a-z, "-,_" and from 2 to 12 characters'
                    },

                ]
            },
            password: {
                identifier: 'password',
                rules: [
                    {
                        type   : 'empty',
                        prompt : 'Please enter a password'
                    },
                    {
                        type   : 'minLength[6]',
                        prompt : 'Your password must be at least {ruleValue} characters'
                    }
                ]
            },
            rePassword: {
                identifier: 'rePassword',
                rules: [
                    {
                        type   : 'reEnterPassword',
                        prompt : 'Your password does not match'
                    }
                ]
            },

        }
    })
;