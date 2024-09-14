<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.eventmanagement.entity.Event" %>
<%@ page import="com.eventmanagement.entity.Attendees" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Event Holder Page</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 50px;
        }
        .table th, .table td {
            vertical-align: middle;
            text-align: center;
        }
        .btn-container {
            text-align: center;
            margin-top: 20px;
        }
        .btn-container a {
            margin: 0 15px;
        }
        .card-title {
            font-size: 1.5rem;
            font-weight: bold;
        }
        .card-header {
            background-color: #007bff;
            color: white;
            text-align: center;
        }
    </style>
</head>

<body>
    <div class="container">
        <% List<Event> eventData = (List<Event>) request.getAttribute("allEvents"); %>

        <% if (eventData != null && !eventData.isEmpty()) { %>
            <div class="card">
                <div class="card-header">
                    <h1 class="card-title">Event Data</h1>
                </div>
                <div class="card-body">
                    <table class="table table-bordered table-hover">
                        <thead class="thead-dark">
                            <tr>
                                <th>ID</th>
                                <th>Event Name</th>
                                <th>Description</th>
                                <th>Location</th>
                                <th>Date & Time</th>
                                <th>Attendees</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Event data : eventData) { %>
                                <tr>
                                    <td><%= data.getEid() %></td>
                                    <td><%= data.getTitle() %></td>
                                    <td><%= data.getDescription() %></td>
                                    <td><%= data.getLocation() %></td>
                                    <td><%= data.getDateTime() %></td>
                                    <td>
                                        <% for (Attendees attendee : data.getList()) { %>
                                            <%= attendee.getName() %> (<%= attendee.getEmail() %>)<br>
                                        <% } %>
                                    </td>
                                    <td>
                                        <a href="data_edit?id=<%= data.getEid() %>" class="btn btn-primary btn-sm">Edit</a>
                                        <br>
                                        <a href="data_delete?id=<%= data.getEid() %>" class="btn btn-danger btn-sm">Delete</a>
                                    </td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        <% } else { %>
            <div class="alert alert-warning text-center">
                <h1>No Events Found</h1>
            </div>
        <% } %>

        <div class="btn-container">
            <a href="./" class="btn btn-success">Add Event</a>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
