<%--
  Created by IntelliJ IDEA.
  User: hasun
  Date: 7/9/2025
  Time: 6:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="dark">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Login - Banking System</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        tailwind.config = {
            darkMode: 'class'
        }
    </script>
</head>
<body class="bg-gray-100 dark:bg-gray-900">
<div class="min-h-screen flex items-center justify-center px-4">
    <div class="bg-white dark:bg-gray-800 shadow-xl rounded-2xl p-8 w-full max-w-md border border-gray-200 dark:border-gray-700">
        <h2 class="text-2xl font-bold text-center text-blue-800 dark:text-blue-300 mb-6">Bank Account Login</h2>
        <form class="space-y-6" action="${pageContext.request.contextPath}/login" method="post">
            <div>
                <label class="block text-sm font-medium text-gray-700 dark:text-gray-300">NIC</label>
                <input type="text" placeholder="0000000000" class="mt-1 block w-full px-4 py-2 border border-gray-300 dark:border-gray-600 rounded-md bg-white dark:bg-gray-700 text-gray-900 dark:text-gray-100 focus:ring-2 focus:ring-blue-500 focus:outline-none" name="nic" required>
            </div>

            <div>
                <label class="block text-sm font-medium text-gray-700 dark:text-gray-300">Password</label>
                <input type="password" placeholder="••••••••" class="mt-1 block w-full px-4 py-2 border border-gray-300 dark:border-gray-600 rounded-md bg-white dark:bg-gray-700 text-gray-900 dark:text-gray-100 focus:ring-2 focus:ring-blue-500 focus:outline-none" name="password" required>
            </div>

            <div class="flex items-center justify-between">
                <label class="inline-flex items-center">
                    <input type="checkbox" class="form-checkbox text-blue-600 dark:text-blue-400">
                    <span class="ml-2 text-sm text-gray-700 dark:text-gray-300">Remember me</span>
                </label>
                <a href="#" class="text-sm text-blue-600 dark:text-blue-400 hover:underline">Forgot password?</a>
            </div>

            <button type="submit" class="w-full bg-blue-700 text-white font-semibold py-2 px-4 rounded-md hover:bg-blue-800 transition duration-300">
                Sign In
            </button>

            <p class="text-center text-sm text-gray-500 dark:text-gray-400 mt-4">
                Don’t have an account?
                <a href="register.jsp" class="text-blue-600 dark:text-blue-400 hover:underline">Register</a>
            </p>
            <p class="text-center text-sm text-gray-500 dark:text-gray-400 mt-1">
                <a href="admin-login.jsp" class="text-blue-600 dark:text-blue-400 hover:underline">admin</a>
            </p>
        </form>
    </div>
</div>
</body>
</html>


