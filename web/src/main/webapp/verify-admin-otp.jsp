<%--
  Created by IntelliJ IDEA.
  User: hasun
  Date: 7/11/2025
  Time: 1:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="dark">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin OTP Verification</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        tailwind.config = {
            darkMode: 'class'
        }
    </script>
    <style>
        input[type="text"]::-webkit-outer-spin-button,
        input[type="text"]::-webkit-inner-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }
        input[type="text"] {
            -moz-appearance: textfield;
        }
    </style>
</head>
<body class="bg-gray-100 dark:bg-gray-900">
<div class="min-h-screen flex items-center justify-center px-4">
    <div class="bg-white dark:bg-slate-800 shadow-xl rounded-2xl p-8 w-full max-w-md border border-gray-200 dark:border-slate-700">
        <h2 class="text-2xl font-bold text-center text-indigo-800 dark:text-indigo-300 mb-4">Verify Admin Access</h2>
        <p class="text-center text-gray-600 dark:text-gray-400 mb-6">Enter the 6-digit code sent to your registered admin email.</p>

        <form class="space-y-6" action="${pageContext.request.contextPath}/verify-admin-otp" method="post">
            <div class="flex justify-center gap-3">
                <input type="text" maxlength="1" class="w-12 h-12 text-center text-xl border border-gray-300 dark:border-slate-600 rounded-md bg-white dark:bg-slate-700 text-gray-900 dark:text-white focus:outline-none focus:ring-2 focus:ring-indigo-500" name="num0" required>
                <input type="text" maxlength="1" class="w-12 h-12 text-center text-xl border border-gray-300 dark:border-slate-600 rounded-md bg-white dark:bg-slate-700 text-gray-900 dark:text-white focus:outline-none focus:ring-2 focus:ring-indigo-500" name="num1" required>
                <input type="text" maxlength="1" class="w-12 h-12 text-center text-xl border border-gray-300 dark:border-slate-600 rounded-md bg-white dark:bg-slate-700 text-gray-900 dark:text-white focus:outline-none focus:ring-2 focus:ring-indigo-500" name="num2" required>
                <input type="text" maxlength="1" class="w-12 h-12 text-center text-xl border border-gray-300 dark:border-slate-600 rounded-md bg-white dark:bg-slate-700 text-gray-900 dark:text-white focus:outline-none focus:ring-2 focus:ring-indigo-500" name="num3" required>
                <input type="text" maxlength="1" class="w-12 h-12 text-center text-xl border border-gray-300 dark:border-slate-600 rounded-md bg-white dark:bg-slate-700 text-gray-900 dark:text-white focus:outline-none focus:ring-2 focus:ring-indigo-500" name="num4" required>
                <input type="text" maxlength="1" class="w-12 h-12 text-center text-xl border border-gray-300 dark:border-slate-600 rounded-md bg-white dark:bg-slate-700 text-gray-900 dark:text-white focus:outline-none focus:ring-2 focus:ring-indigo-500" name="num5" required>
            </div>

            <button type="submit" class="w-full bg-indigo-700 text-white font-semibold py-2 px-4 rounded-md hover:bg-indigo-800 transition duration-300">
                Verify OTP
            </button>

            <p class="text-center text-sm text-gray-500 dark:text-gray-400 mt-4">
                Didn't receive the code?
                <a href="#" class="text-indigo-600 dark:text-indigo-400 hover:underline">Resend</a>
            </p>
        </form>
    </div>
</div>
</body>
</html>

