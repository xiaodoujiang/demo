package cn.bmilk.asm;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class JavassistTest {
    public static void main(String[] args) throws Exception {

        BaseClass b = new BaseClass();
        ClassPool classPool = ClassPool.getDefault();

        CtClass ctClass = classPool.get("cn.bmilk.asm.BaseClass");
        CtMethod process = ctClass.getDeclaredMethod("method");
        process.insertBefore("{System.out.println(\"javassist start\");}");
        process.insertAfter("{System.out.println(\"javassist end\");}");
        Class c = ctClass.toClass();
        BaseClass baseClass = (BaseClass) c.newInstance();
        baseClass.method();

    }
}
