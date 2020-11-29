package olgn;


import java.util.Random;

public class Test {
    public static void main(String[] args) {
        int[] a= new int[50];
        for(int i=0;i<50;i++){
            a[i] = new Random().nextInt(100000);
        }
        System.out.println("before");
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
        }
        long start = System.currentTimeMillis();
        KuaiPai.sort(a);
        long end = System.currentTimeMillis();
        System.out.println("用时"+(end-start));
        System.out.println("after");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }

    }
}
