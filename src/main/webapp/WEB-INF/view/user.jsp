<%@ include file="sections/taglib.jsp" %>
<jsp:include page="sections/startPage.jsp">
    <jsp:param name="title" value="ToDoList: Profile page"/>
</jsp:include>

    <jsp:include page="sections/menu.jsp"/>

    <div class="container-fluid mt-5 text-center">
        <security:authentication var="principal" property="principal"/>
        <h1 class="w-100">Profile - ${principal.email}</h2>

        <p class="pt-5">
        Tasks
        </p>
        <table>
            <tr>

            </tr>
        </table>
    </div>
<jsp:include page="sections/endPage.jsp" />
