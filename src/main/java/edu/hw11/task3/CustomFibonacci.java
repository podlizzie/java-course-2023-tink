package edu.hw11.task3;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;

public class CustomFibonacci implements Implementation {
    private static final int SIZE_IMPACT = 5;
    private static final int MAXIMAL_SIZE = 6;

    private static final String METHOD_DESCRIPTOR = "(I)J";
    private static final String METHOD_OWNER = "edu/hw11/FibonacciCalculator";

    public static Implementation getInstance() {
        return new CustomFibonacci();
    }

    @Override
    @SuppressWarnings("AnonInnerLength")
    public ByteCodeAppender appender(Target implementationTarget) {
        return new ByteCodeAppender() {
            @Override
            public Size apply(
                MethodVisitor mv, Context context, MethodDescription methodDescription
            ) {
                mv.visitCode();

                mv.visitVarInsn(Opcodes.ILOAD, 0);
                mv.visitInsn(Opcodes.I2L);

                mv.visitInsn(Opcodes.LCONST_1);

                mv.visitInsn(Opcodes.LCMP);

                Label elseLabel = new Label();
                mv.visitJumpInsn(Opcodes.IFGT, elseLabel);
                mv.visitVarInsn(Opcodes.ILOAD, 0);
                mv.visitInsn(Opcodes.I2L);
                mv.visitInsn(Opcodes.LRETURN);
                mv.visitLabel(elseLabel);

                mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);

                mv.visitVarInsn(Opcodes.ILOAD, 0);
                mv.visitInsn(Opcodes.I2L);
                mv.visitInsn(Opcodes.LCONST_1);
                mv.visitInsn(Opcodes.LSUB);
                mv.visitInsn(Opcodes.L2I);
                mv.visitMethodInsn(
                    Opcodes.INVOKESTATIC,
                    METHOD_OWNER,
                    ByteBuddyFibGenerator.METHOD_NAME,
                    METHOD_DESCRIPTOR,
                    false
                );

                mv.visitVarInsn(Opcodes.ILOAD, 0);
                mv.visitInsn(Opcodes.I2L);
                mv.visitInsn(Opcodes.ICONST_2);
                mv.visitInsn(Opcodes.I2L);
                mv.visitInsn(Opcodes.LSUB);
                mv.visitInsn(Opcodes.L2I);
                mv.visitMethodInsn(
                    Opcodes.INVOKESTATIC,
                    METHOD_OWNER,
                    ByteBuddyFibGenerator.METHOD_NAME,
                    METHOD_DESCRIPTOR,
                    false
                );

                mv.visitInsn(Opcodes.LADD);
                mv.visitInsn(Opcodes.LRETURN);

                mv.visitEnd();

                if (!methodDescription.getReturnType().asErasure().represents(long.class)) {
                    throw new IllegalArgumentException(methodDescription + " must return long");
                }

                return new Size(
                    new Size(SIZE_IMPACT, MAXIMAL_SIZE).getLocalVariableSize(),
                    methodDescription.getStackSize()
                );
            }
        };
    }

    @Override
    public InstrumentedType prepare(InstrumentedType instrumentedType) {
        return instrumentedType;
    }
}
