<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.eventmanagement.entity.Attendees" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Attendees Details</title>
</head>
<%
   List<Attendees> eventData =  (List<Attendees>)request.getAttribute("attendees");
%>

<% if (eventData != null && !eventData.isEmpty()) { %>
    <h1>Attendees Data</h1>
    <table border="1" align="center">
        <thead>
            <tr>
                <th>ID</th>
                <th>NAME</th>
                <th>EMAIL</th>
                <th>RSVP</th>
            </tr>
        </thead>
        <tbody>
            <% for (Attendees data : eventData) { %>
                <tr>
                    <td><%= data.getId() %></td>
                    <td><%= data.getName() %></td>
                    <td><%= data.getEmail()%></td>
                    <td><%= data.getEvent() %></td>
                    
                    <td>
                        <a href="data_edit?id=<%= data.getId() %>" class="btn edit">Edit</a>
                        <a href="data_delete?id=<%= data.getId() %>" class="btn delete">Delete</a>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>
<% } else { %>
    <h1>Event Not Found</h1>
<% } %>

<div class="btn-container">
    <a href="createEvent" class="btn"> Add Event
    </a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="./" class="btn"> Home
    </a>
</div>

</body>
</html>