package com.C10G14.WorldFitBackend.entity;

import com.C10G14.WorldFitBackend.enumeration.EUnit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

@Getter
@Setter
@Entity
@Table(name = "Units")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "NAME")
    private EUnit name;

    public Unit() {
    }
    public Unit(String name){
        this.name = EUnit.valueOf(name);
        this.id = (name.equals("kms")? 1 : 2);
    }
    public Unit(EUnit name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "" + name + "";
    }
}
