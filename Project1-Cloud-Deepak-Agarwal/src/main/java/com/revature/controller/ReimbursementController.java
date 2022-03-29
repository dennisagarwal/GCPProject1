package com.revature.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.revature.dto.AddReimbursementDTO;
import com.revature.dto.GetReimbursementDTO;
import com.revature.dto.GetReimbursementPureDTO;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.JWTService;
import com.revature.service.ReimbursementService;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;
import io.javalin.http.UploadedFile;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.tika.Tika;

import java.io.InputStream;
import java.util.List;

public class ReimbursementController implements Controller {

    private JWTService jwtService;
    private ReimbursementService reimbursementService;
    private Handler getAllReimbursements = (ctx) -> {
        String jwt = ctx.header("Authorization").split(" ")[1];

        Jws<Claims> token = this.jwtService.parseJwt(jwt);

        if (!token.getBody().get("user_role").equals("manager")) {
            throw new UnauthorizedResponse("You must be a manager to access ths endpoint");
        }
        List<GetReimbursementDTO> reimbursements = this.reimbursementService.getAllReimbursements();
        ctx.json(reimbursements);
        System.out.println(reimbursements);
//                ctx.json("Successfully accessed GET /reimbursements");

    };
    //Authorization logic
    //1. user role should be employee (logged in)
    //2. user id should match who is actually logged in
    private Handler addReimbursements = (ctx) -> {
        String jwt = ctx.header("Authorization").split(" ")[1];

        Jws<Claims> token = this.jwtService.parseJwt(jwt);

        if (!token.getBody().get("user_role").equals("employee")) {
            throw new UnauthorizedResponse(("You must be an employee to access this endpoint"));
        }
        String userId = ctx.pathParam("user_id");

        int id = Integer.parseInt(userId);


        if (!token.getBody().get("user_id").equals(id)) {
            throw new UnauthorizedResponse("You cannot add a reimbursement for other than yourself");
        }
        ;
//        AddReimbursementDTO dto = ctx.bodyAsClass(AddReimbursementDTO.class);
        String amount = ctx.formParam("amount");
        String submitDate = ctx.formParam("submitDate");
        String description = ctx.formParam("description");
        String status = ctx.formParam("status");
        String type = ctx.formParam("type");

        AddReimbursementDTO dto = new AddReimbursementDTO();

        dto.setAmount(Integer.parseInt(amount));
        dto.setSubmitDate(submitDate);
        dto.setDescription(description);
        dto.setType(Integer.parseInt(type));
        dto.setStatus(Integer.parseInt(status));

        UploadedFile file = ctx.uploadedFile("image");
        InputStream is = file.getContent(); //This represents the byte for the file

        dto.setImage(is);
//        System.out.println(mimeType);

        GetReimbursementPureDTO getDto = this.reimbursementService.addReimbursements(id, dto);
        ctx.status(201);
        ctx.json(getDto);
    };
    private Handler resolveReimbursement = (ctx) -> {
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = this.jwtService.parseJwt(jwt);

        if (!token.getBody().get("user_role").equals("manager")) {
            throw new UnauthorizedResponse(("You must be logged in as manager"));
        }

        String reimbursementId = ctx.pathParam("reimbursement_id");
        String status = ctx.queryParam("status");
        int userId = token.getBody().get("user_id", Integer.class);

        if (status == null) {
            throw new IllegalArgumentException("You need a resolve query parameter when attempting to resolve an reimbursement");
        }

          GetReimbursementDTO reimbursement = this.reimbursementService.resolveReimbursement(reimbursementId,status,userId);
          ctx.json(reimbursement);
        //        int st = Integer.parseInt(status);

    };

    public ReimbursementController() {
        this.jwtService = new JWTService();
        this.reimbursementService = new ReimbursementService();
    }

    private Handler getSpecificEmployeeReimbursements=(ctx)->{
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = this.jwtService.parseJwt(jwt);

        if (!token.getBody().get("user_role").equals("employee")) {
            throw new UnauthorizedResponse("You must be an employee to access this endpoint");
        }

        String userId = ctx.pathParam("user_id");
        int id = Integer.parseInt(userId);
        if (!token.getBody().get("user_id").equals(id)) {
            throw new UnauthorizedResponse("You cannot obtain reimbursements that don't belong to yourself");
        }
        List<GetReimbursementDTO> dtos = this.reimbursementService.getAllReimbursementsByUserId(id);
        ctx.json(dtos);
    };

    private Handler getReimbursementImage = (ctx) -> {
//
//        String jwt = ctx.header("Authorization").split(" ")[1];
//        Jws<Claims> token = this.jwtService.parseJwt(jwt);
//
//
//
//        if(!(token.getBody().get("user_role").equals("employee"))|| token.getBody().get("user_role").equals("manager")){
//            throw new UnauthorizedResponse("You are not authorize to access this end point because you a manager");
//        }
//
//        if(token.getBody().get("user_role").equals("employee") && !("" + token.getBody().get("user_id")).equals(userId)){
//           throw new UnauthorizedResponse("You are a employee, but not accessing the reimbursement image that belongs " +
//                   "to you");
//        }

        String reimbursementId = ctx.pathParam("reimbursement_id");
//        String userId = ctx.pathParam("user_id");
//         InputStream image = this.reimbursementService.getReimbursementImage(reimbursementId,userId);
        InputStream image = this.reimbursementService.getReimbursementImage(reimbursementId);

         Tika tika = new Tika();
         String mimeType = tika.detect(image);

         ctx.header("Content-Type", mimeType); // Tell the client what type of image is being send in the response
        ctx.result(image);
    };

    @Override
    public void mapEndpoints(Javalin app) {
//        app.get("/test", test);
        app.get("/reimbursements", getAllReimbursements);
        app.get("/user/{user_id}/reimbursements", getSpecificEmployeeReimbursements);
        app.post("/users/{user_id}/reimbursements", addReimbursements);
//        app.get("/users/{user_id}/reimbursement/{reimbursement_id}/image", getReimbursementImage);
        app.get("/reimbursement/{reimbursement_id}/image", getReimbursementImage);
        app.patch("/reimbursements/{reimbursement_id}", resolveReimbursement);
    }
}
