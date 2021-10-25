import java.io.FileOutputStream;
import java.util.*;

import org.junit.Test;
import org.objectweb.asm.*;

public class TestDump implements Opcodes {

    public static byte[] dump() throws Exception {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        AnnotationVisitor av0;

        cw.visit(52, ACC_PUBLIC + ACC_SUPER, "Test", null, "java/lang/Object", null);

        cw.visitSource("Test.java", null);

        {
            fv = cw.visitField(ACC_PRIVATE, "num1", "I", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PUBLIC + ACC_STATIC, "NUM1", "I", null, null);
            fv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(8, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(11, l1);
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitIntInsn(SIPUSH, 6666);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLineNumber(14, l2);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_1);
            mv.visitFieldInsn(PUTFIELD, "Test", "num1", "I");
            mv.visitInsn(RETURN);
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLocalVariable("this", "LTest;", null, l0, l3, 0);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "func", "(II)I", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(17, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitMethodInsn(INVOKEVIRTUAL, "Test", "add", "(II)I", false);
            mv.visitInsn(IRETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "LTest;", null, l0, l1, 0);
            mv.visitLocalVariable("a", "I", null, l0, l1, 1);
            mv.visitLocalVariable("b", "I", null, l0, l1, 2);
            mv.visitMaxs(3, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "add", "(II)I", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(20, l0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitInsn(IADD);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "Test", "num1", "I");
            mv.visitInsn(IADD);
            mv.visitInsn(IRETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "LTest;", null, l0, l1, 0);
            mv.visitLocalVariable("a", "I", null, l0, l1, 1);
            mv.visitLocalVariable("b", "I", null, l0, l1, 2);
            mv.visitMaxs(2, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "sub", "(II)I", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(23, l0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitInsn(ISUB);
            mv.visitFieldInsn(GETSTATIC, "Test", "NUM1", "I");
            mv.visitInsn(ISUB);
            mv.visitInsn(IRETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "LTest;", null, l0, l1, 0);
            mv.visitLocalVariable("a", "I", null, l0, l1, 1);
            mv.visitLocalVariable("b", "I", null, l0, l1, 2);
            mv.visitMaxs(2, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(27, l0);
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitTypeInsn(NEW, "Test");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "Test", "<init>", "()V", false);
            /**
             * 骚操作 更改测试
             */
           // mv.visitInsn(ICONST_3);
          //  mv.visitInsn(ICONST_5);

            mv.visitInsn(ICONST_2);
            mv.visitInsn(ICONST_M1);

            mv.visitMethodInsn(INVOKEVIRTUAL, "Test", "add", "(II)I", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(28, l1);
            mv.visitInsn(RETURN);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLocalVariable("args", "[Ljava/lang/String;", null, l0, l2, 0);
            mv.visitMaxs(4, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(15, l0);
            mv.visitIntInsn(BIPUSH, 100);
            mv.visitFieldInsn(PUTSTATIC, "Test", "NUM1", "I");
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 0);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }


    @Test
    public void test()throws Exception{
        byte[] code = dump();
        //将二进制流写到out/下
        FileOutputStream fos = new FileOutputStream("/Users/hui.yang/IdeaProjects/MyCodeLoad3.0/springeasy/src/main/java/com/yh/springeasy/asm/test/Test.class");
        fos.write(code);
    }
}
