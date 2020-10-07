package task_2;

public class Main{
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.put("Зубков", "8901324125", "41232");
        phoneBook.put("Зубков", "8901324132425", "41234232");
        phoneBook.put("Зубков", "89013ыва24125", "41236е2");
        phoneBook.put("Зубков", "890ы1324125", "41232ва");

        System.out.println(phoneBook.getNumbers("Зубков"));
        System.out.println(phoneBook.getEmails("Зубков"));
    }
}
