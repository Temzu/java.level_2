public class StringIsNotNumberException extends IllegalArgumentException{
    private String string;
    public StringIsNotNumberException(String string) {
        super("String is not a number: \"" + string + "\"");
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
