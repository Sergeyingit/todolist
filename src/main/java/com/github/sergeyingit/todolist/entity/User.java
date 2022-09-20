package com.github.sergeyingit.todolist.entity;

import com.github.sergeyingit.todolist.validation.PasswordMatches;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@PasswordMatches
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "email")
    @NotBlank(message = "This field is require")
    @Pattern(regexp = "\\w+@[a-z]+\\.[a-z]+", message = "Not valid format for email")
    private String email;

    @Column(name = "password")
    private String password;

    @NotBlank(message = "This field is require")
    @Pattern(regexp = "[A-z0-9]+", message = "Use english alphabet and numbers")
    @Size(min = 4, max = 6, message = "Password length must be beetwen 4 and 6 characters")
    transient private String passwordNew;

    @NotBlank(message = "This field is require")
    transient private String passwordMatches;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> roles;

    @Column(name = "enabled")
    private boolean enabled;


    public User() {
    }

    public User(String email, String passwordNew, String passwordMatches) {
        this.email = email;
        this.passwordNew = passwordNew;
        this.passwordMatches = passwordMatches;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        this.password = password;
    }

    public String getPasswordNew() {
        return passwordNew;
    }

    public void setPasswordNew(String passwordNew) {
        this.passwordNew = passwordNew;
    }

    public String getPasswordMatches() {
        return passwordMatches;
    }

    public void setPasswordMatches(String passwordMatches) {
        this.passwordMatches = passwordMatches;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
