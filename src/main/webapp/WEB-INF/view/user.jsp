<%@ include file="sections/taglib.jsp" %>
<jsp:include page="sections/startPage.jsp">
    <jsp:param name="title" value="ToDoList: Profile page"/>
</jsp:include>

    <jsp:include page="sections/menu.jsp"/>

    <div class="container-fluid mt-5 text-center">
        <security:authentication var="principal" property="principal"/>
        <h1 class="w-100">Profile - ${user.email}</h2>

        <p class="pt-5">
        Tasks
        </p>
        <table class="table">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Date</th>
                    <th>Edit</th>
                </tr>
            </thead>

            <c:if test="${!empty user.tasks}">
            <c:forEach var="task" items="${user.tasks}">
                <c:url var="updateTask" value="/user/${user.id}/tasks/update">
                    <c:param name="taskId" value="${task.id}"/>
                </c:url>
                <c:url var="deleteTask" value="/user/${user.id}/tasks/delete">
                    <c:param name="taskId" value="${task.id}"/>
                </c:url>
                <tbody>
                    <tr>
                        <td>${task.title}</td>
                        <td>${task.description}</td>
                        <td>${task.date}</td>
                        <td>
                            <a href="${updateTask}">update</a>
                            <a href="${deleteTask}">delete</a>
                        </td>
                    </tr>
                </tbody>
            </c:forEach>
            </c:if>
            <c:if test="${empty user.tasks}">
                <tbody>
                    <tr>
                        No tasks
                    </tr>
                </tbody>
            </c:if>
        </table>
    <a href="/user/${user.id}/tasks/create">New task </a>
    </div>
<jsp:include page="sections/endPage.jsp" />
