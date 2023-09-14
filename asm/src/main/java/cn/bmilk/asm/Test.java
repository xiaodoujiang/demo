package cn.bmilk.asm;

import java.math.BigDecimal;

public class Test {
    public static void main(String[] args) {
        // 方法1
        BigDecimal a = new BigDecimal(0.1);
        System.out.println("a --> " + a);
        // 方法2
        BigDecimal b = new BigDecimal("0.1");
        System.out.println("b --> " + b);
        // 方法3
        BigDecimal c = BigDecimal.valueOf(0.1);
        System.out.println("c --> " + c);
    }

}
