public class Main {
    public static void main(String[] args) {
        String s = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 a";
        try {
            String[][] strings = transformStringTo2DArray(s);
            System.out.println(calculateTheSumOfArrayElementsAndDivByTwo(strings));
        } catch (ArrayNot4X4Exception | StringIsNotNumberException e) {
            e.printStackTrace();
        }
    }

    static String[][] transformStringTo2DArray(String s) throws ArrayNot4X4Exception{
        String[] strings = s.split("\n");
        if (strings.length > 4) throw new ArrayNot4X4Exception();
        String[][] strings2D = new String[strings.length][];
        for (int i = 0; i < strings.length; i++) {
            strings2D[i] = strings[i].split(" ");
            if (strings2D[i].length > 4) throw new ArrayNot4X4Exception();
        }
        return strings2D;
    }

    static float calculateTheSumOfArrayElementsAndDivByTwo(String[][] strings) throws StringIsNotNumberException{
        int sum = 0;
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[i].length; j++) {
                try {
                    sum += Integer.parseInt(strings[i][j]);
                } catch (NumberFormatException e) {
                    throw new StringIsNotNumberException(strings[i][j]);
                }
            }
        }
        return (float) sum / 2;
    }
}
