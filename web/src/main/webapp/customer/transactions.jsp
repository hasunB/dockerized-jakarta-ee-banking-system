<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="org.example.ee.core.service.TransferService" %><%--
  Created by IntelliJ IDEA.
  User: hasun
  Date: 7/17/2025
  Time: 11:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="dark">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Dashboard</title>
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
    <%
        String nic = session.getAttribute("customerNic").toString();
    %>
    <aside class="w-64 bg-white dark:bg-slate-800 shadow-md p-6">
        <h2 class="text-2xl font-bold text-indigo-700 dark:text-indigo-300 mb-6">Customer Panel</h2>
        <div class="mb-6 text-sm text-gray-600 dark:text-gray-300">Logged in as: <span class="font-medium"><%=nic%></span></div>
        <nav class="space-y-4">
            <a href="${pageContext.request.contextPath}/customer/" class="block text-gray-700 dark:text-gray-300 hover:text-indigo-600 dark:hover:text-indigo-400">Dashboard</a>
            <a href="#" class="block text-gray-700 dark:text-gray-300 hover:text-indigo-600 dark:hover:text-indigo-400">Transfer</a>
            <a href="#" class="block font-semibold text-indigo-600 dark:text-indigo-400">Transactions</a>
            <a href="#" class="block text-gray-700 dark:text-gray-300 hover:text-indigo-600 dark:hover:text-indigo-400">Settings</a>
            <a href="#" class="block text-gray-700 dark:text-gray-300 hover:text-indigo-600 dark:hover:text-indigo-400">Profile</a>
        </nav>
    </aside>

    <!-- Main Content -->
    <main class="flex-1 p-6">
        <h1 class="text-2xl font-bold text-indigo-800 dark:text-indigo-300 mb-6">Transaction Log</h1>
        <div class="overflow-x-auto">
            <table class="min-w-full text-sm text-left text-gray-500 dark:text-gray-300">
                <thead class="text-xs text-gray-700 uppercase bg-gray-100 dark:bg-slate-700 dark:text-gray-200">
                <tr>
                    <th scope="col" class="px-6 py-3">Date</th>
                    <th scope="col" class="px-6 py-3">Type</th>
                    <th scope="col" class="px-6 py-3">To/From</th>
                    <th scope="col" class="px-6 py-3">Amount</th>
                    <th scope="col" class="px-6 py-3">Status</th>
                </tr>
                </thead>
                <tbody class="bg-white dark:bg-slate-800">

<%--                <%--%>
<%--                    InitialContext ctx = null;--%>
<%--                    try {--%>

<%--                        ctx = new InitialContext();--%>
<%--                        --%>
<%--                        --%>
<%--                        --%>
<%--                        TransferService transferService = (TransferService) ctx.lookup("java:global/banking-system-ear/account-module/TransferServiceBean");--%>
<%--                        List<Account> registeredAccounts = accountService.getAccount(nic);--%>

<%--                        for (Account account : registeredAccounts){--%>
<%--                                %>--%>
<%--                                <option value="<%= account.getAccountNumber()%>"><%= account.getAccountNumber()%> - <%= account.getBalance()%> - <%= account.getAccountType()%></option>--%>

<%--                                <%--%>
<%--                        }--%>


<%--                    } catch (NamingException e) {--%>
<%--                        throw new RuntimeException(e);--%>
<%--                    }--%>
<%--                %>--%>

                    <tr class="border-b border-gray-200 dark:border-slate-700">
                        <td class="px-6 py-4">2025-07-14</td>
                        <td class="px-6 py-4">Transfer</td>
                        <td class="px-6 py-4">Account XXXX5678</td>
                        <td class="px-6 py-4 text-green-500">$500.00</td>
                        <td class="px-6 py-4">Completed</td>
                    </tr>
                    <tr class="border-b border-gray-200 dark:border-slate-700">
                        <td class="px-6 py-4">2025-07-13</td>
                        <td class="px-6 py-4">Scheduled</td>
                        <td class="px-6 py-4">Account XXXX7890</td>
                        <td class="px-6 py-4 text-yellow-400">$200.00</td>
                        <td class="px-6 py-4">Pending</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </main>
</div>
</body>
</html>
