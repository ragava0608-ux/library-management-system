
package com.library.model;

import java.util.ArrayList;
import java.util.List;

import com.library.model.Author;
import com.library.model.Book;
import com.library.model.Member;
import com.library.model.BorrowTransaction;
import com.library.model.ReturnTransaction;

public class Library {
    private List<Book> books;
    private List<Member> members;
    private List<BorrowTransaction> borrowTransactions;
    private List<ReturnTransaction> returnTransactions;

    private int nextBorrowId = 1;
    private int nextReturnId = 1;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
        borrowTransactions = new ArrayList<>();
        returnTransactions = new ArrayList<>();
    }

    public void addBook(Book book) {
        for (Book existing : books) {
            if (existing.getBookId() == book.getBookId()) {
                throw new IllegalArgumentException("Book ID already exists.");
            }
        }
        books.add(book);
    }

    public void addMember(Member member) {
        for (Member existing : members) {
            if (existing.getMemberId() == member.getMemberId()) {
                throw new IllegalArgumentException("Member ID already exists.");
            }
        }
        members.add(member);
    }

    public List<Book> getAvailableBooks() {
        List<Book> available = new ArrayList<>();

        for (Book book : books) {
            if (book.getAvailableCopies() > 0) {
                available.add(book);
            }
        }
        return available;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public BorrowTransaction borrowBook(int memberId, int bookId) {
        Member member = findMember(memberId);
        Book book = findBook(bookId);

        if (member == null) {
            throw new IllegalArgumentException("Member not found.");
        }

        if (book == null) {
            throw new IllegalArgumentException("Book not found.");
        }

        if (!member.canBorrowBook()) {
            throw new IllegalArgumentException("Borrowing limit reached.");
        }

        if (!book.borrowCopy()) {
            throw new IllegalArgumentException("No copies available.");
        }

        BorrowTransaction transaction =
                new BorrowTransaction(nextBorrowId++, member, book);

        member.addBorrowedBook(transaction);
        borrowTransactions.add(transaction);

        return transaction;
    }

    public ReturnTransaction returnBook(int transactionId) {
        BorrowTransaction borrow = findBorrowTransaction(transactionId);

        if (borrow == null) {
            throw new IllegalArgumentException("Borrow transaction not found.");
        }

        if (borrow.isReturned()) {
            throw new IllegalArgumentException("Book already returned.");
        }

        ReturnTransaction returnTransaction =
                new ReturnTransaction(nextReturnId++, borrow);

        borrow.markReturned();
        borrow.getBook().returnCopy();
        borrow.getMember().removeBorrowedBook(borrow);
        returnTransactions.add(returnTransaction);

        return returnTransaction;
    }

    private Member findMember(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }

    private Book findBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }

    private BorrowTransaction findBorrowTransaction(int transactionId) {
        for (BorrowTransaction transaction : borrowTransactions) {
            if (transaction.getTransactionId() == transactionId) {
                return transaction;
            }
        }
        return null;
    }
}
