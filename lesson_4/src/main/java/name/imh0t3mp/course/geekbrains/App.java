package name.imh0t3mp.course.geekbrains;

public class App {

    public static void main(String[] args) {
        try {
            String[][] arr = new String[][]{
                    {"1", "s", "1", "1",},
                    {"1", "1", "1", "1",},
                    {"1", "1", "1", "1",},
                    {"1", "1", "1", "1",}};
            System.out.println("Сумма значений: " + summArrayElems(arr));
        } catch (MyArrayDataException | MyArraySizeException e) {
            System.err.println(e.getMessage());
        }
    }

    public static int summArrayElems(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        if (4 != arr.length || 4 != arr[0].length)
            throw new MyArraySizeException("Массив должен быть " +
                    "размера 4х4, получили " + arr.length + "x" + arr[0].length);
        int summ = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                try {
                    summ += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Значение " + arr[i][j] + " в ячейке " +
                            "[" + i + "][" + j + "] не может быть преобразовано к INT");
                }
            }
        }
        return summ;
    }
}
