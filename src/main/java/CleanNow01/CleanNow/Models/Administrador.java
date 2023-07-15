package CleanNow01.CleanNow.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "administradores")
public class Administrador {
    @Id
    @Column(name = "dni")
    private int dni;
    @Column(name = "name" )
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column (name = "uid")
    private String uid;
    @Column(name = "role")
    private String role;
    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "created_at")
    private long createdAt;
    @Column(name = "updated_at")
    private long updatedAt;

    public Administrador(int dni, String name, String lastName, String password, String email, String uid, String role) {
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.uid = uid;
        this.role = role;
        this.isActive = true;
        this.isDeleted = false;
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
    }

    public Administrador(){};

    public int getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUid() {
        return uid;
    }

    public String getRole() {
        return role;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName= lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {this.password = password;}

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void setCreatedAt(long l) {
        this.createdAt = l;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt= updatedAt;
    }

}
