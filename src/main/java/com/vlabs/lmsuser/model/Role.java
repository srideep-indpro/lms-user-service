package com.vlabs.lmsuser.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ROLES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int roleID;

    @Column(name = "role_name")
    @NotBlank(message = "Role name is mandatory")
    private String roleName;

    @ManyToMany(mappedBy = "roles", cascade = { CascadeType.ALL })
    private Set<User> users = new HashSet<>();

}
