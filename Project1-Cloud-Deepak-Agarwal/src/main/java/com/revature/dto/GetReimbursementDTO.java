package com.revature.dto;

import java.util.Objects;

public class GetReimbursementDTO {

    private int id;
    private int amount;
    private String submitDate;
    private String resolveDate;
    private String status;
    private String type;
    private int authorId;
    private String authorUserName;
    private String authorFirst;
    private String authorLast;
    private String authorEmail;
    private  String authorRole;
    private int resolverId;
    private String resolverUserName;
    private String resolverFirst;
    private String resolverLast;
    private String resolverEmail;
    private String resolverRole;

    public GetReimbursementDTO() {
    }

    public GetReimbursementDTO(int id, int amount, String submitDate, String resolveDate, String status, String type,
                               int authorId, String authorUserName, String authorFirst, String authorLast, String authorEmail,String authorRole,
                               int resolverId, String resolverUserName, String resolverFirst, String resolverLast, String resolverEmail,
                               String resolverRole) {
        this.id = id;
        this.amount = amount;
        this.submitDate = submitDate;
        this.resolveDate = resolveDate;
        this.status = status;
        this.type = type;
        this.authorId = authorId;
        this.authorUserName = authorUserName;
        this.authorFirst = authorFirst;
        this.authorLast = authorLast;
        this.authorEmail = authorEmail;
        this.authorRole = authorRole;
        this.resolverId = resolverId;
        this.resolverUserName = resolverUserName;
        this.resolverFirst = resolverFirst;
        this.resolverLast = resolverLast;
        this.resolverEmail = resolverEmail;
        this.resolverRole = resolverRole;
    }


    public GetReimbursementDTO(int id, int amount, String submittedDate, String status, String type, int id1, String username,
                               String firstName, String lastName, String emailId, String userRole, int id2, String username1,
                               String firstName1, String lastName1, String emailId1, String userRole1)  {
        this.id = id;
        this.amount = amount;
        this.submitDate = submittedDate;
        this.status = status;
        this.type = type;
        this.authorId = id1;
        this.authorUserName = username;
        this.authorFirst = firstName;
        this.authorLast = lastName;
        this.authorEmail = emailId;
        this.authorRole = userRole;
        this.resolverId = id2;
        this.resolverUserName = username1;
        this.resolverFirst = firstName1;
        this.resolverLast = lastName1;
        this.resolverEmail = emailId1;
        this.resolverRole = userRole1;
    }


    public GetReimbursementDTO(int id, int amount, String status, String type, String submittedDate, String resolvedDate,  int id1, String username, int id2, String username1) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.type = type;
        this.submitDate = submittedDate;
        this.resolveDate = resolvedDate;
        this.authorId = id1;
        this.authorUserName = username;
         this.resolverId = id2;
        this.resolverUserName = username1;

    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public String getResolveDate() {
        return resolveDate;
    }

    public void setResolveDate(String resolveDate) {
        this.resolveDate = resolveDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorUserName() {
        return authorUserName;
    }

    public void setAuthorUserName(String authorUserName) {
        this.authorUserName = authorUserName;
    }

    public String getAuthorFirst() {
        return authorFirst;
    }

    public void setAuthorFirst(String authorFirst) {
        this.authorFirst = authorFirst;
    }

    public String getAuthorLast() {
        return authorLast;
    }

    public void setAuthorLast(String authorLast) {
        this.authorLast = authorLast;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public String getAuthorRole() {
        return authorRole;
    }

    public void setAuthorRole(String authorRole) {
        this.authorRole = authorRole;
    }

    public int getResolverId() {
        return resolverId;
    }

    public void setResolverId(int resolverId) {
        this.resolverId = resolverId;
    }

    public String getResolverUserName() {
        return resolverUserName;
    }

    public void setResolverUserName(String resolverUserName) {
        this.resolverUserName = resolverUserName;
    }

    public String getResolverFirst() {
        return resolverFirst;
    }

    public void setResolverFirst(String resolverFirst) {
        this.resolverFirst = resolverFirst;
    }

    public String getResolverLast() {
        return resolverLast;
    }

    public void setResolverLast(String resolverLast) {
        this.resolverLast = resolverLast;
    }

    public String getResolverEmail() {
        return resolverEmail;
    }

    public void setResolverEmail(String resolverEmail) {
        this.resolverEmail = resolverEmail;
    }

    public String getResolverRole() {
        return resolverRole;
    }

    public void setResolverRole(String resolverRole) {
        this.resolverRole = resolverRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetReimbursementDTO that = (GetReimbursementDTO) o;
        return id == that.id && amount == that.amount && authorId == that.authorId && resolverId == that.resolverId && Objects.equals(submitDate, that.submitDate) && Objects.equals(resolveDate, that.resolveDate) && Objects.equals(status, that.status) && Objects.equals(type, that.type) && Objects.equals(authorUserName, that.authorUserName) && Objects.equals(authorFirst, that.authorFirst) && Objects.equals(authorLast, that.authorLast) && Objects.equals(authorEmail, that.authorEmail) && Objects.equals(authorRole, that.authorRole) && Objects.equals(resolverUserName, that.resolverUserName) && Objects.equals(resolverFirst, that.resolverFirst) && Objects.equals(resolverLast, that.resolverLast) && Objects.equals(resolverEmail, that.resolverEmail) && Objects.equals(resolverRole, that.resolverRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, submitDate, resolveDate, status, type, authorId, authorUserName, authorFirst, authorLast, authorEmail, authorRole, resolverId, resolverUserName, resolverFirst, resolverLast, resolverEmail, resolverRole);
    }

    @Override
    public String toString() {
        return "GetReimbursementDTO{" +
                "id=" + id +
                ", amount=" + amount +
                ", submitDate='" + submitDate + '\'' +
                ", resolveDate='" + resolveDate + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", authorId=" + authorId +
                ", authorUserName='" + authorUserName + '\'' +
                ", authorFirst='" + authorFirst + '\'' +
                ", authorLast='" + authorLast + '\'' +
                ", authorEmail='" + authorEmail + '\'' +
                ", authorRole='" + authorRole + '\'' +
                ", resolverId=" + resolverId +
                ", resolverUserName='" + resolverUserName + '\'' +
                ", resolverFirst='" + resolverFirst + '\'' +
                ", resolverLast='" + resolverLast + '\'' +
                ", resolverEmail='" + resolverEmail + '\'' +
                ", resolverRole='" + resolverRole + '\'' +
                '}';
    }
}
