package com.vlabs.lmsuser.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "user_id")
   private int userID;

   @Column(name = "user_name")
   @NotBlank(message = "User name is mandatory")
   private String userName;

   @Column(name = "user_email")
   @NotBlank(message = "Email is mandatory")
   @Email
   private String userEmail;

   @Column(name = "user_password")
   @NotBlank(message = "Password is mandatory")
//   @Size(min = 5)
//   @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$")
   private String userPassword;

   @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   @JoinTable(
           name = "users_roles",
           joinColumns = @JoinColumn(name = "user_id"),
           inverseJoinColumns = @JoinColumn(name = "role_id")
   )
   private Set<Role> roles = new HashSet<>();

}
