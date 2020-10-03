package task_2;

public class Main{
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.put("Зубков", 89056642132L, "324234@mail.ru");
        phoneBook.put("Зубков", 89034404415L, "asdfasa43@mail.ru");
        phoneBook.put("Зубков", 89042107788L, "3242323244@mail.ru");
        phoneBook.put("Опарин", 89101302231L,"safsdfsa@mail.ru");
        phoneBook.findEmail("Зубков");
        System.out.println();
        phoneBook.findNumber("Опарин");
    }
}
