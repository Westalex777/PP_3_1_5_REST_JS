package com.pp_3_1_5.model;

import com.pp_3_1_5.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private int age;

    @Column
    private String password;

    @Column
    private String email;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "join_users_roles", joinColumns = @JoinColumn(name = "user_id")
            , inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {
        roles = new HashSet<>();
    }

    public User(String firstname, String lastname, int age, String password, String email, Set<Role> roles) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public User(UserDto userDto) {
        this.id = userDto.getId();
        this.firstname = userDto.getFirstname();
        this.lastname = userDto.getLastname();
        this.age = userDto.getAge();
        this.password = userDto.getPassword();
        this.email = userDto.getEmail();
        this.roles = userDto.getRoles();
    }

    public Boolean isAdmin() {
        return this.roles.stream()
                .map(Role::getName)
                .anyMatch(x -> x.equals("ROLE_ADMIN"));
    }

    public void setRole(Role role) {
        roles.add(role);
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(Role::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
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

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + firstname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getRolesToString() {
        return roles.stream()
                .map(Role::getNameNotPrefix)
                .sorted()
                .reduce((x, y) -> x + " " + y)
                .orElse("");
    }
}
