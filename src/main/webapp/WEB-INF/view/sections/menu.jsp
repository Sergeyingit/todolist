<%@ include file="taglib.jsp" %>
<nav class="navbar navbar-expand-lg">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">ToDoList</a>

        <div class="collapse navbar-collapse" id="navbarNavDropdown">
              <ul class="navbar-nav w-100 justify-content-between">
                <li class="nav-item">
                  <a class="nav-link active" aria-current="page" href="/">Home</a>
                </li>

                <security:authorize access="isAuthenticated()">
                    <security:authentication var="principal" property="principal"/>
                    <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Profile
                      </a>
                      <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/user/${principal.id}">Tasks</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="/logout">Loguot</a></li>
                      </ul>
                    </li>
                </security:authorize>
              </ul>
        </div>
    </div>
</nav>