package task_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class PhoneBook {
    private final HashMap<String, ArrayList<Person>> phoneBook = new HashMap<>();

    void put(String lastName, String phone, String email) {
        if (phoneBook.containsKey(lastName)) {
            ArrayList<Person> people = phoneBook.get(lastName);
            checkNumberAndEmail(people, phone, email);
            people.add(new Person(lastName, phone, email));
        } else {
            ArrayList<Person> people = new ArrayList<>();
            people.add(new Person(lastName, phone, email));
            phoneBook.put(lastName, people);
        }
    }

    ArrayList<String> getNumbers(String lastName) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Person person : phoneBook.get(lastName)) {
            arrayList.add(person.getPhone());
        }
        return arrayList;
    }

    ArrayList<String> getEmails(String lastName) {
        return phoneBook.get(lastName).stream().map(Person::getEmail).collect(Collectors.toCollection(ArrayList::new));
    }

    private static void checkNumberAndEmail(ArrayList<Person> people, String phone, String email) {
        for (Person person : people) {
            if (person.getPhone().equals(phone))
                throw new PhoneBookHasThisNumber("there is this number in the phone book: number " + phone);
            if (person.getEmail().equals(phone))
                throw new PhoneBookHasThisEmail("there is this email in the phone book: email " + email);
        }
    }

}
