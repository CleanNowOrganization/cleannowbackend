package CleanNow01.CleanNow.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consumidor")
public class Consumidor {
    @Id
    private int dni;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private int phone;
    @Column(name = "is_verified",nullable = false)
    private boolean isVerified;
    @Column(name = "is_active",nullable = false)
    private boolean isActive;
    @Column(name = "is_deleted",nullable = false)
    private boolean isDeleted;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Consumidor(int dni, String name, String lastName, String email, String address, int phone) {
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = "";
        this.address = address;
        this.phone = phone;
        this.isActive = true;
        this.isDeleted = false;
        this.isVerified = true;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Consumidor() {}

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password=password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address=address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone=phone;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        this.isDeleted=deleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt=updatedAt;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        this.isVerified=verified;
    }
}
