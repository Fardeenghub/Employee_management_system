package Entity;

import javax.persistence.*;

@Entity
@Table(name = "login")
public class LoginEntity {

    @Id
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    // --- Constructors ---
    public LoginEntity() {}

    public LoginEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // --- Getters and Setters ---
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
