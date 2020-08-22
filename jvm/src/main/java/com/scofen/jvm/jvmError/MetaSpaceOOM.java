package com.scofen.jvm.jvmError;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by  GF  in  20:19 2019/3/2
 * Description:
 * Modified  By:
 */
public class MetaSpaceOOM extends ClassLoader{

    public static List<Class<?>> createClasses(){

        List<Class<?>> classes = new ArrayList<>();

        for (int i = 0; i < 10000000; ++ i){

            ClassWriter classWriter = new ClassWriter(0);
            classWriter.visit(Opcodes.V1_1, Opcodes.ACC_PUBLIC, "Class" + i, null,
            "java.lang.Object", null);

            MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>",
                    "( )V", null, null);
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java.lang.Object","<init>", "( )V");
            methodVisitor.visitInsn(Opcodes.RETURN);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
            MetaSpaceOOM test = new MetaSpaceOOM();
            byte[] code = classWriter.toByteArray();
            Class<?> exampleClass = test.defineClass("Class" + i, code, 0, code.length);
            classes.add(exampleClass);
        }
        return classes;
    }



}
