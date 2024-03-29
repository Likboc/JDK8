package algorithm.sort;

/**
 * 插入排序 : 分为排序完成和未排序两个部分
 * 时间复杂度
 * 平均O(N^2)
 * 最好O(N ^ 2)
 */
public class InsertSort {
    public int[] sort(int[] arr) {
        int i,j,k;
        for ( i = 0; i < arr.length; i++) {
            for( j = i - 1; j >= 0; j--) {
                if(arr[i] > arr[j]) {
                    break;
                }
            }
            int temp = arr[i];
            for ( k = i; k > j; k--) {
                if( k != j+1) {
                    arr[k] = arr[k - 1];
                }else {
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }
}
