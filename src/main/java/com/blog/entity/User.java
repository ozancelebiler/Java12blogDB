package com.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 30)
    private String name;
    @Column(nullable = false,length = 30)
    private String surname;
    @Column(nullable = false,length = 50)
    private String email;
    @Column(nullable = false,length = 15)
    private String password;
    @OneToMany
    private List<Post>postList;

}
