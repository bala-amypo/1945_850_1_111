// package com.example.demo.model;

// import jakarta.persistence.*;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import java.time.LocalDateTime;
// import java.util.Collection;
// import java.util.Collections;

// @Entity
// @Table(name = "user_accounts", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
// public class UserAccount implements UserDetails {
    
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
    
//     @Column(nullable = false, unique = true)
//     private String email;
    
//     @Column(nullable = false)
//     private String password;
    
//     @Enumerated(EnumType.STRING)
//     @Column(nullable = false)
//     private Role role = Role.STUDENT;
    
//     @Column(nullable = false)
//     private Boolean active = true;
    
//     @Column(name = "created_at")
//     private LocalDateTime createdAt = LocalDateTime.now();
    
//     public UserAccount() {}
    
//     public UserAccount(Long id, String email, String password, Role role, Boolean active, LocalDateTime createdAt) {
//         this.id = id;
//         this.email = email;
//         this.password = password;
//         this.role = role;
//         this.active = active;
//         this.createdAt = createdAt;
//     }
    
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }
    
//     public String getEmail() { return email; }
//     public void setEmail(String email) { this.email = email; }
    
//     public void setPassword(String password) { this.password = password; }
    
//     public Role getRole() { return role; }
//     public void setRole(Role role) { this.role = role; }
    
//     public Boolean getActive() { return active; }
//     public void setActive(Boolean active) { this.active = active; }
    
//     public LocalDateTime getCreatedAt() { return createdAt; }
//     public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role.name()));
//     }
    
//     @Override
//     public String getPassword() {
//         return password;
//     }
    
//     @Override
//     public String getUsername() {
//         return email;
//     }
    
//     @Override
//     public boolean isAccountNonExpired() {
//         return true;
//     }
    
//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }
    
//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }
    
//     @Override
//     public boolean isEnabled() {
//         return active;
//     }
// }