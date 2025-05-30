package management;

import datastructures.lists.CustomArrayList;
import library.Transaction;
import java.util.*;

public class TransactionManager {
    private final CustomArrayList<Transaction> transactions;

    public TransactionManager() {
        this.transactions = new CustomArrayList<>();
    }

    public Transaction addTransaction(String bookTitle, String memberName, String type) {
        Objects.requireNonNull(bookTitle, "Book title cannot be null");
        Objects.requireNonNull(memberName, "Member name cannot be null");
        Objects.requireNonNull(type, "Transaction type cannot be null");

        if (bookTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Book title cannot be empty");
        }
        if (memberName.trim().isEmpty()) {
            throw new IllegalArgumentException("Member name cannot be empty");
        }
        if (type.trim().isEmpty()) {
            throw new IllegalArgumentException("Transaction type cannot be empty");
        }

        Transaction transaction = new Transaction(
                "TXN-" + new Date().getTime(),
                bookTitle,
                memberName,
                type
        );

        transactions.add(transaction);
        return transaction;
    }

    public Transaction getTransactionByIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index cannot be negative");
        }
        if (index >= transactions.size()) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + transactions.size());
        }
        return transactions.get(index);
    }
}
