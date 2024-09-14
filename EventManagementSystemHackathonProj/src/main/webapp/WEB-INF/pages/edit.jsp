<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.eventmanagement.entity.*"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Event</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        .container {
            margin-top: 30px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .card-header {
            background-color: #007bff;
            color: white;
            text-align: center;
        }

        .btn-container {
            text-align: center;
            margin-top: 20px;
        }

        .btn-container a {
            margin: 0 15px;
        }
    </style>
</head>

<body>
<% Event event = (Event)request.getAttribute("event");%>
    <div class="container">
        <div class="row">
            <div class="col-md-8 offset-md-2">
                <div class="card">
                    <div class="card-header">
                        <h5 class="card-title">Create Event</h5>
                    </div>
                    <div class="card-body">
                        <form action="update" method="POST">
                            <div class="form-group">
                                <label for="title">Title</label>
                                <input type="text" class="form-control"  id="title" name=  placeholder="Enter event title" required>
                            </div>

                            <div class="form-group">
                                <label for="location">Location</label>
                                <input type="text" class="form-control"  id="location" name="location" placeholder="Enter event location" required>
                            </div>

                            <div class="form-group">
                                <label for="datetime">Date and Time</label>
                                <input type="datetime-local" class="form-control"  id="datetime" name="datetime" required>
                            </div>

                            <div class="form-group">
                                <label for="description">Description</label>
                                <textarea class="form-control" id="description"  name="description" rows="3" placeholder="Enter event description" required></textarea>
                            </div>

                            <div class="form-group">
                                <label>Attendees</label>
                                <div id="attendees-list">
                                    <div class="attendee">
                                        <label for="attendeeName1">Attendee Name</label>
                                        <input type="text" class="form-control" id="attendeeName1" name="attendees[0].name"  placeholder="Enter attendee name" required>
                                        <label for="attendeeEmail1">Attendee Email</label>
                                        <input type="email" class="form-control" id="attendeeEmail1" name="attendees[0].email" placeholder="Enter attendee email" required>
                                    </div>
                                </div>
                                <button type="button" class="btn btn-primary mt-2" onclick="addAttendee()">Add Attendee</button>
                            </div>

                            <div class="text-center">
                                <input type="submit" class="btn btn-success" value="update">
                            </div>
                        </form>

                        <div class="btn-container">
                            <a href="events" class="btn btn-info">View Events</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        let attendeeCount = 1;

        function addAttendee() {
            attendeeCount++;
            const attendeeHtml = `
                <div class="attendee mt-3">
                    <label for="attendeeName${attendeeCount}">Attendee Name</label>
                    <input type="text" class="form-control" id="attendeeName${attendeeCount}" name="attendees[${attendeeCount - 1}].name" placeholder="Enter attendee name" required>
                    <label for="attendeeEmail${attendeeCount}">Attendee Email</label>
                    <input type="email" class="form-control" id="attendeeEmail${attendeeCount}" name="attendees[${attendeeCount - 1}].email" placeholder="Enter attendee email" required>
                </div>
            `;
            document.getElementById('attendees-list').insertAdjacentHTML('beforeend', attendeeHtml);
        }
    </script>

    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
