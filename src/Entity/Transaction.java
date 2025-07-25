package Entity;

import java.time.LocalDate;

public class Transaction {
    private LocalDate transactionDate;
    private String transactionUserId;
    private Double transactionAmount;
    private String transactionType;
    private Double initialBalance;
    private Double finalBalance;
    private String transactionPerformedBy;

    public Transaction(String transactionUserId, String transactionType, LocalDate transactionDate, Double transactionAmount, Double initialBalance, Double finalBalance,String transactionPerformedBy) {
        this.transactionUserId = transactionUserId;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
        this.initialBalance = initialBalance;
        this.finalBalance = finalBalance;
        this.transactionPerformedBy=transactionPerformedBy;
    }

    public String getTransactionPerformedBy() {
        return transactionPerformedBy;
    }

    public void setTransactionPerformedBy(String transactionPerformedBy) {
        this.transactionPerformedBy = transactionPerformedBy;
    }

    public Double getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(Double finalBalance) {
        this.finalBalance = finalBalance;
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionUserId() {
        return transactionUserId;
    }

    public void setTransactionUserId(String transactionUserId) {
        this.transactionUserId = transactionUserId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "finalBalance=" + finalBalance +
                ", transactionDate=" + transactionDate +
                ", transactionUserId='" + transactionUserId + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", transactionType='" + transactionType + '\'' +
                ", initialBalance=" + initialBalance +
                ", transactionPerformedBy='" + transactionPerformedBy + '\'' +
                '}';
    }
}
