<%--
  Created by IntelliJ IDEA.
  User: hasun
  Date: 7/17/2025
  Time: 10:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="dark">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Scheduled Transfer Confirmed</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        tailwind.config = {
            darkMode: 'class'
        }
    </script>
</head>
<body class="bg-gray-100 dark:bg-gray-900 text-gray-800 dark:text-gray-100 min-h-screen flex items-center justify-center p-6">
<div class="max-w-xl bg-white dark:bg-slate-800 rounded-lg shadow-lg p-8 text-center">
    <div class="flex justify-center mb-4">
        <svg class="w-12 h-12 text-blue-600 dark:text-blue-400" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" d="M9 12l2 2 4-4m-7 4a7 7 0 1114 0 7 7 0 01-14 0z" />
        </svg>
    </div>
    <h1 class="text-2xl font-bold mb-2">Scheduled Transfer Confirmed!</h1>
    <p class="mb-4">Your transaction has been successfully scheduled and will be processed at the specified date and time.</p>
    <a href="${pageContext.request.contextPath}/customer/" class="inline-block mt-4 px-6 py-2 bg-indigo-700 hover:bg-indigo-800 text-white rounded-md transition">
        Back to Dashboard
    </a>
</div>
</body>
</html>

