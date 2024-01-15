<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
    nav ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
        overflow: hidden;
    }

    nav li {
        float: left;
    }

    nav li button {
        display: block;
        color: white;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
        background-color: #4CAF50;
    }

    nav li button:hover {
        background-color: #3e8e41;
    }
</style>
<meta charset="UTF-8">
<title>Список должносткей</title>
</head>
<body>
<nav>
    <ul>
        <li><button onclick="location.href='/manager/clear'">Clear DB</button></li>
        <li><button onclick="location.href='/manager/fill_db'">Fill DB</button></li>
        <li><button onclick="location.href='/employee'">Employees</button></li>
        <li><button onclick="location.href='/project'">Projects</button></li>
        <li><button onclick="location.href='/customer'">Customers</button></li>
        <li><button onclick="location.href='/position'">Positions</button></li>
        <li><button onclick="location.href='/manager/full_info'">Full info</button></li>
        <li><button onclick="location.href='/manager/employee_position'">Add employee + position</button></li>
        <li><button onclick="location.href='/manager/project_customer'">Add project + customer</button></li>
    </ul>
</nav>
    <h1>Список должностей</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Должность</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="position" items="${positionList}">
                <tr>
                    <td>${position.id}</td>
                    <td>${position.name}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>