/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package net.java.usermanagement.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.java.usermanagement.model.SignUp;
import net.java.usermanagement.model.Users;
import net.java.usermangement.dao.UserDao;

/**
 *
 * @author USER
 */
@WebServlet(name = "userServerlet", urlPatterns = {"/"})
public class userServerlet extends HttpServlet {

    private UserDao userDao;

    public userServerlet() {
        this.userDao = new UserDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("loggedInUser") == null) {
            // If the user is not logged in, redirect to the login page for secured pages
            String action = request.getServletPath();
//            switch (action) {
//                case "/addNew":
//                    showNewForm(request, response);
//                    break;
//                case "/loginUser":
//                    loginForm(request, response);
//                    break;
//                case "/signupUser":
//                    signupForm(request, response);
//                    break;
//                case "/signup":
//                try {
//                    register(request, response);
//                } catch (Exception e) {
//
//                    e.printStackTrace();
//
//                }
//            }

            if (!action.equals("/loginUser") && !action.equals("/signupUser") && !action.equals("/loginCh")&& !action.equals("/signup")) {
                response.sendRedirect("Login-form.jsp");
                return;
            }
        }

        String action = request.getServletPath();

        switch (action) {
            case "/addNew":
                showNewForm(request, response);
                break;
            case "/loginUser":
                loginForm(request, response);
                break;
            case "/signupUser":
                signupForm(request, response);
                break;
            case "/logout":
                logoutUsers(request, response);
                break;
            case "/insert":
                try {
                inserts(request, response);
            } catch (Exception e) {

                e.printStackTrace();

            }

            break;
            case "/signup":
                try {
                register(request, response);
            } catch (Exception e) {

                e.printStackTrace();

            }

            break;
            case "/loginCh":
                try {
                loginCheck(request, response);
            } catch (Exception e) {
                e.printStackTrace();

            }
            break;
            case "/delete":
                 try {
                delete(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }

            break;
            case "/edit":
                try {
                ShowEdit(request, response);
            } catch (Exception e) {
                e.printStackTrace();

            }
            break;
            case "/update":
                try {
                update(request, response);
            } catch (Exception e) {
                e.printStackTrace();

            }
            break;
            case "/search":
                try {
                searchUser(request, response);
            } catch (Exception e) {
                e.printStackTrace();

            }
            break;
            default:
                try {
                listUser(request, response);
            } catch (Exception e) {
                e.printStackTrace();

            }
            break;
        }

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void loginForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Login-form.jsp");
        dispatcher.forward(request, response);
    }
    private void dashboard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Dashboard.jsp");
        dispatcher.forward(request, response);
    }

    private void signupForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Signup-form.jsp");
        dispatcher.forward(request, response);
    }

    protected void inserts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        Users newUser = new Users(name, email, country);
        userDao.insert(newUser);
        System.out.println("insert sucess");
        response.sendRedirect("list");

    }

    protected void register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String pss = request.getParameter("passwords");

        SignUp regis = new SignUp(name, email, phone, pss);

        userDao.signup(regis);
        System.out.println("insert sucess");
        response.sendRedirect("list");

    }

    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        userDao.deleteUser(id);

        response.sendRedirect("list");

    }

    protected void ShowEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Users edit = userDao.seclectUser(id);
        request.setAttribute("user", edit);
        RequestDispatcher dispatch = request.getRequestDispatcher("user-form.jsp");
        dispatch.forward(request, response);

    }

    protected void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        Users newUpdate = new Users(id, name, email, country);
        userDao.update(newUpdate);

        response.sendRedirect("list");

    }

    protected void listUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 4; // Change this value to set the number of records per page
        int startingNumber = (page - 1) * recordsPerPage + 1;

// Set the starting number as an attribute in the request object
        request.setAttribute("startingNumber", startingNumber);

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int offset = (page - 1) * recordsPerPage;

        List<Users> listUser = userDao.selectUsersWithPagination(offset, recordsPerPage);
        request.setAttribute("listUser", listUser);

        int totalRecords = userDao.getTotalRecords(); // Get the total number of records

        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
        int totalUser = userDao.getTotalRecords();
        request.setAttribute("totalUser", totalUser);

        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        RequestDispatcher dispatch = request.getRequestDispatcher("user-form-list.jsp");
        dispatch.forward(request, response);
    }

//    protected void listUser(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        List<Users> listUser = userDao.seclectAllUser();
//        request.setAttribute("listUser", listUser);
//        RequestDispatcher dispatch = request.getRequestDispatcher("user-form-list.jsp");
//        dispatch.forward(request, response);
//
//    }
//    protected void CountUser(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//       int totalUser = userDao.getTotalRecords();
//       request.setAttribute("totalUser", totalUser);
//
//    }
    protected void searchUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");

        List<Users> searchResults = userDao.seclectUserByname(name);
        request.setAttribute("searchResults", searchResults);

        RequestDispatcher dispatch = request.getRequestDispatcher("search-results.jsp");
        dispatch.forward(request, response);
    }

    protected void loginCheck(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("passwords");

        // Call a method from your UserDao to verify the credentials
        SignUp loggedInUser = userDao.login(email, password);

        if (loggedInUser != null) {
            // If the credentials are valid, redirect to a success page or perform other actions
            // For example, you can set the logged-in user in session for further authentication:
            request.getSession().setAttribute("loggedInUser", loggedInUser);
            response.sendRedirect("list"); // Redirect to the dashboard or another page
        } else {
            // If the credentials are invalid, redirect back to the login form or show an error message
            request.setAttribute("loginError", "Invalid email or password");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Login-form.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void logoutUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect("Login-form.jsp");

    }

}
