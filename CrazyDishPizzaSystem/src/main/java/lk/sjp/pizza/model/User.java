package lk.sjp.pizza.model;

public class User {
    private String username;
    private String password;
    private String fullName;
    private String phone;
    private String role; // "CUSTOMER" or "ADMIN"

    public User(String username, String password, String fullName, String phone, String role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.role = role;
    }

    public String getUsername() { 
        return username; 
    }
    public String getPassword() {
        return password; 
    }
    public String getFullName() { 
        return fullName;
    }
    public String getPhone() { 
        return phone; 
    }
    public String getRole() { 
        return role; 
    }
}
