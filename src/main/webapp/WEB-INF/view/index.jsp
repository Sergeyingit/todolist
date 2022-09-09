<%@ include file="sections/taglib.jsp" %>
<jsp:include page="sections/startPage.jsp">
    <jsp:param name="title" value="ToDoList: Home page"/>
</jsp:include>

    <jsp:include page="sections/menu.jsp"/>

    <div class="container-fluid mt-5 text-center">
        <h1 class="w-100">ToDoList</h2>

        <p class="pt-5">
        Case planning app
        </p>
        <p class="pt-2">
            <ul class="d-flex text-white list-group justify-content-center flex-row justify-content-center">
                <security:authorize access="isAuthenticated()">
                    <li>
                        <a href="/user/" class="btn btn-primary">Create task</a>
                    </li>


                </security:authorize>
                <security:authorize access="hasRole('ROLE_ADMIN')">
                    <li class="ps-2">
                        <a href="/admin" class="btn btn-primary">Admin page</a>
                    </li>
                </security:authorize>
                <security:authorize access="!isAuthenticated()">
                    <li>
                        <a href="/login" class="btn btn-primary">Sign in</a>
                    </li>
                    <li class="ps-2">
                        <a href="/registration" class="btn btn-primary">Sign up</a>
                    </li>


                </security:authorize>
            </ul>
        </p>
    </div>
<jsp:include page="sections/endPage.jsp" />
