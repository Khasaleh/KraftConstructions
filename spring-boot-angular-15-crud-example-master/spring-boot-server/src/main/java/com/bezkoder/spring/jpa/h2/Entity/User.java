package com.bezkoder.spring.jpa.h2.Entity;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", nullable=false, length=20)
    private String username;

    @Column(name="email", nullable=false, length=50)
    private String email;

    @Column(name="firstname", nullable=true, length=20)
    private String firstname;

    @Column(name="lastname", nullable=true, length=20)
    private String lastname;

    @Column(name="password", nullable=false, length=120)
    private String password;

    @Column(name="profileimage", nullable = true)
    private byte[] profileimage;

    @Transient
    private MultipartFile imageFile;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public User(String username, String email, String firstname, String lastname, String password, byte profileimage) {
        this.username = username;
        this.email = email;
        this.firstname=firstname;
        this.lastname=lastname;
        this.password = password;
        this.profileimage = profileimage;
    }
    public User(String username, String email,String firstname,String lastname, String password, MultipartFile imageFile) {
        this.username = username;
        this.email = email;
        this.firstname=firstname;
        this.lastname=lastname;
        this.password = password;
        this.imageFile = imageFile;
    }
//    public User(String username, String email,String firstname,String lastname, String password,String profileimage) {
//        this.username = username;
//        this.email = email;
//        this.firstname=firstname;
//        this.lastname=lastname;
//        this.profileimage=profileimage;
//        this.password = password;
//    }
}
