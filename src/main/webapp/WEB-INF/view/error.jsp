<%@ include file="sections/taglib.jsp" %>
<jsp:include page="sections/startPage.jsp">
    <jsp:param name="title" value="ToDoList: ${status}"/>
</jsp:include>


    <div class="container-fluid mt-5 text-center">
        <h1 class="w-100">${status}</h2>

        <a href="/" class="mt-5 btn btn-primary text-white">
            Return on the main page
        </a>

    </div>
<jsp:include page="sections/endPage.jsp" />
