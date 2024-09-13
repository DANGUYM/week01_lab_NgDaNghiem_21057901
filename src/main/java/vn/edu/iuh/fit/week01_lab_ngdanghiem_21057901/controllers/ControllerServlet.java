package vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.entities.*;
import vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.servicies.AccountService;
import vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.servicies.LogService;
import vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.servicies.RoleService;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "ControllerServlet", value = "/ControllerServlet")
public class ControllerServlet extends HttpServlet {
    private AccountService accountService;
    private LogService logService;
    private RoleService roleService;

    @Override
    public void init() throws ServletException {
        accountService = new AccountService();
        logService = new LogService();
        roleService = new RoleService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "login":
                login(request, response);
                break;
//            case "addAccount":
//                addAccount(request, response);
//                break;
//            case "updateAccount":
//                updateAccount(request, response);
//                break;
//            case "deleteAccount":
//                deleteAccount(request, response);
//                break;
//            case "grantRole":
//                grantRole(request, response);
//                break;
//            case "viewAccountRoles":
//                viewAccountRoles(request, response);
//                break;
//            case "viewRoleAccounts":
//                viewRoleAccounts(request, response);
//                break;
            case "logout":
                logout(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Account account = accountService.login(email, password);

        if (account != null) {
            Log log = new Log();
            log.setAccountId(account.getAccountId());
            log.setLoginTime(LocalDateTime.now().toInstant(java.time.ZoneOffset.UTC));

            log.setLogoutTime(LocalDateTime.now().toInstant(java.time.ZoneOffset.UTC));
            log.setNotes("Not logout");

            logService.recordLogin(log);

            HttpSession session = request.getSession();
            session.setAttribute("account", account);
            if (accountService.isAdmin(account)) {
                response.sendRedirect("dashboard.jsp");
            } else {
                response.sendRedirect("userDashboard.jsp");
            }

        } else {
            response.sendRedirect("index.jsp");
        }

    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Account account = (Account) session.getAttribute("account");
            if (account != null) {
                logService.recordLogout(account.getAccountId());
                session.invalidate();
            }
        }
        response.sendRedirect("index.jsp");
    }

//    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession(false);
////        if (session != null) {
////            Account account = (Account) session.getAttribute("account");
////            if (account != null) {
////                logService.recordLogout(log);
////            }
////            session.invalidate();
////     }
//
////        Log log = logService.findByAccountId(((Account) session.getAttribute("account")).getAccountId());
////
////
////        logService.recordLogout(log);
//
//        response.sendRedirect("index.jsp");
//    }

//    private void addAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String fullName = request.getParameter("fullName");
//        String password = request.getParameter("password");
//        String email = request.getParameter("email");
//        String phone = request.getParameter("phone");
//        Account newAccount = new Account(fullName, password, email, phone, (byte) 1);
//        accountService.addAccount(newAccount);
//        response.sendRedirect("userDashboard.jsp");
//    }

//    private void updateAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String accountId = request.getParameter("accountId");
//        String fullName = request.getParameter("fullName");
//        String password = request.getParameter("password");
//        String email = request.getParameter("email");
//        String phone = request.getParameter("phone");
//        Account account = accountService.getAccountById(accountId);
//        account.setFullName(fullName);
//        account.setPassword(password);
//        account.setEmail(email);
//        account.setPhone(phone);
//        accountService.updateAccount(account);
//        response.sendRedirect("userDashboard.jsp");
//    }

//    private void deleteAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String accountId = request.getParameter("accountId");
//        accountService.deleteAccount(accountId);
//        response.sendRedirect("userDashboard.jsp");
//    }

//    private void grantRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String accountId = request.getParameter("accountId");
//        String roleId = request.getParameter("roleId");
//        accountService.grantRole(accountId, roleId);
//        response.sendRedirect("userDashboard.jsp");
//    }

//    private void viewAccountRoles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String accountId = request.getParameter("accountId");
//        Account account = accountService.getAccountById(accountId);
//        request.setAttribute("roles", accountService.getRolesForAccount(account));
//        request.getRequestDispatcher("accountRoles.jsp").forward(request, response);
//    }

//    private void viewRoleAccounts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String roleId = request.getParameter("roleId");
//        request.setAttribute("accounts", roleService.getAccountsForRole(roleId));
//        request.getRequestDispatcher("roleAccounts.jsp").forward(request, response);
//    }
}
