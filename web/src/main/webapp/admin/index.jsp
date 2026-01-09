<%@ page import="org.example.ee.core.model.Admin" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="org.example.ee.core.service.AdminService" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.ee.core.model.User" %><%--
  Created by IntelliJ IDEA.
  User: hasun
  Date: 7/10/2025
  Time: 12:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="dark">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        tailwind.config = {
            darkMode: 'class'
        }
    </script>
</head>
<body class="bg-gray-100 dark:bg-gray-900">
<div class="min-h-screen flex">
    <!-- Sidebar -->
    <aside class="w-64 bg-white dark:bg-slate-800 shadow-md p-6">
        <h2 class="text-2xl font-bold text-indigo-700 dark:text-indigo-300 mb-6">Admin Panel</h2>
        <%
            String adminName = session.getAttribute("adminName").toString();

        %>
        <div class="mb-6 text-sm text-gray-600 dark:text-gray-300">Logged in as: <span class="font-medium"><%= adminName %></span></div>
        <nav class="space-y-4">
            <a href="#" class="block text-gray-700 dark:text-gray-300 hover:text-indigo-600 dark:hover:text-indigo-400">Dashboard</a>
            <a href="#" class="block text-gray-700 dark:text-gray-300 hover:text-indigo-600 dark:hover:text-indigo-400">Users</a>
            <a href="#" class="block text-gray-700 dark:text-gray-300 hover:text-indigo-600 dark:hover:text-indigo-400">Customers</a>
            <a href="#" class="block text-gray-700 dark:text-gray-300 hover:text-indigo-600 dark:hover:text-indigo-400">Reports</a>
            <a href="#" class="block text-gray-700 dark:text-gray-300 hover:text-indigo-600 dark:hover:text-indigo-400">Settings</a>
            <a href="#" class="block text-gray-700 dark:text-gray-300 hover:text-indigo-600 dark:hover:text-indigo-400">Profile</a>
        </nav>
    </aside>

    <!-- Main Content -->
    <main class="flex-1 p-6">
        <h1 class="text-3xl font-bold text-indigo-800 dark:text-indigo-300 mb-6">Registered Users</h1>
        <div class="overflow-x-auto">
            <table class="min-w-full table-auto border border-gray-300 dark:border-slate-600">
                <thead class="bg-gray-200 dark:bg-slate-700 text-gray-700 dark:text-gray-300">
                <tr>
                    <th class="px-4 py-2 text-left">Nic</th>
                    <th class="px-4 py-2 text-left">Name</th>
                    <th class="px-4 py-2 text-left">Email</th>
                    <th class="px-4 py-2 text-left">Status</th>
                    <th class="px-4 py-2 text-left">Actions</th>
                </tr>
                </thead>
                <tbody class="bg-white dark:bg-slate-800 text-gray-900 dark:text-white">
                <%

                    InitialContext ctx = null;

                    try {

                        ctx = new InitialContext();
                        AdminService adminService = (AdminService) ctx.lookup("java:global/banking-system-ear/user-module/AdminServiceBean");
                        List<User>  registeredUsers = adminService.getAllUsers();

                        for(User user: registeredUsers){
                            %>
                                <tr class="border-t border-gray-300 dark:border-slate-700">
                                    <td class="px-4 py-2"><%= user.getNic()%></td>
                                    <td class="px-4 py-2"><%= user.getName()%></td>
                                    <td class="px-4 py-2"><%= user.getEmail()%></td>
                                    <td class="px-4 py-2">Pending</td>
                                    <td class="px-4 py-2 space-x-2">
                                        <form action="approve-user" method="post" style="display:inline">
                                            <input type="hidden" name="nic" value="<%= user.getNic()%>">
                                            <button class="bg-green-600 hover:bg-green-700 text-white px-3 py-1 rounded" type="submit">Approve</button>
                                        </form>
                                        <form action="block-user" method="post" style="display:inline">
                                            <input type="hidden" name="nic" value="<%= user.getNic()%>">
                                            <button class="bg-red-600 hover:bg-red-700 text-white px-3 py-1 rounded" type="submit">Block</button>
                                        </form>
                                    </td>
                                </tr>
                            <%
                        }


                    } catch (NamingException e) {
                        throw new RuntimeException(e);
                    }

                %>

                <!-- More user rows as needed -->
                </tbody>
            </table>
        </div>
    </main>
</div>
</body>
</html>

