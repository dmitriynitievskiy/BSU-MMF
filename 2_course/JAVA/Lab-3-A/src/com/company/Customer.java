package com.company;

public class Customer implements Comparable <Customer> {
    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private String address;
    private long card_number;
    private long bank_number;

    public int compareTo(Customer other) {
        return surname.compareTo(other.surname);
    }


    Customer(int id,String surname,String name,String patronymic,String address,long card_number,long bank_number) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.address = address;
        this.card_number = card_number;
        this.bank_number = bank_number;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCard_number() {
        return card_number;
    }

    public void setCard_number(long card_number) {
        this.card_number = card_number;
    }

    public long getBank_number() {
        return bank_number;
    }

    public void setBank_number(long bank_number) {
        this.bank_number = bank_number;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", address='" + address + '\'' +
                ", card_number=" + card_number +
                ", bank_number=" + bank_number +
                '}';
    }
}
