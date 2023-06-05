package cn.bmilk.instrument;

import cn.bmilk.asm.BaseClass;

import java.lang.instrument.Instrumentation;

public class BaseClassPreMain {
    public static void premain(String args, Instrumentation inst){
        System.out.println("SimpleAgent premain method called");
        try {
            inst.addTransformer(new BaseClassTransformer());
            System.out.println("premain load done");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
