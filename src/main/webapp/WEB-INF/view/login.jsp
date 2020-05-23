<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="ui tiny modal">
    <i class="close icon"></i>
    <div class="header">
        Login
    </div>
    <div class=" content">
        <div class="description">
            <div class="ui placeholder segment">
                <div class="ui two column very relaxed stackable grid">
                    <div class="column">
                        <form:form action="${pageContext.request.contextPath}/do-login" method="POST" class="ui form">
                            <div class="field">
                                <label>Username</label>
                                <input type="text" style="position: fixed; left: -10000000px;" disabled/>
                                <div class="ui left icon input">
                                    <input type="text" name="username" placeholder="Username">
                                    <i class="user icon"></i>
                                </div>
                            </div>
                            <div class="field">
                                <label>Password</label>
                                <div class="ui left icon input">
                                    <input name="password"  type="password" placeholder="*******">
                                    <i class="lock icon"></i>
                                </div>
                            </div>
                            <button class="ui teal submit button">Login</button>
                        </form:form>
                    </div>
                    <div class="middle aligned column">
                        <div class="ui big button">
                            <i class="signup icon"></i>
                            Sign Up
                        </div>
                    </div>
                </div>
               
            </div>
        </div>
    </div>
    <div class="actions">
<%--        <div class="ui black deny button">--%>
<%--            Nope--%>
<%--        </div>--%>
<%--        <div class="ui positive right labeled icon button">--%>
<%--            Yep, that's me--%>
<%--            <i class="checkmark icon"></i>--%>
<%--        </div>--%>
    </div>
</div>