package com.revature.service;

import com.revature.dao.ReimbursementDao;
import com.revature.dto.AddReimbursementDTO;
import com.revature.dto.GetReimbursementDTO;
import com.revature.dto.GetReimbursementPureDTO;
import com.revature.exception.ImageNotFoundException;
import com.revature.exception.InvalidImageException;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementPure;
import org.apache.tika.Tika;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementService {

    private ReimbursementDao reimbursementDao;

    public ReimbursementService(){
      this.reimbursementDao = new ReimbursementDao();
    }

    public ReimbursementService(ReimbursementDao mockDao){
        this.reimbursementDao = mockDao;
    }

     public List<GetReimbursementDTO> getAllReimbursementsByUserId(int userId) throws SQLException {
        List<Reimbursement> reimbursements = this.reimbursementDao.getAllReimbursementsByUserId(userId);

        List<GetReimbursementDTO> dtos = new ArrayList<>();
        for (Reimbursement r : reimbursements){
            dtos.add(new GetReimbursementDTO(r.getId(), r.getAmount(),r.getStatus(),r.getType(), r.getSubmittedDate(), r.getResolvedDate(),
                     r.getAuthor().getId(),r.getAuthor().getUsername(),r.getResolver().getId(),r.getResolver().getUsername()));
        }
        return dtos;
     }

    public GetReimbursementDTO resolveReimbursement(String reimbursementId,String statusId, int resolverId) throws SQLException {
        try{
            int intReimbursementId = Integer.parseInt(reimbursementId);

            int intStatusId = Integer.parseInt(statusId);

            Reimbursement reimbursement = this.reimbursementDao.resolveReimbursement(intReimbursementId,intStatusId,resolverId);

            return new GetReimbursementDTO(reimbursement.getId(),reimbursement.getAmount(),reimbursement.getSubmittedDate(),
                    reimbursement.getStatus(),reimbursement.getType(),
                    reimbursement.getAuthor().getId(),
                    reimbursement.getAuthor().getUsername(),
                    reimbursement.getAuthor().getFirstName(),reimbursement.getAuthor().getLastName(),reimbursement.getAuthor().getEmailId(),
                    reimbursement.getAuthor().getUserRole(),

                    reimbursement.getResolver().getId(),reimbursement.getResolver().getUsername(), reimbursement.getResolver().getFirstName(),
                    reimbursement.getResolver().getLastName(),reimbursement.getResolver().getEmailId(),reimbursement.getResolver().getUserRole());


        } catch(NumberFormatException e){
          throw new IllegalArgumentException("Reimbursement Id and status provided must be a int value");
        }
    }


    public GetReimbursementPureDTO addReimbursements(int authorId,AddReimbursementDTO dto) throws SQLException,
            InvalidImageException, IOException {

        Tika tika = new Tika();
        String mimeType = tika.detect(dto.getImage());

        if(!mimeType.equals("image/jpeg") && !mimeType.equals("image/png") && !mimeType.equals("image/gif")){
              throw new InvalidImageException("Image must be a JPEG, PNG, or GIF");
        }
        ReimbursementPure reimbursementAdded = this.reimbursementDao.addReimbursements(authorId,dto);

        return new GetReimbursementPureDTO(reimbursementAdded.getId(),reimbursementAdded.getAmount(),reimbursementAdded.getSubmittedDate(),
              reimbursementAdded.getStatus(),reimbursementAdded.getType(), reimbursementAdded.getAuthor());
    }


    public List<GetReimbursementDTO> getAllReimbursements() throws SQLException{
        List<Reimbursement> reimbursements =  this.reimbursementDao.getAllReimbursements();

        List<GetReimbursementDTO> reimbursementDTOs = new ArrayList<>();

        for (Reimbursement reimbursement : reimbursements){

            reimbursementDTOs.add(new GetReimbursementDTO(reimbursement.getId(), reimbursement.getAmount(), reimbursement.getSubmittedDate(),
                    reimbursement.getResolvedDate(),reimbursement.getStatus(), reimbursement.getType(),
                    reimbursement.getAuthor().getId(), reimbursement.getAuthor().getUsername(),reimbursement.getAuthor().getFirstName(),
                    reimbursement.getAuthor().getLastName(),reimbursement.getAuthor().getEmailId(),reimbursement.getAuthor().getUserRole(),

                    reimbursement.getResolver().getId(),reimbursement.getResolver().getUsername(),reimbursement.getResolver().getFirstName(),
                    reimbursement.getResolver().getLastName(),reimbursement.getResolver().getEmailId(), reimbursement.getResolver().getUserRole()
                    ));
        }

        return reimbursementDTOs;
    }


//    public InputStream getReimbursementImage(String reimbursementId, String userId) throws SQLException, ImageNotFoundException {
        public InputStream getReimbursementImage(String reimbursementId) throws SQLException, ImageNotFoundException {
        try{
            int rId = Integer.parseInt(reimbursementId);
//            int uId = Integer.parseInt(userId);

//            InputStream is = this.reimbursementDao.getReimbursementImage(rId,uId);
            InputStream is = this.reimbursementDao.getReimbursementImage(rId);
            if(is == null){
                throw new ImageNotFoundException("Reimbursement id " + reimbursementId + " does not have an image");
            }

            return is;

//            return this.reimbursementDao.getReimbursementImage(rId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Reimbursement is must be an int value");
        }
    }
}
