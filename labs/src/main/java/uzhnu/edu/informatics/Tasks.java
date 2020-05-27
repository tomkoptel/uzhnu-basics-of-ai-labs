package uzhnu.edu.informatics;

public class Tasks {
    /**
     * Дано масив M, розмірністю 11. Знайти найбільший елемент масиву та його номер.
     */
    public static void findMax(int[] M) {
        int max = Integer.MIN_VALUE;
        int index = Integer.MIN_VALUE;
        for (int i = 0; i < M.length; i++) {
            int element = M[i];
            if (element > max) {
                max = element;
                index = i;
            }
        }
        System.out.println("Найбільше число " + max);
        System.out.println("Його індекс " + index);
    }

    /**
     * Вивести на екран значення подвоєної суми елементів масиву.
     */
    public static void doubleSum(int[] M) {
        int sum = 0;
        for (int i = 0; i < M.length; i++) {
            sum += M[i];
        }
        int doubleSum = sum + sum;
        System.out.println("Сумма всіх елементів " + sum);
        System.out.println("Подвійна сумма елементів " + doubleSum);
    }
}
