<%--
  Created by IntelliJ IDEA.
  User: hasun
  Date: 7/16/2025
  Time: 3:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="dark">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transfer Successful</title>
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
        <svg class="w-12 h-12 text-green-600 dark:text-green-400" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" d="M5 13l4 4L19 7" />
        </svg>
    </div>
    <h1 class="text-2xl font-bold mb-2">Transfer Successful!</h1>
    <p class="mb-4">Your funds have been successfully transferred to the recipient's account.</p>
    <a href="${pageContext.request.contextPath}/customer/" class="inline-block mt-4 px-6 py-2 bg-indigo-700 hover:bg-indigo-800 text-white rounded-md transition">
        Back to Dashboard
    </a>
</div>
</body>
</html>

