package com.revature.dto;

import com.revature.model.User;

import java.util.Objects;

public class GetReimbursementPureDTO {


    private int id;
    private int amount;
    private String submitDate;
    private int status;
    private int type;
    private User author;

    public GetReimbursementPureDTO() {

    }

    public GetReimbursementPureDTO(int id, int amount, String submitDate, int status, int type, User author) {
        this.id = id;
        this.amount = amount;
        this.submitDate = submitDate;
        this.status = status;
        this.type = type;
        this.author = author;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetReimbursementPureDTO that = (GetReimbursementPureDTO) o;
        return id == that.id && amount == that.amount && status == that.status && type == that.type && author == that.author && Objects.equals(submitDate, that.submitDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, submitDate, status, type, author);
    }

    @Override
    public String toString() {
        return "GetReimbursementPureDTO{" +
                "id=" + id +
                ", amount=" + amount +
                ", submitDate='" + submitDate + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", author=" + author +
                '}';
    }
}
