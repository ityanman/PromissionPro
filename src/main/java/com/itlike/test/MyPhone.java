package com.itlike.test;

import org.junit.jupiter.api.Test;

public class MyPhone {
    @Test
    public void myPhone(){
      int[] arr = new int[]{3,1,5,9,2,6,0};
      int[] index = new int[]{1,0,2,4,5,3,2,6,3,4};
      String tel  = "";
        for (int i : index) {
            tel+= arr[i];
        }
        System.out.println("我的号码是："+tel);
    }
}
