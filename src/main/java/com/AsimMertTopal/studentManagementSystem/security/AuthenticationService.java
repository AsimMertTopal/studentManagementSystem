package com.AsimMertTopal.studentManagementSystem.security;


import com.AsimMertTopal.studentManagementSystem.dto.JwtAuthenticationResponse;
import com.AsimMertTopal.studentManagementSystem.dto.SignUpRequest;
import com.AsimMertTopal.studentManagementSystem.dto.SigninRequest;
import com.AsimMertTopal.studentManagementSystem.entities.Admin;
import com.AsimMertTopal.studentManagementSystem.entities.Student;
import com.AsimMertTopal.studentManagementSystem.entities.Teacher;
import com.AsimMertTopal.studentManagementSystem.entities.User;
import com.AsimMertTopal.studentManagementSystem.enums.Role;
import com.AsimMertTopal.studentManagementSystem.repository.AdminRepository;
import com.AsimMertTopal.studentManagementSystem.repository.StudentRepository;
import com.AsimMertTopal.studentManagementSystem.repository.TeacherRepository;
import com.AsimMertTopal.studentManagementSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AdminRepository   adminRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        var jwt = jwtTokenProvider.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtTokenProvider.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    public JwtAuthenticationResponse signupAdmın(Admin admin) {
        var user = User.builder().firstName(admin.getFirstName()).lastName(admin.getLastName())
                .email(admin.getEmail()).password(passwordEncoder.encode(admin.getPassword()))
                .role(Role.ADMIN).build();
        adminRepository.save(admin);
        var jwt = jwtTokenProvider.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    public JwtAuthenticationResponse signinAdmın(Admin admin) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(admin.getEmail(), admin.getPassword()));
        var user = adminRepository.findByEmail(admin.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtTokenProvider.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    public JwtAuthenticationResponse createTeacher(Teacher teacher) {
        var user = User.builder()
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .email(teacher.getEmail()).password(passwordEncoder.encode(teacher.getPassword()))
                .role(Role.TEACHER).build();
        userRepository.save(user);
        var jwt = jwtTokenProvider.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }


    public JwtAuthenticationResponse logInTeacher(Teacher teacher) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(teacher.getEmail(), teacher.getPassword()));
        var user = teacherRepository.findByEmail(teacher.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtTokenProvider.generateToken(teacher);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }


    public JwtAuthenticationResponse createStudent(Student student) {
        var user = User.builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail()).password(passwordEncoder.encode(student.getPassword()))
                .role(Role.STUDENT).build();
        studentRepository.save(student);
        var jwt = jwtTokenProvider.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }


    public JwtAuthenticationResponse logInStudent(Student student) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(student.getEmail(), student.getPassword()));
        var user = studentRepository.findByEmail(student.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtTokenProvider.generateToken(student);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }



}