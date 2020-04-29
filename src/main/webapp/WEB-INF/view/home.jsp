
<html>
<header>
    <link href="/css/login.css" rel="stylesheet">
</header>
<body>

<div class="container">
        <div class="row">
            <h2 style="text-align:center">Login with Social Media or Manually</h2>
            <div class="vl">
                <span class="vl-innertext">View</span>
            </div>

            <div class="col">
                <a href="${pageContext.request.contextPath}/facebookLogin" class="fb btn">
                    <i class="fa fa-facebook fa-fw"></i> Login with Facebook
                </a>
                <a href="${pageContext.request.contextPath}/googleLogin" class="google btn">
                    <i class="fa fa-google fa-fw"></i> Login with Google+
                </a>
            </div>



        </div>
</div>



</body>

</html>