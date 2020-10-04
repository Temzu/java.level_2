package task_2;

import java.util.ArrayList;
import java.util.HashMap;

public class PhoneBook_v2 {
    private final int EMAIL = 0;
    private final int PHONE = 1;
    private final HashMap<Person, String> phoneBook = new HashMap<>();

    void put(String lastName, String phone, String email) {
        checkPhoneAndEmail(phone, email);
        Person person = new Person(lastName, phone, email);
        phoneBook.put(person,person.getEmail() + " " + person.getPhone());
    }

    private void checkPhoneAndEmail(String phone, String email) {
        for (Person str : phoneBook.keySet()) {
            if (str.getPhone().equals(phone))
                throw new PhoneBookHasThisNumber("there is this number in the phone book: number " + phone);
            if (str.getEmail().equals(email))
                throw new PhoneBookHasThisEmail("there is this email in the phone book: email " + email);
        }
    }

    ArrayList<String> getNumbers(String lastName) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Person str : phoneBook.keySet()) {
            if (str.getLastName().equals(lastName)) {
                arrayList.add(phoneBook.get(str).split(" ")[PHONE]);
            }
        }
        return arrayList;
    }

    ArrayList<String> getEmails(String lastName) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Person str : phoneBook.keySet()) {
            if (str.getLastName().equals(lastName)) {
                arrayList.add(phoneBook.get(str).split(" ")[EMAIL]);
            }
        }
        return arrayList;
    }

}
