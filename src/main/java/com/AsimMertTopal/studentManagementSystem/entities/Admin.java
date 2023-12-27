package com.AsimMertTopal.studentManagementSystem.entities;

import com.AsimMertTopal.studentManagementSystem.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admin")
@PreAuthorize("hasRole('ADMIN')")
public class Admin  implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "admin_id")
    @Min(value = 1, message = "ID should be between 1 and 5")
    @Max(value = 15, message = "ID should be between 1 and 5")
    private Integer id;

    @Column(name = "admin_firstName")
    //@NotBlank(message = "İsim Zorunludur")
    private String firstName;

    @Column(name = "admin_lastName")
    // @NotBlank(message = "Soyisim Bilgisi Zorunludur")
    private String lastName;

    @Column(unique = true,name = "admin_email")
    @Email(message = "Email adresiniz geçersiz karakterlerden oluşmaktadır")
    private String email;

    @Column(name = "admin_password")
    private String password;


    @Enumerated(EnumType.STRING)
    private Role role;







    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }



    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
