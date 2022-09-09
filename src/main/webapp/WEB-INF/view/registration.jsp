<%@ include file="sections/taglib.jsp" %>
<jsp:include page="sections/startPage.jsp">
    <jsp:param name="title" value="ToDoList: Registration page"/>
</jsp:include>
            <div class="container-fluid mt-5 text-center">
                <h1 class="w-100">Registration</h1>
                <form:form class="form-registration" action="registration" modelAttribute="user">
                    <form:hidden path="id"/>
                    <div class="mb-3">
                        <form:input class="form-control form-control-lg" type="email" path="email" placeholder="email"/>
                        <div class="form-text text-danger"><form:errors path="email"/></div>
                    </div>

                    <div class="mb-3">
                        <form:input class="form-control form-control-lg" path="passwordNew"/>
                        <div class="form-text text-danger"><form:errors path="passwordNew"/></div>
                    </div>

                    <div class="mb-3">
                        <form:input class="form-control form-control-lg" path="passwordMatches"/>
                        <div class="form-text text-danger">
                            <c:if test="${errorPasswordMatches != null}">
                                <span>${errorPasswordMatches} <br></span>
                            </c:if>
                            <form:errors path="passwordMatches"/>
                        </div>
                    </div>

                    <input type="submit" value="Registration" class="btn btn-primary">
                </form:form>
                <div class="form-text text-danger">${messageError}</div>

            </div>
<jsp:include page="sections/endPage.jsp" />
