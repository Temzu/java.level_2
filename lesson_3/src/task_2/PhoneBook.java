package task_2;

import java.util.HashMap;

public class PhoneBook {
    private final HashMap<String, Person> phoneBook = new HashMap<>();

    void put(String lastName, long phone, String email) {
        for (String str : phoneBook.keySet()) {
            if (phoneBook.get(str).phone == phone)
                throw new PhoneBookHasThisNumber("there is this number in the phone book: number " + phone);
            if (phoneBook.get(str).email.equals(email))
                throw new PhoneBookHasThisEmail("there is this email in the phone book: email " + email);
            if (str.equals(lastName)) {
                lastName += " ";
            }
        }
        phoneBook.put(lastName, new Person(lastName.trim(), phone, email));
    }

    void findNumber(String lastName) {
        for (String str : phoneBook.keySet()) {
            if (str.trim().equals(lastName)) {
                System.out.println(str.trim() + ": " + phoneBook.get(str).phone);
            }
        }
    }

    void findEmail(String lastName) {
        for (String str : phoneBook.keySet()) {
            if (str.trim().equals(lastName)) {
                System.out.println(str.trim() + ": " + phoneBook.get(str).email);
            }
        }
    }

}
