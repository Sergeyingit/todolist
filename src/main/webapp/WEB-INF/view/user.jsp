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
        <c:if test="${!empty user.tasks}">
            <table class="table">
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Date</th>
                        <th>Edit</th>
                    </tr>
                </thead>


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
                                <a href="${updateTask}" class="btn btn-success">update</a>
                                <a href="${deleteTask}" class="btn btn-danger">delete</a>
                            </td>
                        </tr>
                    </tbody>
                </c:forEach>
            </table>
        </c:if>

        <c:if test="${empty user.tasks}">
            <p>No tasks</p>
        </c:if>

    <a href="/user/${user.id}/tasks/create" class="btn btn-primary">New task </a>
    <a href="/user/${user.id}/tasks/pdf" class="btn btn-primary">Create PDF </a>
    </div>
<jsp:include page="sections/endPage.jsp" />
