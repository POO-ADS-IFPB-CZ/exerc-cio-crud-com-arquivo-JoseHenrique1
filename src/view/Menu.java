package view;


import dao.PersonDao;
import model.Person;

import java.util.Scanner;

public class Menu {
    private PersonDao personDao;

    public Menu() {
        this.personDao = new PersonDao();
    }

    public void run() {
        Scanner scan = new Scanner(System.in);
        String menu = """
        \nPerson Registration
        1 - create
        2 - list
        3 - delete by email
        4 - exit
        """;
        String choice;
        do {
            System.out.print(menu);
            choice = scan.nextLine();

            if (choice.equals("1")) {
                boolean cond = createPerson(this.personDao);
                if (cond) {
                    System.out.println("Created person");
                }
                else {System.out.println("Not created");}
            }
            else if (choice.equals("2")) {
                listPerson(this.personDao);
            }
            else if (choice.equals("3")) {
                boolean cond = deletePerson(this.personDao);
                if (cond) {
                    System.out.println("Deleted person");
                }
                else {System.out.println("Not deleted");}
            }
            else {
                System.out.println("Operation not found");
            }
        } while (!choice.equals("4"));
    }

    static private boolean createPerson (PersonDao personDao) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter email: ");
        String email = scan.nextLine();
        System.out.print("Enter name: ");
        String name = scan.nextLine();
        personDao.salvar(new Person(name, email));
        return true;
    }

    static private void listPerson (PersonDao personDao) {
        System.out.println(personDao.getPersons());
    }

    static private boolean deletePerson (PersonDao personDao) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter email: ");
        String email = scan.nextLine();
        personDao.deletar(new Person("", email));
        return true;
    }
}
