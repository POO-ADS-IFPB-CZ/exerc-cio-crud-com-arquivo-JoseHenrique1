package view;

import model.Person;

public class Main {
    public static void main(String[] args) {
        Person person = new Person("jose", "jose@gmail.com");
        System.out.println(person.getEmail());
    }
}
