package dao;

import model.Person;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class PersonDao {
    public File archive;

    public PersonDao () {
        this.archive = new File("persons.ser");

        if(!this.archive.exists()){
            try{
                this.archive.createNewFile();
            }catch (IOException e){
                System.out.println("Falha ao criar arquivo");
            }
        }
    }

    public Set<Person> getPersons(){
        if(this.archive.length()>0){
            //Há dados no arquivo, ler conjunto
            try{
                FileInputStream inputStream = new FileInputStream(this.archive);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                Set<Person> persons = (Set<Person>) objectInputStream.readObject();
                return persons;
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Falha ao ler arquivo: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println("Classe não encontrada ao tentar desserializar: " + e.getMessage());
            } catch (ClassCastException e) {
                System.out.println("Erro ao converter objeto lido para Set<Person>: " + e.getMessage());
            }
        }
        //Não há nada no arquivo, criar um novo conjunto
        return new HashSet<>();
    }

    public boolean salvar(Person person) {
        Set<Person> persons = getPersons();
        boolean cond = persons.add(person);
        if(cond){
            try{
                FileOutputStream outputStream = new FileOutputStream(this.archive);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(persons);
                return true;
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado");
            } catch (IOException e) {
                System.out.println("Falha ao escrever no arquivo");
            }
        }
        return false;
    }

    public boolean deletar(Person person) {
        Set<Person> persons = getPersons();
        if(persons.remove(person)){
            try{
                FileOutputStream outputStream = new FileOutputStream(this.archive);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(persons);
                return true;
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado");
            } catch (IOException e) {
                System.out.println("Falha ao escrever no arquivo");
            }
        }
        return false;
    }
}
