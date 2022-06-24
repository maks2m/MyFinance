package ru.elkin.myfinance.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/*
 * транзакции дохода
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction_income_deposit")
public class TransactionIncomeDeposit extends AbstractEntity implements Cloneable {

    @Column(name = "money")
    private Long money;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_income", foreignKey = @ForeignKey(name = "transaction_income_deposit_income_fk"))
    private Income income;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_deposit", foreignKey = @ForeignKey(name = "transaction_income_deposit_deposit_fk"))
    private Deposit deposit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "transaction_income_deposit_user_fk"))
    private User user;

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
