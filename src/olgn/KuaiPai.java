package olgn;

public class KuaiPai {
    public static void sort(int[] a){
        int n = a.length;
        quickSort(a,0,n-1);
    }

    private static void quickSort(int[] a, int p,int q) {
        if(p>=q)return;//递归终止条件
        int r = parition(a,p,q);
        quickSort(a,p,r-1);
        quickSort(a,r+1,q);
    }

    private static int parition(int[] a, int p, int q) {
        int i = p;
        for(int j = p;j<q;j++){
            if(a[j]<a[q]){
                if(i==j){
                    i++;
                }else {
                    int temp = a[j];
                    a[j] = a[i];
                    a[i++] = temp;
                }
            }
        }
        int temp = a[q];
        a[q] = a[i];
        a[i] = temp;
        return i;
    }
}
