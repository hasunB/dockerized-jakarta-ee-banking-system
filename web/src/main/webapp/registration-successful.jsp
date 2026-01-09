<%--
  Created by IntelliJ IDEA.
  User: hasun
  Date: 7/10/2025
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="dark">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Successful</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        tailwind.config = {
            darkMode: 'class'
        }
    </script>
</head>
<body class="bg-gray-100 dark:bg-gray-900">
<div class="min-h-screen flex items-center justify-center px-4">
    <div class="bg-white dark:bg-gray-800 shadow-xl rounded-2xl p-8 w-full max-w-md text-center border border-gray-200 dark:border-gray-700">
        <svg class="mx-auto mb-4 w-16 h-16 text-green-500" fill="none" stroke="currentColor" stroke-width="1.5" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" d="M4.5 12.75l6 6 9-13.5" />
        </svg>
        <h2 class="text-2xl font-bold text-blue-800 dark:text-blue-300 mb-4">Registration Successful</h2>
        <p class="text-gray-700 dark:text-gray-300 mb-6">You have been successfully registered. We're reviewing your account. Once it's approved, We will notify you with a mail</p>
        <a href="/" class="inline-block bg-blue-700 hover:bg-blue-800 text-white font-semibold py-2 px-6 rounded-md transition duration-300">
            Go to Login
        </a>
    </div>
</div>
</body>
</html>

