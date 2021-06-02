package com.example.PW14.java.entities;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "clients")
public class Client implements UserDetails{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;
    @Column(name="email")
    private String email;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @ManyToMany(fetch=FetchType.EAGER)
    Set<UserRole> roles;
    
    public Client(String name, String surname, String email, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
    }
    public Client() {}

    public String getEmail() {
        return email;
    }
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getPassword() {
        return password;
    }
    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return getRoles();
    }
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
    
}
