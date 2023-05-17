package CleanNow01.CleanNow.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "limpiador")
public class Limpiador {
    @Id
    private int dni;
    @Column(nullable = false)
    private String name;
    @Column(name= "last_name", nullable = false)
    private String lastName;
    @Column(nullable = true)
    private String email;
    @Column(nullable = false)
    private int phone;
    @Column(nullable = false)
    private boolean checked_records;
    @Column(nullable = false)
    private boolean available;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name="is_active",nullable = false)
    private boolean isActive;
    @Column(name = "is_deleted",nullable = false)
    private boolean isDeleted;
    
    public Limpiador(int dni, String name, String lastName, 
                String email, int phone, boolean checked_records, 
                boolean available) {
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.checked_records = checked_records;
        this.available = available;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.isActive = true;
        this.isDeleted = false;
    }

    public Limpiador() {}
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isChecked_records() {
        return checked_records;
    }

    public void setChecked_records(boolean checked_records) {
        this.checked_records = checked_records;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeleted() { return isDeleted; }

    public void setDeleted(boolean deleted) { isDeleted = deleted; }
}
    
