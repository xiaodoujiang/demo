package cn.bmilk;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Test.build().parallelStream().forEach(Test::out);
    }

}
