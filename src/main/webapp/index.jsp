<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Input Form</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #333; /* Set default dark background color */
            color: #fff; /* Set default light text color */
            transition: background-color 0.3s ease, color 0.3s ease;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            display: grid;
            justify-content: center;
            width: inherit;
            border-radius: 10px;
            margin-top: 50px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            color: #333;
        }
        form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        input[type="text"] {
            width: 500px; 
            padding: 15px;
            margin-bottom: 20px; 
            border: 1px solid #ccc;
            border-radius: 5px;
            outline: none;
            text-align: left; 
        }
        input[type="submit"] {
            width: 200px;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .start-btn {
            position: fixed;
            right: 10px;
            bottom: 10px;
        }
        .chatbox {
            width: 500px;
            height: 680px;
            position: fixed;
            right: 10px;
            bottom: 10px;
            display: none;
        }
        i{
            font-size: 50px;
        }
        .toggle-theme-btn {
            margin-top: 20px;
            cursor: pointer;
            font-size: 24px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Text To Vector Conversion</h2>
        <form action="Embedding" method="post">
            <input type="text" name="userInput" placeholder="Enter your text here">
            <input type="submit" value="Submit">
        </form>
        <div class="toggle-theme-btn" onclick="toggleTheme()">Toggle Theme</div>
    </div>
    <div class="start-btn">
        <button class="btn btn-primary"><i id="StartChatButton" class="fa-solid fa-comments"></i></button>
    </div>
    <iframe id="chatFrame" class="chatbox" src="https://chat.openai.com/"></iframe>

    <script>
        document.getElementById('StartChatButton').addEventListener('click', function() {
            document.querySelector('.start-btn').style.display = 'none';
            document.querySelector('.chatbox').style.display = 'block';
        });

        // Function to handle clicks outside the chatbox
        function handleClickOutside(event) {
            const chatbox = document.querySelector('.chatbox');
            const startButton = document.querySelector('.start-btn');

            // Check if the clicked element is the chatbox or the start button
            if (!chatbox.contains(event.target) && event.target !== startButton && event.target.id !== 'StartChatButton') {
                chatbox.style.display = 'none';
                startButton.style.display = 'block';
            }
        }

        // Add event listener to the document body to handle clicks
        document.body.addEventListener('click', handleClickOutside);

        // Function to toggle theme
        function toggleTheme() {
            const body = document.body;
            body.classList.toggle('dark-theme');
        }
    </script>
</body>
</html>
