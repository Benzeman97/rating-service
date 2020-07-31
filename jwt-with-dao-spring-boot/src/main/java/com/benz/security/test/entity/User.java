package com.benz.security.test.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.benz.security.test.util.Schema;

import java.util.Set;

@Entity
@Table(name = "USER56",schema = Schema.HR,
 uniqueConstraints = @UniqueConstraint(
         columnNames = "USERNAME"
 ))
@Getter
@Setter
public class User {

    @Id
    @SequenceGenerator(name = "USERID_GEN",sequenceName = Schema.HR+".USERID_SEQ",initialValue =1005,allocationSize =1)
    @GeneratedValue(generator = "USERID_GEN",strategy = GenerationType.SEQUENCE)
    @Column(name = "USERID")
    private int userId;
    @Column(name = "USERNAME",nullable = false)
    @Size(max = 120)
    private String userName;
    @Column(name = "PASSWORD",nullable = false)
    @Size(min = 6,max = 300)
    private String password;
    @Column(name = "ACTIVE")
    private String active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USERROLES",
      joinColumns = @JoinColumn(name = "USERID"),
       inverseJoinColumns = @JoinColumn(name="ROLEID"))
    private Set<Role> roles;
}
