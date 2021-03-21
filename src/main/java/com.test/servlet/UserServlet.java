package com.test.servlet;

import com.alibaba.fastjson.JSON;
import com.test.connection.DbConnectionUtil;
import com.test.service.UserService;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 19:45 2021/3/20
 */
@WebServlet(urlPatterns = {"/user/*"})
public class UserServlet  extends HttpServlet {

    private UserService userService  ;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        resp.setContentType("application/json;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.getWriter().write(JSON.toJSONString(userService.getUser(userId)));
        String method = req.getParameter("method");

    }
}
