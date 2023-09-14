package cn.bmilk;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public void out() {
        try {
            Thread.sleep(100);
        }catch (Exception e){

        }

        System.out.println(this + "======="+ Thread.currentThread().getName());
    }

    public static List<Test> build(){
        List<Test> tests = new ArrayList<>();
        tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());
        tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());
        tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());
        tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());
        tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());
        tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());
        tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());
        tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());
        tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());
        tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());
        tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());
        tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());
        tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());
        tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());
        tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());
        tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());
        tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());
        tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());tests.add(new Test());
        System.out.println(tests.size());
        return tests;
    }
}
