<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>Demo</title>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width"/>
    <base href="/"/>
    <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/css/bootstrap.min.css"/>
    <script type="text/javascript" src="/webjars/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<h1>Demo</h1>

<div class="container unauthenticated">
    Login : <a href="/login">click here</a>
    <br>
    Login as Facebook : <a href="${pageContext.request.contextPath}/login/oauth2/code/facebook">Facebook</a>
</div>
<div class="container authenticated" style="display:none">
    Logged into Facebook as: <span id="user"></span>
</div>
</div>
<script type="text/javascript">
    $.get("/user", function(data) {
        $("#user").html(data.userAuthentication.details.name);
        $(".unauthenticated").hide()
        $(".authenticated").show()
    });

</script>
</body>
</html>