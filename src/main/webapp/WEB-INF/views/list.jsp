<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ryun
  Date: 2022/12/02
  Time: 10:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Anonymous Board</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>

<!-- <header>
  <div class="bg-dark" id="navbarHeader">
  <div class="navbar navbar-dark bg-dark shadow-sm fixed-top">
    <div class="container">
      <a href="#" class="navbar-brand d-flex align-items-center">
        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" aria-hidden="true" class="me-2" viewBox="0 0 24 24"><path d="M23 19a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h4l2-3h6l2 3h4a2 2 0 0 1 2 2z"/><circle cx="12" cy="13" r="4"/></svg>
        <strong>Anonymous Board</strong>
      </a>
      <a href="#" class="btn btn-primary my-2">로그인</a>
    </div>
  </div>
</header> -->

<main>

    <!-- 헤더 -->
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="fw-light">Anonymous Board</h1>
                <p class="lead text-muted">실전프로젝트1</p>
                <p>
                    <a href="add" class="btn btn-primary my-2">글 쓰기</a>
                    <!-- <a href="#" class="btn btn-secondary my-2">Secondary action</a> -->
                </p>
            </div>
        </div>
    </section>


    <div class="py-5 bg-light">
        <div class="container">

            <c:forEach items="${list}" var="u">
                <div class="row row-cols-3 g-3">
                    <div class="col mx-auto">
                        <div class="card shadow-sm">
                            <div class="card-header">
                                <span>${u.nickname}</span>
                                <span>${u.ip}</span>
                            </div>
                            <img src="${pageContext.request.contextPath}/img/${u.getImage()}" class="card-img" alt="!!THERE IS NO IMAGE!!">


                            <div class="card-body">
                                <div class="d-flex justify-content-end align-items-center">
                                    <span class="px-2" id="loveNum${u.getSeq()}">${u.likecount}</span>
                                    <button type="button" class="btn btn-outline-danger" onclick="javascript:love('${u.getSeq()}')">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart-fill" viewBox="0 0 16 16">
                                            <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
                                        </svg>
                                    </button>
                                </div>
                                <p class="card-text">${u.content}</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <small class="text-muted">${u.regdate}</small>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-sm btn-outline-secondary" onclick="javascript:edit_ok('${u.getSeq()}')">수정</button>
                                        <button type="button" class="btn btn-sm btn-outline-secondary" onclick="javascript:delete_ok('${u.getSeq()}')">삭제</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <br><br><br>
            </c:forEach>
        </div>
    </div>

</main>

<footer class="text-muted py-5">
    <div class="container">
        <p class="float-end mb-1">
            <a href="#">Back to top</a>
        </p>
        <div class="col-sm-8 col-md-7 py-4">
            <h4 class="text-dark">Creators</h4>
            <p class="text-muted">권은혁 21900050<br>김가륜 21900059</p>
        </div>
    </div>
</footer>


<script src="../assets/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function delete_ok(id){
        var a = confirm("정말로 삭제하겠습니까?");
        if(a) location.href='delete/' + id;
    }

    function edit_ok(id){
        let pw = prompt("Enter pw", "");
        // alert(pw);
        if (pw == null || pw == "") {
            // alert("enter pw!");
            // return false;
        } else {
            location.href = "editpost/"+id+"?pw="+pw;
        }
    }

    function delete_ok(id){
        let pw = prompt("Enter pw", "");
        if (pw == null || pw == "") {
        } else {
            location.href = "delete/"+id+"?pw="+pw;
        }
    }

    function love(seq) {
        var xhttp;

        xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("loveNum"+seq).innerHTML = this.responseText;
            }
        };
        xhttp.open("GET", "like?q="+seq, true);
        xhttp.send();
    }
</script>

</body>
</html>
