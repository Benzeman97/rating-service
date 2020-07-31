package com.benz.api.gateway.entity;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

import com.benz.api.gateway.util.ERole;
import com.benz.api.gateway.util.Schema;

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
