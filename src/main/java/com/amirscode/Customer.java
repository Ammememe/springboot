package com.amirscode;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
public class Customer {

    @Id
    @SequenceGenerator(
            name = "customer_id_sequence",
            sequenceName = "customer_id_sequence",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence"
    )
    private Integer libraryId;
    private String email;
    private String name;
    private Integer age;
    private String bookToBorrow;




    public void setBookToBorrow(String bookToBorrow) {
        this.bookToBorrow = bookToBorrow;
    }

    public Customer(Integer libraryId,
                    String email,
                    String name,
                    Integer age,
                    String bookToBorrow) {
        this.libraryId = libraryId;
        this.email = email;
        this.name = name;
        this.age = age;
        this.bookToBorrow = bookToBorrow;

    }

    public Customer() {
    }

    public Integer getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Integer libraryId) {
        this.libraryId = libraryId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBookToBorrow() { return bookToBorrow;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(libraryId, customer.libraryId) && Objects.equals(email, customer.email) && Objects.equals(name, customer.name) && Objects.equals(age, customer.age) && Objects.equals(bookToBorrow, customer.bookToBorrow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libraryId, email, name, age, bookToBorrow);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "libraryId=" + libraryId +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", bookToBorrow='" + bookToBorrow + '\'' +
                '}';
    }
}
