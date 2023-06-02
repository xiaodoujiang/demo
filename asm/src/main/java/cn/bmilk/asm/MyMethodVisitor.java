package cn.bmilk.asm;


import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class MyMethodVisitor extends MethodVisitor implements Opcodes {

    public MyMethodVisitor( MethodVisitor methodVisitor) {
        super(ASM5, methodVisitor);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("start");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    }

    @Override
    public void visitInsn(int opcode) {
        if((opcode >= Opcodes.IRETURN && opcode<=Opcodes.RETURN) || opcode == Opcodes.ATHROW){
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("end");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }
        mv.visitInsn(opcode);
    }
}
