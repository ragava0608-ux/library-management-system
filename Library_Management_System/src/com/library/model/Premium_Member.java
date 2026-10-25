
package com.library.model;

public class Premium_Member extends Member {

    public Premium_Member(int memberId, String name, String email) {
        super(memberId, name, email);
    }

    @Override
    public int getBorrowingLimit() {
        return 5;
    }

    @Override
    public int getLoanPeriodDays() {
        return 21;
    }

    @Override
    public String toString() {
        return "Premium " + super.toString();
    }
}
