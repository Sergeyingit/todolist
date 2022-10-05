<%@ include file="sections/taglib.jsp" %>
<jsp:include page="sections/startPage.jsp">
    <jsp:param name="title" value="ToDoList: Profile page"/>
</jsp:include>

    <jsp:include page="sections/menu.jsp"/>
    <security:authentication var="principal" property="principal"/>
    <div class="container-fluid mt-5 text-center">
        <h1 class="w-100">Create task</h1>
        <form:form class="form-task" action="saveTask" modelAttribute="task">
            <form:hidden path="userId"/>
            <form:hidden path="id"/>
            <div class="mb-3">
                <form:input class="form-control form-control-lg" type="text" path="title" placeholder="title"/>
                <div class="form-text text-danger"><form:errors path="title"/></div>
            </div>

            <div class="mb-3">
                <form:textarea class="form-control form-control-lg" path="description"/>
                <div class="form-text text-danger"><form:errors path="description"/></div>
            </div>

            <div class="mb-3">
                <form:input class="form-control form-control-lg" type="date" path="date"/>
            </div>

            <input type="submit" value="Create" class="btn btn-primary">
        </form:form>

    </div>
<jsp:include page="sections/endPage.jsp" />
