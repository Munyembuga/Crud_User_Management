package net.java.usermanagement.model;

public class SignUp {
//    private int id;

    private String name;
    private String email;
    private String phone;
    private String password;

    public SignUp(String name, String email, String phone, String password) {
        super();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;

    }
     public SignUp(String name, String email, String phone) {
        super();
        this.name = name;
        this.email = email;
        this.phone = phone;
       

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setCountry(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
