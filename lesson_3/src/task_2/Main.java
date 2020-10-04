package task_2;

public class Main{
    public static void main(String[] args) {
        PhoneBook_v2 phoneBook_v2 = new PhoneBook_v2();
        phoneBook_v2.put("Зубков", "89524556678", "saasdg@mail.ru");
        phoneBook_v2.put("Зубков", "89876543321", "443sfgd@mail.ru");
        phoneBook_v2.put("Опарин", "87856213548", "oparin@mail.ru");
        System.out.println(phoneBook_v2.getEmails("Опарин"));
        System.out.println(phoneBook_v2.getNumbers("Зубков"));

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.put("Зубков", "89524556678", "saasdg@mail.ru");
        phoneBook.put("Зубков", "89876543321", "443sfgd@mail.ru");
        phoneBook.put("Опарин", "87856213548", "oparin@mail.ru");
        System.out.println(phoneBook.getEmails("Зубков"));
        System.out.println(phoneBook.getNumbers("Опарин"));
    }
}
