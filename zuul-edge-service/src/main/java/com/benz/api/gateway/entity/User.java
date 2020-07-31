package com.benz.api.gateway.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.Size;

import com.benz.api.gateway.util.Schema;

import lombok.Getter;
import lombok.Setter;



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
