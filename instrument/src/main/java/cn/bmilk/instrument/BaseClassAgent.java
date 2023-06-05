package cn.bmilk.instrument;

import cn.bmilk.asm.BaseClass;

import java.lang.instrument.Instrumentation;

public class BaseClassAgent {
    public static void agentmain(String args, Instrumentation inst){
        inst.addTransformer(new BaseClassTransformer(), true);
        try {
            inst.retransformClasses(BaseClass.class);
            System.out.println("Agnet load done");
        }catch (Exception e){
            System.out.println("agent load failed");
        }
    }
}
