<!DOCTYPE html>
<html xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout" xmlns:th="http://www.w3.org/1999/xhtml"
      layout:decorate="~{layouts/application.html}">

<head>
    <title>Анализатор страниц</title>
</head>
<section layout:fragment="content">
    <div class="container-lg mt-5">
        <h1>Сайты</h1>

        <table class="table table-bordered table-hover mt-3">
            <thead>
            <tr>
                <th class="col-1">ID</th>
                <th>Имя</th>
                <th class="col-2">Последняя проверка</th>
                <th class="col-1">Код ответа</th>
            </tr>
            </thead>
            <tbody>
            <tr th:each="url :">

            </tr>
            </tbody>
        </table>>
    </div>
</section>
</html>