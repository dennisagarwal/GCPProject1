package com.revature.main;

import com.revature.controller.ReimbursementController;
import com.revature.controller.AuthenticationController;
import com.revature.controller.Controller;
import com.revature.controller.ExceptionController;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;
import com.revature.hashing.MD5;
import com.revature.model.User;
import com.revature.utility.ConnectionUtility;
import io.javalin.Javalin;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Driver {

    public static void main(String[] args) throws NoSuchAlgorithmException {
//        User user = new User()
//        try{
//            System.out.println(dao.getAllReimbursements());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        ReimbursementDao dao = new ReimbursementDao();
//         try{
//             System.out.println(dao.getAllReimbursements());
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//        try {
//            ConnectionUtility.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        MD5 md5 = new MD5();
//        String s ="123456";
//        System.out.println(md5.getMd5(s));

        Javalin app = Javalin.create((config)->{
            config.enableCorsForAllOrigins();
            //config.enableCorsForOrigin("http://localhost:5500")
        });
        map(app, new AuthenticationController(), new ExceptionController(), new ReimbursementController());
          app.start(8081);

        }

        public static void map(Javalin app, Controller... controllers){
            for(Controller c : controllers){
                c.mapEndpoints(app);
            }
        }
}
