<%--
  Created by IntelliJ IDEA.
  User: hasun
  Date: 7/10/2025
  Time: 7:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="dark">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Login</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        tailwind.config = {
            darkMode: 'class'
        }
    </script>
</head>
<body class="bg-gray-100 dark:bg-gray-900">
<div class="min-h-screen flex items-center justify-center px-4">
    <div class="bg-white dark:bg-slate-800 shadow-xl rounded-2xl p-8 w-full max-w-md border border-gray-200 dark:border-slate-700">
        <h2 class="text-2xl font-bold text-center text-indigo-800 dark:text-indigo-300 mb-6">Admin Panel Login</h2>
        <form class="space-y-6" action="${pageContext.request.contextPath}/admin-login" method="post">
            <div>
                <label class="block text-sm font-medium text-gray-700 dark:text-gray-300">Admin NIC</label>
                <input type="text" placeholder="000000000000" class="mt-1 block w-full px-4 py-2 border border-gray-300 dark:border-slate-600 rounded-md bg-white dark:bg-slate-700 text-gray-900 dark:text-gray-100 focus:ring-2 focus:ring-indigo-500 focus:outline-none" name="nic" required>
            </div>

            <div>
                <label class="block text-sm font-medium text-gray-700 dark:text-gray-300">Password</label>
                <input type="password" placeholder="••••••••" class="mt-1 block w-full px-4 py-2 border border-gray-300 dark:border-slate-600 rounded-md bg-white dark:bg-slate-700 text-gray-900 dark:text-gray-100 focus:ring-2 focus:ring-indigo-500 focus:outline-none" name="password" required>
            </div>

            <button type="submit" class="w-full bg-indigo-700 text-white font-semibold py-2 px-4 rounded-md hover:bg-indigo-800 transition duration-300">
                Sign In as Admin
            </button>

            <p class="text-center text-sm text-gray-500 dark:text-gray-400 mt-4">
                Not an admin?
                <a href="" class="text-indigo-600 dark:text-indigo-400 hover:underline">Go to User Login</a>
            </p>
        </form>
    </div>
</div>
</body>
</html>

