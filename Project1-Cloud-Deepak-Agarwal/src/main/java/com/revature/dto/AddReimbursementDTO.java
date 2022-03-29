package com.revature.dto;

import com.revature.model.User;

import java.io.InputStream;
import java.util.Objects;

public class AddReimbursementDTO {

    private int amount;
    private String submitDate;
    private String resolveDate;
    private String description;
    private int status;
    private int type;
    private InputStream image;

    public AddReimbursementDTO() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddReimbursementDTO that = (AddReimbursementDTO) o;
        return amount == that.amount && status == that.status && type == that.type && Objects.equals(submitDate, that.submitDate) && Objects.equals(resolveDate, that.resolveDate) && Objects.equals(description, that.description) && Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, submitDate, resolveDate, description, status, type, image);
    }

    @Override
    public String toString() {
        return "AddReimbursementDTO{" +
                "amount=" + amount +
                ", submitDate='" + submitDate + '\'' +
                ", resolveDate='" + resolveDate + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", image=" + image +
                '}';
    }
}
