
package com.library.model;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private int memberId;
    private String name;
    private String email;
    private List<BorrowTransaction> borrowedBooks;

    public Member(int memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<BorrowTransaction> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBorrowingLimit() {
        return 2;
    }

    public int getLoanPeriodDays() {
        return 14;
    }

    public boolean canBorrowBook() {
        return borrowedBooks.size() < getBorrowingLimit();
    }

    public void addBorrowedBook(BorrowTransaction transaction) {
        borrowedBooks.add(transaction);
    }

    public void removeBorrowedBook(BorrowTransaction transaction) {
        borrowedBooks.remove(transaction);
    }

    @Override
    public String toString() {
        return "Member ID: " + memberId
                + ", Name: " + name
                + ", Email: " + email
                + ", Borrowed Books: " + borrowedBooks.size();
    }
}
