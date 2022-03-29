package com.revature.dao;

import com.revature.dto.AddReimbursementDTO;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementPure;
import com.revature.model.User;
import com.revature.utility.ConnectionUtility;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDao {

    public Reimbursement resolveReimbursement(int reimbursementId ,int statusId, int resolverId ) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            con.setAutoCommit(false);
            String sql = "update reimbursements " +
                    " SET reimbursements_status_id = ?, " +
                    " reimbursements_resolver = ? " +
                    " where id = ? ";

            PreparedStatement pstmt = con.prepareStatement(sql);
//            pstmt.setInt(1, resolveDate);
            pstmt.setInt(1, statusId);
            pstmt.setInt(2, resolverId);
            pstmt.setInt(3, reimbursementId);

            pstmt.executeUpdate();


            String sql2 = "Select reimbursements.id as rs_id, reimbursements.reimbursements_amount as rs_amount , " +
                    " reimbursements.reimbursements_submitted as rs_submitted, reimbursements.reimbursements_resolved as rs_resolved, " +
                    " employee_user.id as employee_id,employee_user.first_name as employee_first,employee_user.last_name as employee_last," +
                    " employee_user.username as employee_name,employee_user.password as employee_password,employee_user.email as employee_email, " +
                    " manager_user.id as manager_id,manager_user.first_name as manager_first,manager_user.last_name as manager_last," +
                    " manager_user.username as manager_name,manager_user.password as manager_password,manager_user.email as manager_email, " +
                    " rs.status ,rt.type   " +
                    " from reimbursements " +
                    " LEFT join users employee_user " +
                    " on employee_user.id = reimbursements.reimbursements_author " +
                    " LEFT join users manager_user  " +
                    " on manager_user.id = reimbursements.reimbursements_resolver   " +
                    " LEFT join reimbursement_status rs " +
                    " on rs.id = reimbursements.reimbursements_status_id " +
                    " LEFT join reimbursement_type rt  " +
                    " on rt.id = reimbursements.reimbursements_type_id  " +
                    " where reimbursements.id= ? ";

            PreparedStatement pstmt2 = con.prepareStatement(sql2);
            pstmt2.setInt(1, reimbursementId);

            ResultSet rs2 = pstmt2.executeQuery();
            rs2.next();

                //Reimbursements
                int rsId = rs2.getInt("rs_id");
                int rsAmount = rs2.getInt("rs_amount");
                String rsSubmitted = rs2.getString("rs_submitted");
                String rsResolved = rs2.getString("rs_resolved");
                String rsStatus = rs2.getString("status");
                String rsType = rs2.getString("type");

                //user employee
                int eId = rs2.getInt("employee_id");
                String eName = rs2.getString("employee_name");
                String eFirst = rs2.getString("employee_first");
                String eLast = rs2.getString("employee_last");
                String eEmail = rs2.getString("employee_email");
                String eRole = "employee";
//              String ePassword = rs.getString("employee_password");


                User employee = new User(eId, eName, eFirst, eLast, eEmail, eRole);
//              User employee = new User(eId,eName,eFirst,eLast,eEmail,eRole, ePassword);

                //user manager
                int mId = rs2.getInt("manager_id");
                String mName = rs2.getString("manager_name");
                String mFirst = rs2.getString("manager_first");
                String mLast = rs2.getString("manager_last");
            String mEmail = rs2.getString("manager_email");
//                String mPassword = rs2.getString("manager_password");
                String mRole = "manager";


//              User manager = new User(mId,mFirst,mLast,mName, mPassword, mRole,mEmail);
                User manager = new User(mId,mName, mFirst, mLast, mEmail, mRole);
                Reimbursement r = new Reimbursement(rsId, rsAmount, rsSubmitted, rsResolved, rsStatus, rsType, employee, manager, rsStatus);

                con.commit();
                return r;

        }
    }


    public ReimbursementPure addReimbursements(int authorId ,AddReimbursementDTO dto) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {

            con.setAutoCommit(false);//we could set autocommit and at the end commit the change
            String sql = "insert into reimbursements (reimbursements_amount,reimbursements_submitted, " +
                    "reimbursements_description,reimbursements_receipt, " +
                    "reimbursements_status_id,reimbursements_type_id, reimbursements_author) " +
                    "values (?,?,?,?,?,?,?); ";

            PreparedStatement pstmt1 = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            pstmt1.setInt(1,dto.getAmount());
            pstmt1.setString(2,dto.getSubmitDate());
            pstmt1.setString(3,dto.getDescription());
            pstmt1.setBinaryStream(4,dto.getImage());
            pstmt1.setInt(5,dto.getStatus());
            pstmt1.setInt(6,dto.getType());
            pstmt1.setInt(7,authorId);

            pstmt1.executeUpdate();

           ResultSet rs = pstmt1.getGeneratedKeys();
            rs.next();
           int reimbursementId = rs.getInt(1);

           String sql2 = "SELECT * FROM users where id =?";
           PreparedStatement pstmt2 = con.prepareStatement(sql2);
            pstmt2.setInt(1,authorId);
           ResultSet rs2 = pstmt2.executeQuery();
           rs2.next();
           int aId = rs2.getInt("id");
           String aUserName = rs2.getString("username");
           String aFirst = rs2.getString("first_name");
            String aLast = rs2.getString("last_name");
            String aEmail = rs2.getString("email");


            User author = new User(aId,aUserName,aFirst,aLast,aEmail);

            ReimbursementPure reimbursementPure = new ReimbursementPure(reimbursementId,dto.getAmount(),dto.getSubmitDate(),dto.getStatus(),
                    dto.getType(),author);

            con.commit();

            return reimbursementPure;
        }
    };

    public List<Reimbursement> getAllReimbursements() throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            List<Reimbursement> reimbursements = new ArrayList<>();
          String sql = "Select reimbursements.id as rs_id, reimbursements.reimbursements_amount as rs_amount , " +
                  " reimbursements.reimbursements_submitted as rs_submitted, reimbursements.reimbursements_resolved as rs_resolved, " +
                  " employee_user.id as employee_id,employee_user.first_name as employee_first,employee_user.last_name as employee_last," +
                  " employee_user.username as employee_name,employee_user.password as employee_password,employee_user.email as employee_email, " +
                  " manager_user.id as manager_id,manager_user.first_name as manager_first,manager_user.last_name as manager_last," +
                  " manager_user.username as manager_name,manager_user.password as manager_password,manager_user.email as manager_email, " +
                  " rs.status ,rt.type   " +
                  " from reimbursements " +
                  " LEFT join users employee_user " +
                  " on employee_user.id = reimbursements.reimbursements_author " +
                  " LEFT join users manager_user  " +
                  " on manager_user.id = reimbursements.reimbursements_resolver   " +
                  " LEFT join reimbursement_status rs " +
                  " on rs.id = reimbursements.reimbursements_status_id " +
                  " LEFT join reimbursement_type rt  " +
                  " on rt.id = reimbursements.reimbursements_type_id  ";

          PreparedStatement pstmt = con.prepareStatement(sql);

          ResultSet rs = pstmt.executeQuery();
          while (rs.next()){
              //Reimbursements
              int rsId = rs.getInt("rs_id");
              int rsAmount = rs.getInt("rs_amount");
              String rsSubmitted = rs.getString("rs_submitted");
              String rsResolved = rs.getString("rs_resolved");
              String rsStatus = rs.getString("status");
              String rsType = rs.getString("type");

              //user employee
              int eId = rs.getInt("employee_id");
              String eName = rs.getString("employee_name");
              String eFirst = rs.getString("employee_first");
              String eLast = rs.getString("employee_last");
              String eEmail = rs.getString("employee_email");
              String eRole = "employee";
//              String ePassword = rs.getString("employee_password");


              User employee = new User(eId,eName,eFirst,eLast,eEmail,eRole);
//              User employee = new User(eId,eName,eFirst,eLast,eEmail,eRole, ePassword);

              //user manager
              int mId = rs.getInt("manager_id");
              String mFirst = rs.getString("manager_first");
              String mLast = rs.getString("manager_last");
               String mName = rs.getString("manager_name") ;
              String mPassword = rs.getString("manager_password");
              String mRole = "manager";
              String mEmail = rs.getString("manager_email");

//              User manager = new User(mId,mFirst,mLast,mName, mPassword, mRole,mEmail);
              User manager = new User(mId,mFirst,mLast,mName,mEmail, mRole);
              Reimbursement r = new Reimbursement(rsId, rsAmount, rsSubmitted, rsResolved,rsStatus,rsType, employee, manager,rsStatus);

              reimbursements.add(r);
          }
          return reimbursements;
        }

    }

    public List<Reimbursement> getAllReimbursementsByUserId(int userId) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            List<Reimbursement> reimbursements = new ArrayList<>();
            String sql = "Select reimbursements.id as rs_id, reimbursements.reimbursements_amount as rs_amount , " +
                    " reimbursements.reimbursements_submitted as rs_submitted, reimbursements.reimbursements_resolved as rs_resolved, " +
                    " employee_user.id as employee_id,employee_user.first_name as employee_first,employee_user.last_name as employee_last," +
                    " employee_user.username as employee_name,employee_user.password as employee_password,employee_user.email as employee_email, " +
                    " manager_user.id as manager_id,manager_user.first_name as manager_first,manager_user.last_name as manager_last," +
                    " manager_user.username as manager_name,manager_user.password as manager_password,manager_user.email as manager_email, " +
                    " rs.status ,rt.type   " +
                    " from reimbursements " +
                    " LEFT join users employee_user " +
                    " on employee_user.id = reimbursements.reimbursements_author " +
                    " LEFT join users manager_user  " +
                    " on manager_user.id = reimbursements.reimbursements_resolver   " +
                    " LEFT join reimbursement_status rs " +
                    " on rs.id = reimbursements.reimbursements_status_id " +
                    " LEFT join reimbursement_type rt  " +
                    " on rt.id = reimbursements.reimbursements_type_id  " +
                    " WHERE reimbursements.reimbursements_author = ? ";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,userId);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                //Reimbursements
                int rsId = rs.getInt("rs_id");
                int rsAmount = rs.getInt("rs_amount");
                String rsSubmitted = rs.getString("rs_submitted");
                String rsResolved = rs.getString("rs_resolved");
                String rsStatus = rs.getString("status");
                String rsType = rs.getString("type");

                //user employee
                int eId = rs.getInt("employee_id");
                String eName = rs.getString("employee_name");
                String eFirst = rs.getString("employee_first");
                String eLast = rs.getString("employee_last");
                String eEmail = rs.getString("employee_email");
                String eRole = "employee";
//              String ePassword = rs.getString("employee_password");


                User employee = new User(eId,eName,eFirst,eLast,eEmail,eRole);
//              User employee = new User(eId,eName,eFirst,eLast,eEmail,eRole, ePassword);

                //user manager
                int mId = rs.getInt("manager_id");
                String mFirst = rs.getString("manager_first");
                String mLast = rs.getString("manager_last");
                String mName = rs.getString("manager_name") ;
                String mPassword = rs.getString("manager_password");
                String mRole = "manager";
                String mEmail = rs.getString("manager_email");

//              User manager = new User(mId,mFirst,mLast,mName, mPassword, mRole,mEmail);
                User manager = new User(mId,mFirst,mLast,mName,mEmail, mRole);
                Reimbursement r = new Reimbursement(rsId, rsAmount, rsSubmitted, rsResolved,rsStatus,rsType, employee, manager,rsStatus);

                reimbursements.add(r);
            }
            return reimbursements;
        }

    }

//    public InputStream getReimbursementImage(int rId, int uId) throws SQLException {
        public InputStream getReimbursementImage(int rId) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()){
            String sql = "SELECT r.reimbursements_receipt "+
                    "FROM reimbursements r " +
                    "WHERE r.id = ? ";
//                    "WHERE r.id = ? AND r.reimbursements_author = ? ";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,rId);
//            pstmt.setInt(2,uId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
               InputStream is = rs.getBinaryStream("reimbursements_receipt");
               return is;
            }else{
                return null;
            }
        }
    }
}


