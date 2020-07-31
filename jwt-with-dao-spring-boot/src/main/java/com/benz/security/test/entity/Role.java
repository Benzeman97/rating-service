package com.benz.security.test.entity;

import com.benz.security.test.util.ERole;
import com.benz.security.test.util.Schema;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "ROLES",schema = Schema.HR)
@Getter
@Setter
public class Role {

    @Id
    @SequenceGenerator(name = "ROLEID_GEN",sequenceName = Schema.HR+".ROLEID_GEN",initialValue = 101,allocationSize = 1)
    @GeneratedValue(generator = "ROLEID_GEN",strategy = GenerationType.SEQUENCE)
    @Column(name="ROLEID")
    private int roleId;

    @Enumerated(EnumType.STRING)
    @Column(name="NAME")
    private ERole name;
}
