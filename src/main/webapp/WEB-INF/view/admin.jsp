<%@ include file="sections/taglib.jsp" %>
<jsp:include page="sections/startPage.jsp">
    <jsp:param name="title" value="ToDoList: Admin page"/>
</jsp:include>

   <jsp:include page="sections/menu.jsp"/>

    <div class="container-fluid mt-5 text-center">
        <h1 class="w-100">Admin page</h2>

        <p class="pt-5">
        Viewing and editing
        </p>
        <table class="table">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Email</th>
                    <th>Tasks</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <c:forEach var="user" items="${users}">
                <c:url var="deleteUser" value="/admin/users/delete">
                    <c:param name="userId" value="${user.id}"/>
                </c:url>
                <tbody>
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.email}</td>
                        <td><a href="/user/${user.id}/tasks">${user.tasks.size()}</a></td>
                        <td>
                            <c:if test="${user.id != 1}">
                                <a href="${deleteUser}">delete</a>
                            </c:if>
                        </td>
                    </tr>
                </tbody>
            </c:forEach>
        </table>
    </div>
<jsp:include page="sections/endPage.jsp" />
