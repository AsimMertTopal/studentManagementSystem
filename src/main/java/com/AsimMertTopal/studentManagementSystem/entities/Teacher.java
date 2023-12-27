package com.AsimMertTopal.studentManagementSystem.entities;

import com.AsimMertTopal.studentManagementSystem.enums.Branch;
import com.AsimMertTopal.studentManagementSystem.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teachers")
@PreAuthorize("hasRole('TEACHER')")

public class Teacher  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Integer id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "teacher_email")
    private String email;

    @Column(name = "teacher_password")
    private String password;


    @Column(name = "branch")
    @Enumerated(EnumType.STRING)
    private Branch branch;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(mappedBy = "teachers")
    private List<Student> students;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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
