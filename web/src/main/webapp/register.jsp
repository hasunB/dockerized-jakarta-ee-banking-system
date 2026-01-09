<%--
  Created by IntelliJ IDEA.
  User: hasun
  Date: 7/9/2025
  Time: 6:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="dark">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration - banking System</title>
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
        <h2 class="text-2xl font-bold text-center text-blue-800 dark:text-blue-300 mb-6">Create Your Bank Account</h2>
        <form class="space-y-4" action="${pageContext.request.contextPath}/register" method="post">
            <div class="grid grid-cols-2 gap-4">
                <div>
                    <label class="block text-sm font-medium text-gray-700 dark:text-gray-300">First Name</label>
                    <input type="text" placeholder="John" class="mt-1 block w-full px-4 py-2 border border-gray-300 dark:border-gray-600 rounded-md bg-white dark:bg-gray-700 text-gray-900 dark:text-gray-100 focus:ring-2 focus:ring-blue-500 focus:outline-none" name="firstName" required>
                </div>
                <div>
                    <label class="block text-sm font-medium text-gray-700 dark:text-gray-300">Last Name</label>
                    <input type="text" placeholder="Doe" class="mt-1 block w-full px-4 py-2 border border-gray-300 dark:border-gray-600 rounded-md bg-white dark:bg-gray-700 text-gray-900 dark:text-gray-100 focus:ring-2 focus:ring-blue-500 focus:outline-none" name="lastName" required>
                </div>
            </div>

            <div>
                <label class="block text-sm font-medium text-gray-700 dark:text-gray-300">Email Address</label>
                <input type="email" placeholder="you@example.com" class="mt-1 block w-full px-4 py-2 border border-gray-300 dark:border-gray-600 rounded-md bg-white dark:bg-gray-700 text-gray-900 dark:text-gray-100 focus:ring-2 focus:ring-blue-500 focus:outline-none" name="email" required>
            </div>

            <div>
                <label class="block text-sm font-medium text-gray-700 dark:text-gray-300">Mobile Number</label>
                <input type="tel" placeholder="+1 234 567 8900" class="mt-1 block w-full px-4 py-2 border border-gray-300 dark:border-gray-600 rounded-md bg-white dark:bg-gray-700 text-gray-900 dark:text-gray-100 focus:ring-2 focus:ring-blue-500 focus:outline-none" name="contact" required>
            </div>

            <div>
                <label class="block text-sm font-medium text-gray-700 dark:text-gray-300">Nic</label>
                <input type="password" placeholder="••••••••" class="mt-1 block w-full px-4 py-2 border border-gray-300 dark:border-gray-600 rounded-md bg-white dark:bg-gray-700 text-gray-900 dark:text-gray-100 focus:ring-2 focus:ring-blue-500 focus:outline-none" name="nic" required>
            </div>

            <div>
                <label class="block text-sm font-medium text-gray-700 dark:text-gray-300">Password</label>
                <input type="password" placeholder="••••••••" class="mt-1 block w-full px-4 py-2 border border-gray-300 dark:border-gray-600 rounded-md bg-white dark:bg-gray-700 text-gray-900 dark:text-gray-100 focus:ring-2 focus:ring-blue-500 focus:outline-none" name="password" required>
            </div>

            <button type="submit" class="w-full bg-blue-700 text-white font-semibold py-2 px-4 rounded-md hover:bg-blue-800 transition duration-300">
                Register Account
            </button>

            <p class="text-center text-sm text-gray-500 dark:text-gray-400 mt-4">
                Already have an account?
                <a href="#" class="text-blue-600 dark:text-blue-400 hover:underline">Sign in</a>
            </p>
        </form>
    </div>
</div>
</body>
</html>

