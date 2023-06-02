package cn.bmilk.asm;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ASMTest {
    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        BaseClass baseClass1 = new BaseClass();
        baseClass1.method();
        ClassReader classReader = new ClassReader("cn/bmilk/asm/BaseClass");
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        ClassVisitor classVisitor = new MyClassVisitor(classWriter);
        classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
        byte[] data = classWriter.toByteArray();

        File f = new File("asm/target/classes/cn/bmilk/asm/BaseClass.class");

        FileOutputStream fileOutputStream = new FileOutputStream(f);
        fileOutputStream.write(data);
        fileOutputStream.close();

        System.out.println("======success===");
    }
}
