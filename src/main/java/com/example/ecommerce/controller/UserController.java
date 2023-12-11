package com.example.ecommerce.controller;

import com.example.ecommerce.dao.ProductDao;
import com.example.ecommerce.dao.UserDao;
import com.example.ecommerce.dto.LoginRequestDto;
import com.example.ecommerce.dto.UserDto;
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

@WebServlet(name="user", value = "/user")
public class UserController extends HttpServlet {

    private Logger logger = Logger.getGlobal();

protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String login = req.getParameter("login") == null ? null : req.getParameter("login");

    if (login != null) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
        dispatcher.forward(req, resp);
    } else {
        RequestDispatcher dispatcher = req.getRequestDispatcher("sign-up.jsp");
        dispatcher.forward(req, resp);
    }
}
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Displaying user info now...");

        UserServiceImpl userService = new UserServiceImpl();
        UserDao userDao = new UserDao();
        // "^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$"
        //conditional rendering...
        String login = req.getParameter("login")==null? null:req.getParameter("login");

        if (login!=null){
            LoginRequestDto loggedInUser = new LoginRequestDto(req.getParameter("email"), req.getParameter("password"));
//            Users user = userService.findUserByEmail.apply(loggedInUser);
            System.out.println(loggedInUser);
            Users user =  userDao.findUser.apply(loggedInUser);
            System.out.println(user);
            loggedInUser.setHashPassword(user.getPassword());
            Boolean verified = userService.verifyPassword.apply(loggedInUser);
            System.out.println(verified);
            if (verified){
                HttpSession session = req.getSession();
                session.setAttribute("userID", user.getId());
                session.setAttribute("userName", user.getUsername());
                req.setAttribute("product-list", new ProductDao().findAllProducts.get());
                RequestDispatcher dispatcher = req.getRequestDispatcher("dashboard.jsp");
                dispatcher.forward(req, resp);
                System.out.println("Successfull Logged In");
            }
            else{
                System.out.println("Error: Incorrect Password");
                RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
                dispatcher.forward(req, resp);
                System.out.println("Login details not found");
            }
        } else {
            System.out.println("Error: Email not found");
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
        }


        UserDto userDto = new UserDto();
        userDto.setUsername(req.getParameter("username"));
        userDto.setEmail(req.getParameter("email"));
        userDto.setPassword(req.getParameter("password"));
        logger.info("SAVED USER --> "+userDao.saveUser.apply(new Users(userDto)));
        RequestDispatcher dispatcher =req.getRequestDispatcher("successful.jsp");
        dispatcher.forward(req, resp);
    }
}
