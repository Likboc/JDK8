package algorithm.sort;

public class BubbleSort {
    public int[] sort(int[] array) {
        int len = array.length;
        // 执行 len 次，每次执行得到最大的元素
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
        return array;
    }
}
