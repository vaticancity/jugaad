public class CocaCola {

    public static boolean solveCryptarithmetic() {
        int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        String letters = "COALDRINK"; 
        char[] letterArray = letters.toCharArray();

        // Try all permutations of the digits
        do {
            int c = digits[findIndex(letterArray, 'C')];
            int o = digits[findIndex(letterArray, 'O')];
            int l = digits[findIndex(letterArray, 'L')];
            int a = digits[findIndex(letterArray, 'A')];
            int d = digits[findIndex(letterArray, 'D')];
            int r = digits[findIndex(letterArray, 'R')];
            int i = digits[findIndex(letterArray, 'I')];
            int n = digits[findIndex(letterArray, 'N')];
            int k = digits[findIndex(letterArray, 'K')];

            if (c == 0 || d == 0) {
                continue;
            }

            int coca = 1000 * c + 100 * o + 10 * c + a;
            int cola = 1000 * c + 100 * o + 10 * l + a;
            int drink = 10000 * d + 1000 * r + 100 * i + 10 * n + k;

            if (coca + cola == drink) {
                System.out.println("COCA = " + coca + ", COLA = " + cola + ", DRINK = " + drink);
                System.out.println("C = " + c + ", O = " + o + ", L = " + l + ", A = " + a);
                System.out.println("D = " + d + ", R = " + r + ", I = " + i + ", N = " + n + ", K = " + k);
                return true;
            }
        } while (nextPermutation(digits));

        System.out.println("No solution found.");
        return false;
    }

    private static int findIndex(char[] array, char target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // Method to generate the next permutation of an array
    private static boolean nextPermutation(int[] arr) {
        int n = arr.length;
        int i = n - 1;

        // Find the first pair where arr[i] < arr[i + 1]
        while (i > 0 && arr[i - 1] >= arr[i]) {
            i--;
        }

        // If no such pair exists, this is the last permutation
        if (i <= 0) return false;

        // Find the rightmost element that is greater than arr[i - 1]
        int j = n - 1;
        while (arr[j] <= arr[i - 1]) {
            j--;
        }

        // Swap arr[i - 1] and arr[j]
        int temp = arr[i - 1];
        arr[i - 1] = arr[j];
        arr[j] = temp;

        // Reverse the sequence from arr[i] to the end
        j = n - 1;
        while (i < j) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }

        return true;
    }

    public static void main(String[] args) {
        solveCryptarithmetic();
    }
}