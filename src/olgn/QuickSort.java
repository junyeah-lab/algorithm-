package olgn;

public class QuickSort {
    public static void sort(int[] a){
        int n = a.length;//数组大小
        quickSort(a,0,n-1);
    }
    //p、q为数组的下标
    private static void quickSort(int[] a, int p, int q) {
        if(p>=q) return;
        int r = partition(a,p,q);
        quickSort(a,p,r-1);
        quickSort(a,r+1,q);
    }

    private static int partition(int[] a, int p, int q) {
        //选取下标最大的数作为分区点
        int i = p;
        for(int j = p;j<q;j++){
            if(a[j]<a[q]){
                if(i == j){
                    i++;
                }else {
                    int tem = a[i];
                    a[i++] = a[j];
                    a[j] = tem;
                }
            }
        }
        int temp = a[q];
        a[q] = a[i];
        a[i] = temp;
        System.out.println("i="+i);
        return i;
    }
}
