
package com.library.model;

public class Author {
    private int authorId;
    private String name;
    private String email;

    public Author(int authorId, String name, String email) {
        this.authorId = authorId;
        this.name = name;
        this.email = email;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Author ID: " + authorId
                + ", Name: " + name
                + ", Email: " + email;
    }
}
