<%@ page import="org.example.ee.core.model.User" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="org.example.ee.core.service.AccountService" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.ee.core.model.Account" %>
<%@ page import="org.example.ee.core.model.AccountType" %><%--
  Created by IntelliJ IDEA.
  User: hasun
  Date: 7/15/2025
  Time: 5:11 PM
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
            <a href="#" class="block text-gray-700 dark:text-gray-300 hover:text-indigo-600 dark:hover:text-indigo-400">Dashboard</a>
            <a href="#" class="block text-gray-700 dark:text-gray-300 hover:text-indigo-600 dark:hover:text-indigo-400">Transfer</a>
            <a href="${pageContext.request.contextPath}/customer/transactions.jsp" class="block text-gray-700 dark:text-gray-300 hover:text-indigo-600 dark:hover:text-indigo-400">Transactions</a>
            <a href="#" class="block text-gray-700 dark:text-gray-300 hover:text-indigo-600 dark:hover:text-indigo-400">Settings</a>
            <a href="#" class="block text-gray-700 dark:text-gray-300 hover:text-indigo-600 dark:hover:text-indigo-400">Profile</a>
        </nav>
    </aside>

    <!-- Main Content -->
    <main class="flex-1 p-6 space-y-10">
        <!-- Money Transfer Section -->
        <section id="transfer">
            <h1 class="text-2xl font-bold text-indigo-800 dark:text-indigo-300 mb-4">Transfer Money</h1>
            <form class="space-y-4 max-w-xl" action="${pageContext.request.contextPath}/customer/fund-transfer" method="post">
                <div>
                    <label class="block text-sm text-gray-700 dark:text-gray-300">Select Your Account</label>
                    <select name="from" class="mt-1 w-full px-4 py-2 rounded-md border border-gray-300 dark:border-slate-600 bg-white dark:bg-slate-700 text-gray-900 dark:text-white focus:ring-2 focus:ring-indigo-500">
                        <option value="">Choose Account</option>
                        <%

                            InitialContext ctx = null;
                            try {

                                ctx = new InitialContext();
                                AccountService accountService = (AccountService) ctx.lookup("java:global/banking-system-ear/account-module/AccountServiceBean");
                                //AdminService adminService = (AdminService) ctx.lookup("java:global/banking-system-ear/user-module/AdminServiceBean");
                                List<Account> registeredAccounts = accountService.getAccount(nic);

                                for (Account account : registeredAccounts){
                                    %>
                                        <option value="<%= account.getAccountNumber()%>"><%= account.getAccountNumber()%> - <%= account.getBalance()%> - <%= account.getAccountType()%></option>

                                    <%
                                }


                            } catch (NamingException e) {
                                throw new RuntimeException(e);
                            }



                        %>
                    </select>
                </div>
                <div>
                    <label class="block text-sm text-gray-700 dark:text-gray-300">Recipient Account Number</label>
                    <input type="text" placeholder="Enter account number" name="to" class="mt-1 w-full px-4 py-2 rounded-md border border-gray-300 dark:border-slate-600 bg-white dark:bg-slate-700 text-gray-900 dark:text-white focus:ring-2 focus:ring-indigo-500">
                </div>
                <div>
                    <label class="block text-sm text-gray-700 dark:text-gray-300">Amount</label>
                    <input type="number" step="0.01" name="amount" placeholder="Enter amount" class="mt-1 w-full px-4 py-2 rounded-md border border-gray-300 dark:border-slate-600 bg-white dark:bg-slate-700 text-gray-900 dark:text-white focus:ring-2 focus:ring-indigo-500">
                </div>
                <button type="submit" class="bg-indigo-700 hover:bg-indigo-800 text-white font-semibold px-6 py-2 rounded-md">
                    Send Money
                </button>
            </form>
        </section>

        <!-- Scheduled Transfers Section -->
        <section id="schedule">
            <h2 class="text-2xl font-bold text-indigo-800 dark:text-indigo-300 mb-4">Scheduled Transactions</h2>
            <form class="space-y-4 max-w-xl" action="${pageContext.request.contextPath}/customer/fund-transfer-scheduled" method="post">
                <div>
                    <label class="block text-sm text-gray-700 dark:text-gray-300">Select Your Account</label>
                    <select name="from" class="mt-1 w-full px-4 py-2 rounded-md border border-gray-300 dark:border-slate-600 bg-white dark:bg-slate-700 text-gray-900 dark:text-white focus:ring-2 focus:ring-indigo-500">
                        <option value="">Choose Account</option>
                        <%

                            InitialContext ctx2 = null;
                            try {

                                ctx2 = new InitialContext();
                                AccountService accountService = (AccountService) ctx.lookup("java:global/banking-system-ear/account-module/AccountServiceBean");
                                //AdminService adminService = (AdminService) ctx.lookup("java:global/banking-system-ear/user-module/AdminServiceBean");
                                List<Account> registeredAccounts = accountService.getAccount(nic);

                                for (Account account : registeredAccounts){
                                    %>
                                    <option value="<%= account.getAccountNumber()%>"><%= account.getAccountNumber()%> - <%= account.getBalance()%> - <%= account.getAccountType()%></option>

                                    <%
                                }


                            } catch (NamingException e) {
                                throw new RuntimeException(e);
                            }



                        %>
                    </select>
                </div>
                <div>
                    <label class="block text-sm text-gray-700 dark:text-gray-300">Recipient Account Number</label>
                    <input type="text" name="to" placeholder="Enter account number" class="mt-1 w-full px-4 py-2 rounded-md border border-gray-300 dark:border-slate-600 bg-white dark:bg-slate-700 text-gray-900 dark:text-white focus:ring-2 focus:ring-indigo-500">
                </div>
                <div>
                    <label class="block text-sm text-gray-700 dark:text-gray-300">Amount</label>
                    <input type="number" name="amount" placeholder="Enter amount" class="mt-1 w-full px-4 py-2 rounded-md border border-gray-300 dark:border-slate-600 bg-white dark:bg-slate-700 text-gray-900 dark:text-white focus:ring-2 focus:ring-indigo-500">
                </div>
                <div>
                    <label class="block text-sm text-gray-700 dark:text-gray-300">Schedule Date Time</label>
                    <input type="datetime-local" name="datetime" class="mt-1 w-full px-4 py-2 rounded-md border border-gray-300 dark:border-slate-600 bg-white dark:bg-slate-700 text-gray-900 dark:text-white focus:ring-2 focus:ring-indigo-500">
                </div>
                <button type="submit" class="bg-indigo-700 hover:bg-indigo-800 text-white font-semibold px-6 py-2 rounded-md">
                    Schedule Transfer
                </button>
            </form>
        </section>
    </main>
</div>
</body>
</html>


