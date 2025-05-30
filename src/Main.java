import library.Book;
import library.Library;
import library.Member;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        // Adding books
        Book book1 = new Book("The Hunger Games", "Suzanne Collins", "9780545791878");
        Book book2 = new Book("Catching Fire", "Suzanne Collins", "9780545586177");

        library.addBook(book1);
        library.addBook(book2);

        // Adding members
        Member member1 = new Member("M001", "Hooman");
        Member member2 = new Member("M002", "Sania");

        library.addMember(member1);
        library.addMember(member2);

        // Borrowing books
        library.borrowBook("9780545791878", "M001"); // Hooman borrows The Hunger Games
        System.out.println(library.getLastTransaction("M001")); // Check last transaction for Hooman

        library.borrowBook("9780545586177", "M001"); // Hooman tries to borrow Catching Fire

        // Trying to borrow an already borrowed book
        library.borrowBook("9780545791878", "M002"); // Sania tries to get The Hunger Games

        library.borrowBook("9780545586177", "M002"); // Sania borrows Catching Fire
        library.returnBook("9780545586177", "M002"); // Sania returns Catching Fire

        //Getting Transactions based on index
        System.out.println(library.getTranansactionByIndex(1));
        System.out.println(library.getTranansactionByIndex(2));

        // Returning a book
        library.returnBook("9780545791878", "M001"); // Hooman returns The Hunger Games
        //Sania should automatically get the book

        System.out.println(library.getTranansactionByIndex(3));

        //checking the borrow transaction of The Hunger Games
        System.out.println(library.getLastTransaction("M002"));
    }

}