package cn.bmilk.instrument;

import javassist.*;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class BaseClassTransformer implements ClassFileTransformer {
//    @Override
//    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
//        System.out.println("Transforming" + className);
//        try {
//            ClassPool cp = ClassPool.getDefault();
//            CtClass ctClass = cp.get("cn.bmilk.asm.BaseClass");
//
//            CtMethod method = ctClass.getDeclaredMethod("method");
//            method.insertBefore("{ System.out.println(\"start do  thing\"); }");
//            method.insertAfter("{ System.out.println(\"end do thing\"); }");
//
//            return ctClass.toBytecode();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if(!className.equals("cn/bmilk/asm/BaseClass")){
            return classfileBuffer;
        }

        System.out.println("Transforming" + className);
        try {
            ClassPool cp = ClassPool.getDefault();
            CtClass ctClass = cp.makeClass(new ByteArrayInputStream(classfileBuffer));
            if(ctClass.isInterface()) return classfileBuffer;
            CtBehavior[] methods = ctClass.getDeclaredBehaviors();
            for (CtBehavior method : methods){
                if(method.getName().equals("method")){
                    transformMethod(method);
                }
            }
            return ctClass.toBytecode();
        }catch (Exception e){
            e.printStackTrace();
        }
        return classfileBuffer;
    }

    public void transformMethod(CtBehavior method) throws CannotCompileException {
        method.insertBefore("{ System.out.println(\"start do  thing\"); }");
        method.insertAfter("{ System.out.println(\"end do thing\"); }");
    }
}
