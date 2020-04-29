
<html>
<header>
    <link href="/css/login.css" rel="stylesheet">
</header>
<body>

<div class="container">
    <div class="row">
        <h2 style="text-align:center">Login with Social Media or Manually</h2>
        <div class="vl">
            <span class="vl-innertext">or</span>
        </div>

        <div class="col">
            <a href="#" class="fb btn">
                <i class="fa fa-facebook fa-fw"></i> Login with Facebook
            </a>
            <a href="#" class="google btn">
                <i class="fa fa-google fa-fw"></i> Login with Google+
            </a>
        </div>


        <div class="col">
            <img src="${user.imageUrl}" width="130px" height="130px">
            <h2>${user.firstName}</h2>
            <h2>${user.lastName}</h2>

        </div>
        
    </div>
</div>




</body>

</html>