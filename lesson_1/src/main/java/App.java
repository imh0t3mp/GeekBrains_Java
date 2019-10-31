import java.util.Arrays;

/**
 * App for the first lesson
 */
public class App {
    public static void main(String[] args) {
        initArray();
        for (int[] sub : createSquareDiagonal(8)) {
            System.out.println(Arrays.toString(sub));
        }
    }

    /**
     * Написать метод, принимающий на вход два числа и проверяющий,
     * что их сумма лежит в пределах от 10 до 20 (включительно),
     * если да – вернуть true, в противном случае – false.
     *
     * @param x - первое число
     * @param y - второе число
     * @return - результат проверки
     */
    public static boolean checkBetween(int x, int y) {
        return x + y <= 20 && 10 >= x + y;
    }

    /**
     * Написать метод, которому в качестве параметра передается целое число,
     * метод должен напечатать в консоль, положительное ли число передали или
     * отрицательное. Замечание: ноль считаем положительным числом.
     *
     * @param x - число для проверки
     */
    public static void isPositive(int x) {
        System.out.println(x >= 0 ? "true" : "false");
    }

    /**
     * Написать метод, который определяет, является ли год високосным,
     * и выводит сообщение в консоль.
     * Каждый 4-й год является високосным, кроме каждого 100-го,
     * при этом каждый 400-й – високосный.
     *
     * @param year - год для проверки
     */
    public static void isLeapYear(int year) {
        if (0 != year % 4 || (0 != year % 400 && 0 == year % 100)) {
            System.out.println("Обычный");
        } else {
            System.out.println("Високосный");
        }
    }

    /**
     * Написать метод, который получает в качестве аргумента целочисленный массив,
     * и возвращает сумму элементов этого массива.
     *
     * @param array - массив значений
     * @return - сумма значений
     */
    public static int getSumOfArray(int[] array) {
        int sum = 0;
        for (int elem : array) {
            sum += elem;
        }
        return sum;
    }

    /**
     * Задать пустой целочисленный массив размером 8.
     * С помощью цикла заполнить его значениями
     * 1 4 7 10 13 16 19 22;
     */
    public static void initArray() {
        int[] arr = new int[8];
        arr[0] = 1;
        for (int i = 1; i < 8; i++) {
            arr[i] += arr[i - 1] + 3;
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * Написать метод, который принимает на вход число n, создает и возвращает квадратный
     * двумерный целочисленный массив размера nxn, в котором главная диагональ
     * заполнена единицами;
     * <p>
     * 1 0 0 0
     * 0 1 0 0
     * 0 0 1 0
     * 0 0 0 1
     *
     * @param n - размер массива
     * @return - результрующий массив
     */
    public static int[][] createSquareDiagonal(int n) {
        int[][] ret = new int[n][n];
        for (int i = 0; i < n; i++) {
//            int[] subArr = new int[n];
//            Arrays.fill(subArr, 0);
            ret[i][i] = 1;
//            ret[i] = subArr;
        }
        return ret;
    }

    /**
     * Написать метод, который принимает в качестве аргумента целочисленный массив,
     * и возвращает максимальный элемент этого массива;
     *
     * @param array - массив значений
     * @return - максимальное значение
     */
    public static int getMax(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int val : array) {
            if (max < val) {
                max = val;
            }
        }
        return max;
    }
}
