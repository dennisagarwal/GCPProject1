package com.revature.model;

import java.util.Objects;

public class Reimbursement {

    private int id;
    private int amount;
    private String submittedDate;
    private String resolvedDate;
    private String description;

    private User author;
    private User resolver;

    private String status;
    private String type;

    public Reimbursement(int id, int amount, String submittedDate, String resolvedDate, String description, User author, User resolver, String status, String type) {
        this.id = id;
        this.amount = amount;
        this.submittedDate = submittedDate;
        this.resolvedDate = resolvedDate;
        this.description = description;
        this.author = author;
        this.resolver = resolver;
        this.status = status;
        this.type = type;
    }


    public Reimbursement(int rsId, int rsAmount, String rsSubmitted, String rsResolved, String rsStatus, String rsType, User employee, User manager, String rsStatus1) {
        this.id=rsId;
        this.amount=rsAmount;
        this.submittedDate=rsSubmitted;
        this.resolvedDate=rsResolved;
        this.status= rsStatus;
        this.type= rsType;
        this.author = employee;
        this.resolver= manager;

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

    public String getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(String submittedDate) {
        this.submittedDate = submittedDate;
    }

    public String getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(String resolvedDate) {
        this.resolvedDate = resolvedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getResolver() {
        return resolver;
    }

    public void setResolver(User resolver) {
        this.resolver = resolver;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return id == that.id && amount == that.amount && Objects.equals(submittedDate, that.submittedDate) && Objects.equals(resolvedDate, that.resolvedDate) && Objects.equals(description, that.description) && Objects.equals(author, that.author) && Objects.equals(resolver, that.resolver) && Objects.equals(status, that.status) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, submittedDate, resolvedDate, description, author, resolver, status, type);
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + id +
                ", amount=" + amount +
                ", submittedDate='" + submittedDate + '\'' +
                ", resolvedDate='" + resolvedDate + '\'' +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", resolver=" + resolver +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
