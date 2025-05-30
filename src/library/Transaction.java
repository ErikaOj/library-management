package library;

import java.util.Date;

public class Transaction {
    private String transactionId;
    private String bookTitle;
    private String memberName;
    private Date transactionDate;
    private String type; // "BORROW" or "RETURN"

    public Transaction(String transactionId, String bookName, String memberName, String type) {
        this.transactionId = transactionId;
        this.bookTitle = bookName;
        this.memberName = memberName;
        this.transactionDate = new Date();
        this.type = type;
    }

    // Getters
    public String getTransactionId() { return transactionId; }
    public String getBookTitle() { return bookTitle; }
    public String getMember() { return memberName; }
    public Date getTransactionDate() { return transactionDate; }
    public String getType() { return type; }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId = '" + transactionId + '\'' +
                ", book = " + bookTitle +
                ", member = " + memberName +
                ", transactionDate = " + transactionDate +
                ", type = '" + type + '\'' +
                '}';
    }
}