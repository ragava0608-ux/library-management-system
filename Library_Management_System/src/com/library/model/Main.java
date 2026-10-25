
package com.library.model;

import java.util.Scanner;

import com.library.model.Library;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final Library library = new Library();

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display Available Books");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = readInt();

            try {
                switch (choice) {
                    case 1:
                        addBook();
                        break;

                    case 2:
                        addMember();
                        break;

                    case 3:
                        borrowBook();
                        break;

                    case 4:
                        returnBook();
                        break;

                    case 5:
                        displayBooks();
                        break;

                    case 6:
                        System.out.println(
                            "Thank you for using the Library System!");
                        break;

                    default:
                        System.out.println(
                            "Invalid choice. Enter a number from 1 to 6.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }

        } while (choice != 6);

        sc.close();
    }

    private static int readInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Invalid input. Enter a whole number: ");
            sc.next();
        }

        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    private static String readText(String prompt) {
        System.out.print(prompt);
        String value = sc.nextLine().trim();

        while (value.isEmpty()) {
            System.out.print("Input cannot be empty. Try again: ");
            value = sc.nextLine().trim();
        }

        return value;
    }

    private static void addBook() {
        System.out.println("\n--- Add Book ---");

        System.out.print("Enter Book ID: ");
        int bookId = readInt();

        String title = readText("Enter Book Title: ");

        System.out.print("Enter Author ID: ");
        int authorId = readInt();

        String authorName = readText("Enter Author Name: ");
        String email = readText("Enter Author Email: ");

        System.out.print("Enter Total Copies: ");
        int copies = readInt();

        if (bookId <= 0 || authorId <= 0 || copies <= 0) {
            throw new IllegalArgumentException(
                "Book ID, Author ID and copies must be positive.");
        }

        Author author = new Author(authorId, authorName, email);
        Book book = new Book(bookId, title, author, copies);

        library.addBook(book);

        System.out.println("Book added successfully!");
    }

    private static void addMember() {
        System.out.println("\n--- Add Member ---");

        System.out.print("Enter Member ID: ");
        int id = readInt();

        if (id <= 0) {
            throw new IllegalArgumentException(
                "Member ID must be positive.");
        }

        String name = readText("Enter Member Name: ");
        String email = readText("Enter Member Email: ");

        System.out.println("1. Regular Member");
        System.out.println("2. Premium Member");
        System.out.print("Choose Member Type: ");

        int type = readInt();

        Member member;

        switch (type) {
            case 1:
                member = new Member(id, name, email);
                break;

            case 2:
                member = new Premium_Member(id, name, email);
                break;

            default:
                throw new IllegalArgumentException(
                    "Invalid member type.");
        }

        library.addMember(member);

        System.out.println("Member added successfully!");
    }

    private static void borrowBook() {
        System.out.println("\n--- Borrow Book ---");

        System.out.print("Enter Member ID: ");
        int memberId = readInt();

        System.out.print("Enter Book ID: ");
        int bookId = readInt();

        BorrowTransaction transaction =
            library.borrowBook(memberId, bookId);

        System.out.println("Book borrowed successfully!");
        System.out.println(transaction);
    }

    private static void returnBook() {
        System.out.println("\n--- Return Book ---");

        System.out.print("Enter Borrow Transaction ID: ");
        int transactionId = readInt();

        ReturnTransaction transaction =
            library.returnBook(transactionId);

        System.out.println("Book returned successfully!");
        System.out.println(transaction);
    }

    private static void displayBooks() {
        System.out.println("\n--- Available Books ---");

        var availableBooks = library.getAvailableBooks();

        if (availableBooks.isEmpty()) {
            System.out.println("No books are currently available.");
            return;
        }

        for (Book book : availableBooks) {
            System.out.println(book);
        }
    }
}
