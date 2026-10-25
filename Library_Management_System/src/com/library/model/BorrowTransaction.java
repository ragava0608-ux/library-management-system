
package com.library.model;

import java.time.LocalDate;

public class BorrowTransaction {
    private int transactionId;
    private Member member;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private boolean returned;

    public BorrowTransaction(int transactionId, Member member, Book book) {
        this.transactionId = transactionId;
        this.member = member;
        this.book = book;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(member.getLoanPeriodDays());
        this.returned = false;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public Member getMember() {
        return member;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void markReturned() {
        returned = true;
    }

    public long calculateLateDays(LocalDate returnDate) {
        if (returnDate.isAfter(dueDate)) {
            return java.time.temporal.ChronoUnit.DAYS.between(
                    dueDate, returnDate);
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Transaction ID: " + transactionId
                + ", Member: " + member.getName()
                + ", Book: " + book.getTitle()
                + ", Borrow Date: " + borrowDate
                + ", Due Date: " + dueDate
                + ", Returned: " + returned;
    }
}
