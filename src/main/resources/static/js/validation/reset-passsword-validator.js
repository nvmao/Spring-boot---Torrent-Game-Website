
// match password
$.fn.form.settings.rules.reEnterPassword = function(value) {

    let password = document.getElementById("password")

    return (value == password.value)
}


$('.ui.form')
    .form({
        fields: {

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