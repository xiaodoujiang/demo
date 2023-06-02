package cn.bmilk.asm;


import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class MyClassVisitor extends ClassVisitor implements Opcodes {


    public MyClassVisitor(ClassVisitor classVisitor) {
        super(ASM5, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        cv.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = cv.visitMethod(access, name, desc, signature, exceptions);
        if(!name.equals("<init>") && methodVisitor != null){
            methodVisitor = new MyMethodVisitor(methodVisitor);
        }
        return methodVisitor;
    }

}
