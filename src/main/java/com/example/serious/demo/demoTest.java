package com.example.serious.demo;

public class demoTest {
    public static void main(String[] args) {
        System.out.println(sk());


    }
    public static boolean sk(){
        String A="1232456789abcd"; String B="789abcd1232456";
        if (A.length() != B.length()){
            return false;
        }
        int j = A.length();
        for(int i=1;i<j;i++){
            int k = B.indexOf(A.substring(0,i)) ;
            int m = B.indexOf(A.substring(i,j-1));
            if(k+m-1 == i){
                return true;
            }
        }
        return false;
    }
}
