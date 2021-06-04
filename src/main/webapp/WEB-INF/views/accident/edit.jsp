<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
<div class="container-fluid" style="margin-top: 20px">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h4>Редактировать происшествие</h4>
            <form action="<c:url value='/save?id=${accident.id}'/>" method='POST'>
                <div class="form-group">
                    <label for="nameInput">Название</label>
                    <input type="text" class="form-control" id="nameInput" name="name" value="${accident.name}">
                </div>
                <div class="form-group">
                    <label for="textInput">Описание</label>
                    <input type="text" class="form-control" id="textInput" name="text" value="${accident.text}">
                </div>
                <div class="form-group">
                    <label for="addressInput">Адрес</label>
                    <input type="text" class="form-control" id="addressInput" name="address" value="${accident.address}">
                </div>
                <div class="form-group">
                    <label for="typeInput">Тип происшествия</label>
                    <select id="typeInput" name="accidentType.id">
                        <c:forEach var="type" items="${types}">
                            <option value="${type.id}" name="typeId"
                                    <c:if test="${type.id == accident.accidentType.id}">
                                        selected
                                    </c:if>>${type.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Сохранить</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>