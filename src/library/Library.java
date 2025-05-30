package library;

import management.BookManager;
import management.MemberManager;
import management.TransactionManager;

public class Library {
    private BookManager bookManager;
    private MemberManager memberManager;
    private TransactionManager transactionManager;

    public Library() {
        this.memberManager = new MemberManager();
        this.bookManager = new BookManager(memberManager);
        this.transactionManager = new TransactionManager();
    }

    public void addBook(Book book) {
        bookManager.addBook(book);
    }

    public void addMember(Member member) {
        memberManager.addMember(member);
    }

    public void borrowBook(String isbn, String memberId) {

        // Check if the book exists and hasn't been borrowed
        if (bookManager.isBookAvailable(isbn)) {

            // Check if member already has a borrowed book
            if (memberManager.getLastTransaction(memberId)!= null &&
                memberManager.getLastTransaction(memberId).getType().equals("BORROW")){

                System.out.println("First you have to return the borrowed book: " +
                                    memberManager.getLastTransaction(memberId).getBookTitle());

            } else {
                bookManager.setBookAvailability(isbn, false); // Book is borrowed so it is unavailable
                Transaction transaction = transactionManager.addTransaction(bookManager.getBookByIsbn(isbn).getTitle(),
                                                                            memberManager.getMember(memberId).getName(),
                                                                            "BORROW");
                memberManager.recordTransaction(memberId, transaction); // Record a borrow transaction for member
            }
        // If the book wasn't available, add member to the waitlist
        } else {
            System.out.println("The requested book with code " + isbn + " is not available in the library.");
            bookManager.addToWaitlist(isbn, memberId);
        }
    }

    public void returnBook(String isbn, String memberId) {

        bookManager.setBookAvailability(isbn, true); // Book is returned so it is available
        Transaction transaction = transactionManager.addTransaction(bookManager.getBookByIsbn(isbn).getTitle(),
                                                                    memberManager.getMember(memberId).getName(),
                                                                    "RETURN");
        memberManager.recordTransaction(memberId, transaction); // Record a return transaction for member

        // Check if anyone requested for this book while it was borrowed
        if (bookManager.hasWaitlist(isbn)) {
            Member nextMember = bookManager.getNextFromWaitlist(isbn);
            if (nextMember != null) {
                borrowBook(isbn, nextMember.getMemberId());
            }
        }
    }

    public Transaction getLastTransaction(String memberId) {
        return memberManager.getLastTransaction(memberId);
    }

    public Transaction getTranansactionByIndex(int index) {
        return transactionManager.getTransactionByIndex(index);
    }
}