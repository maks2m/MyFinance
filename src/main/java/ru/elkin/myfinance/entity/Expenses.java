package ru.elkin.myfinance.entity;

/*
 * класс расходов
 */

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "expenses")
public class Expenses extends AbstractEntity implements Cloneable {

    @Column(name = "name")
    private String name;

    @Column(name = "plan_money")
    private Long planMoney;

    @Column(name = "fact_money")
    private Long factMoney;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "expenses_user_fk"))
    private User user;

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
