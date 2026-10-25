
package com.library.model;

import java.time.LocalDate;

public class ReturnTransaction {
    private int returnId;
    private BorrowTransaction borrowTransaction;
    private LocalDate returnDate;
    private long lateDays;
    private double fine;

    private static final double FINE_PER_DAY = 5.0;

    public ReturnTransaction(int returnId,
                             BorrowTransaction borrowTransaction) {
        this.returnId = returnId;
        this.borrowTransaction = borrowTransaction;
        this.returnDate = LocalDate.now();
        this.lateDays = borrowTransaction.calculateLateDays(returnDate);
        this.fine = lateDays * FINE_PER_DAY;
    }

    public int getReturnId() {
        return returnId;
    }

    public BorrowTransaction getBorrowTransaction() {
        return borrowTransaction;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public long getLateDays() {
        return lateDays;
    }

    public double getFine() {
        return fine;
    }

    @Override
    public String toString() {
        return "Return ID: " + returnId
                + ", Book: " + borrowTransaction.getBook().getTitle()
                + ", Return Date: " + returnDate
                + ", Late Days: " + lateDays
                + ", Fine: Rs. " + fine;
    }
}
