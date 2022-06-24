package ru.elkin.myfinance.entity;

/*
 * класс денег в наличии
 */

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "deposit")
public class Deposit extends AbstractEntity implements Cloneable {

    @Column(name = "name")
    private String name;

    @Column(name = "money")
    private Long money;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "deposit_user_fk"))
    private User user;

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
