package com.example.mylibrary;

import java.util.ArrayList;

public class Utils {

    private static Utils instance;

    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> favoriteBooks;

    private Utils() {
        if (null == allBooks) {
            allBooks = new ArrayList<>();
            initData();
        }

        if (null == alreadyReadBooks) {
            alreadyReadBooks = new ArrayList<>();
        }

        if (null == wantToReadBooks) {
            wantToReadBooks = new ArrayList<>();
        }

        if (null == currentlyReadingBooks) {
            currentlyReadingBooks = new ArrayList<>();
        }

        if (null == favoriteBooks) {
            favoriteBooks = new ArrayList<>();
        }
    }

    private void initData() {
        allBooks.add(new Book(
                1,
                "Atomic Habits",
                "James Clear",
                320,
                "https://images-na.ssl-images-amazon.com/images/I/91bYsX41DVL.jpg",
                "Build good habits, break bad ones.",
                "Atomic Habits offers a proven framework for improving every day. James Clear, one of the world’s leading experts on habit formation, reveals practical strategies to form good habits, break bad ones, and master the tiny behaviors that lead to remarkable results."
        ));

        allBooks.add(new Book(
                2,
                "The Alchemist",
                "Paulo Coelho",
                208,
                "https://images-na.ssl-images-amazon.com/images/I/71aFt4+OTOL.jpg",
                "A magical journey of following dreams.",
                "The Alchemist is a timeless story about a shepherd named Santiago who sets out to find treasure, but ends up discovering the treasure within. A philosophical book that teaches about destiny, purpose, and the universe conspiring to help you succeed."
        ));

        allBooks.add(new Book(
                3,
                "Sapiens: A Brief History of Humankind",
                "Yuval Noah Harari",
                498,
                "https://images-na.ssl-images-amazon.com/images/I/713jIoMO3UL.jpg",
                "History of humans from apes to AI.",
                "Sapiens explores how Homo sapiens came to dominate the world, examining biology, anthropology, and economics. It provides thought-provoking insights on how history shaped modern society and our place in it."
        ));

        allBooks.add(new Book(
                4,
                "Can't Hurt Me",
                "David Goggins",
                364,
                "https://ia800404.us.archive.org/view_archive.php?archive=/33/items/l_covers_0010/l_covers_0010_53.zip&file=0010536567-L.jpg",
                "Master your mind and defy the odds.",
                "In Can't Hurt Me, David Goggins shares his astonishing life story and reveals that most of us tap into only 40% of our capabilities. Through hard work and mental toughness, Goggins transformed his life and shows you how to do the same."
        ));

        allBooks.add(new Book(
                5,
                "The Subtle Art of Not Giving a F*ck",
                "Mark Manson",
                224,
                "https://images-na.ssl-images-amazon.com/images/I/71QKQ9mwV7L.jpg",
                "Counterintuitive guide to living a good life.",
                "This brutally honest book rejects traditional positivity and instead focuses on embracing life's struggles, setting realistic expectations, and focusing on what truly matters to you. A no-nonsense guide to personal growth."
        ));
    }

    public static Utils getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new Utils();
            return instance;
        }
    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getFavoriteBooks() {
        return favoriteBooks;
    }

    public Book getBookById(int bookId) {
        for (Book b: allBooks) {
            if(b.getId() == bookId) {
                return b;
            }
        }
        return null;
    }

    public boolean AddToAlreadyReadBooks(Book book) {
        return alreadyReadBooks.add(book);
    }

    public boolean AddToFavoriteBooks(Book book) {
        return favoriteBooks.add(book);
    }

    public boolean AddToWantToReadBooks(Book book) {
        return wantToReadBooks.add(book);
    }

    public boolean AddToCurrentlyReadingBooks(Book book) {
        return currentlyReadingBooks.add(book);
    }

    public boolean removeFromAlreadyReadBooks(Book book) {
        return alreadyReadBooks.remove(book);
    }

    public boolean removeFromWantToReadBooks(Book book) {
        return wantToReadBooks.remove(book);
    }

    public boolean removeFromCurrentlyReadingBooks(Book book) {
        return currentlyReadingBooks.remove(book);
    }

    public boolean removeFromFavoriteBooks(Book book) {
        return favoriteBooks.remove(book);
    }

}
