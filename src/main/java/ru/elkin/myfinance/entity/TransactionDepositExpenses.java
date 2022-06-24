package ru.elkin.myfinance.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/*
 * транзакции расхода
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction_deposit_expenses")
public class TransactionDepositExpenses extends AbstractEntity implements Cloneable {

    @Column(name = "money")
    private Long money;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_deposit", foreignKey = @ForeignKey(name = "transaction_deposit_expenses_deposit_fk"))
    private Deposit deposit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_expenses", foreignKey = @ForeignKey(name = "transaction_deposit_expenses_expenses_fk"))
    private Expenses expenses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "transaction_deposit_expenses_user_fk"))
    private User user;

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
