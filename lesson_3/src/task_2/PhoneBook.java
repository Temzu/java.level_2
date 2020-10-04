package task_2;

import java.util.ArrayList;
import java.util.HashMap;

public class PhoneBook {
    private final HashMap<String, Person> phoneBook = new HashMap<>();

    void put(String lastName, String phone, String email) {
        for (String str : phoneBook.keySet()) {
            if (phoneBook.get(str).getPhone() == phone)
                throw new PhoneBookHasThisNumber("there is this number in the phone book: number " + phone);
            if (phoneBook.get(str).getEmail().equals(email))
                throw new PhoneBookHasThisEmail("there is this email in the phone book: email " + email);
            if (str.equals(lastName)) {
                lastName += " ";
            }
        }
        phoneBook.put(lastName, new Person(lastName.trim(), phone, email));
    }

    ArrayList<String> getNumbers(String lastName) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (String str : phoneBook.keySet()) {
            if (str.trim().equals(lastName)) {
                arrayList.add(phoneBook.get(str).getPhone());
            }
        }
        return arrayList;
    }

    ArrayList<String> getEmails(String lastName) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (String str : phoneBook.keySet()) {
            if (str.trim().equals(lastName)) {
                arrayList.add(phoneBook.get(str).getEmail());
            }
        }
        return arrayList;
    }


    void findNumber(String lastName) {
        for (String str : phoneBook.keySet()) {
            if (str.trim().equals(lastName)) {
                System.out.println(str.trim() + ": " + phoneBook.get(str).getPhone());
            }
        }
    }

    void findEmail(String lastName) {
        for (String str : phoneBook.keySet()) {
            if (str.trim().equals(lastName)) {
                System.out.println(str.trim() + ": " + phoneBook.get(str).getEmail());
            }
        }
    }

}
