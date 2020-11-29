package olgn;

public class MergeSort_my {
    public static void sort(int[] a) {
        int n = a.length;

        merger_sort(a, 0, n - 1);
    }

    //对下标p到q的数组排序
    private static void merger_sort(int[] a, int p, int q) {
        if (p >= q) return;//递归终止条件
        int r = p + (q - p) / 2;//防止p+q溢出
        merger_sort(a, p, r);
        merger_sort(a, r + 1, q);
        merge(a, p, r, q);
    }

    //合并分区排序的数组
    private static void merge(int[] a, int p, int r, int q) {
        int i = p;
        int j = r + 1;
        int k = 0;
        int[] temp = new int[q - p + 1];//定义一个临时数组用来存放排好序的元素
        while (i <= r && j <= q) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        //判断哪个数组还有剩余元素
        int start = i;
        int end = r;
        if(j<q){
            start = j;
            end =q;
        }
        //将剩余元素存入temp
        while(start<=end){
            temp[k++] = a[start++];
        }
        //将temp中的元素拷入a
        for(i = 0;i<k;i++){
            a[p+i] = temp[i];
        }
    }
}
