package com.example.ecommerce.controller;

import com.example.ecommerce.dao.AdminDao;
import com.example.ecommerce.dto.LoginRequestDto;
import com.example.ecommerce.models.Users;
import com.example.ecommerce.serviceImpl.UserServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.logging.Logger;



@WebServlet(name = "admin", value = "/admin")
public class AdminController extends HttpServlet{



        private Logger logger = Logger.getGlobal();

        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            String adminLogin = req.getParameter("adminlogin") == null ? null : req.getParameter("adminlogin");
            if (adminLogin != null) {
                RequestDispatcher dispatcher = req.getRequestDispatcher("admin.jsp");
                dispatcher.forward(req, resp);
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("admin-login.jsp");
                dispatcher.forward(req, resp);
            }
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            System.out.println("Displaying admin info now...");

            UserServiceImpl userService = new UserServiceImpl();
            AdminDao adminDao = new AdminDao();

            // "^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$"
            //conditional rendering...


            /////login admin
            String login = req.getParameter("adminlogin") == null ? null : req.getParameter("adminlogin");
            if (login != null) {
                LoginRequestDto loggedInAdmin = new LoginRequestDto(req.getParameter("email"), req.getParameter("password"));
//            Users user = userService.findUserByEmail.apply(loggedInAdmin);
                System.out.println(loggedInAdmin);
                Users admin = adminDao.findAdmin.apply(loggedInAdmin);
                System.out.println(admin);
                loggedInAdmin.setHashPassword(admin.getPassword());
                Boolean verified = userService.verifyPassword.apply(loggedInAdmin);
                System.out.println(verified);
                if (verified) {
                    HttpSession session = req.getSession();
                    session.setAttribute("userID", admin.getId());
                    session.setAttribute("userName", admin.getUsername());
                    System.out.println("Admin1 logged In");
                    RequestDispatcher dispatcher = req.getRequestDispatcher("admin.jsp");
                    dispatcher.forward(req, resp);
                    //System.out.println("Admin logged In");
                } else {
                    RequestDispatcher dispatcher = req.getRequestDispatcher("admin-login.jsp");
                    dispatcher.forward(req, resp);
                    System.out.println("Admin not logged In");

                }
            }

///////////////register admin
//            UserDto adminDto = new UserDto();
//            adminDto.setUsername(req.getParameter("username"));
//            adminDto.setEmail(req.getParameter("email"));
//            adminDto.setPassword(req.getParameter("password"));
//            logger.info("SAVED USER --> " + adminDao.saveAdmin.apply(new Users(adminDto)));
//            RequestDispatcher dispatcher = req.getRequestDispatcher("admin-login.jsp");
//            dispatcher.forward(req, resp);
        }
}
