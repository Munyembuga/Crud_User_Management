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

        String action = request.getServletPath();

        switch (action) {
            case "/addNew":
                showNewForm(request, response);
                break;
            case "/insert":
                try {
                inserts(request, response);
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
        List<Users> listUser = userDao.seclectAllUser();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatch = request.getRequestDispatcher("user-form-list.jsp");
        dispatch.forward(request, response);

    }
    protected void searchUser(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    int id =Integer.parseInt( request.getParameter("id"));

    List<Users> searchResults = userDao.seclectUserByname(id);
    request.setAttribute("searchResults", searchResults);

    RequestDispatcher dispatch = request.getRequestDispatcher("search-results.jsp");
    dispatch.forward(request, response);
}
}
