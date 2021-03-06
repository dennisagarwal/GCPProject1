package com.revature.controller;

import com.revature.dto.LoginDTO;
import com.revature.model.User;
import com.revature.service.JWTService;
import com.revature.service.UserService;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.HttpSession;

public class AuthenticationController implements Controller {

    private UserService userService;
    private JWTService jwtService;
    private Handler login = (ctx) -> {
        System.out.println("Login endpoint invoked");

        LoginDTO loginInfo = ctx.bodyAsClass(LoginDTO.class);


        User user = userService.login(loginInfo.getUsername(), loginInfo.getPassword());

        String jwt = this.jwtService.createJWT(user);
        ctx.header("Access-Control-Expose-Headers", "*");
        ctx.header("Token", jwt);
        ctx.json(user);


        System.out.println(loginInfo);
    };

    public AuthenticationController() {
        this.userService = new UserService();
        this.jwtService = new JWTService();
    }



    @Override
    public void mapEndpoints(Javalin app) {
        app.post("/login", login);
//        app.post("/logout", logout);
//        app.get("/reimbursements",getAllReimbursements);


    }
}
