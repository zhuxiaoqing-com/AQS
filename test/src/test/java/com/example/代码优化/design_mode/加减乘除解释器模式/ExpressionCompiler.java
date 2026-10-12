package com.example.代码优化.design_mode.加减乘除解释器模式;

import com.example.代码优化.design_mode.加减乘除解释器模式.instruction.BooleanInstruction;
import com.example.代码优化.design_mode.加减乘除解释器模式.instruction.BooleanMethodInstruction;
import com.example.代码优化.design_mode.加减乘除解释器模式.instruction.JumpIfFalseInstruction;
import com.example.代码优化.design_mode.加减乘除解释器模式.instruction.JumpIfTrueInstruction;
import com.example.代码优化.design_mode.加减乘除解释器模式.instruction.JumpInstruction;
import com.example.代码优化.design_mode.加减乘除解释器模式.instruction.MethodInstruction;
import com.example.代码优化.design_mode.加减乘除解释器模式.instruction.NumberInstruction;
import com.example.代码优化.design_mode.加减乘除解释器模式.instruction.OperatorInstruction;

import java.util.ArrayList;
import java.util.List;

/** 把经过类型检查的 AST 编译成可重复执行的栈式指令。 */
final class ExpressionCompiler {
    private final List<ExpressionInstruction> instructions = new ArrayList<>();

    ExpressionInstruction[] compile(ExpressionNode root) {
        compileNode(root);
        return instructions.toArray(new ExpressionInstruction[instructions.size()]);
    }

    private void compileNode(ExpressionNode node) {
        if (node instanceof LiteralNode) {
            LiteralNode literal = (LiteralNode) node;
            if (literal.type == ExpressionValueType.BOOLEAN) {
                instructions.add(new BooleanInstruction(literal.value != 0.0));
            } else {
                instructions.add(new NumberInstruction(literal.value));
            }
        } else if (node instanceof VariableNode) {
            VariableNode variable = (VariableNode) node;
            if (variable.type == ExpressionValueType.BOOLEAN) {
                instructions.add(new BooleanVariableInstruction(variable.name));
            } else {
                instructions.add(new VariableInstruction(variable.name));
            }
        } else if (node instanceof UnaryNode) {
            UnaryNode unary = (UnaryNode) node;
            compileNode(unary.operand);
            instructions.add(new OperatorInstruction(unary.operator));
        } else if (node instanceof BinaryNode) {
            compileBinary((BinaryNode) node);
        } else if (node instanceof MethodCallNode) {
            MethodCallNode call = (MethodCallNode) node;
            for (ExpressionNode argument : call.arguments) {
                compileNode(argument);
            }
            if (call.method.returnType == ExpressionValueType.BOOLEAN) {
                instructions.add(new BooleanMethodInstruction(call.method));
            } else {
                instructions.add(new MethodInstruction(call.method));
            }
        } else {
            throw new IllegalStateException("未知 AST 节点：" + node.getClass().getName());
        }
    }

    private void compileBinary(BinaryNode binary) {
        if (binary.operator == BinaryOperator.AND) {
            compileAnd(binary);
            return;
        }
        if (binary.operator == BinaryOperator.OR) {
            compileOr(binary);
            return;
        }
        compileNode(binary.left);
        compileNode(binary.right);
        instructions.add(new OperatorInstruction(binary.operator));
    }

    private void compileAnd(BinaryNode binary) {
        compileNode(binary.left);
        JumpIfFalseInstruction leftIsFalse = emit(new JumpIfFalseInstruction(-1));
        compileNode(binary.right);
        JumpIfFalseInstruction rightIsFalse = emit(new JumpIfFalseInstruction(-1));
        instructions.add(new BooleanInstruction(true));
        JumpInstruction end = emit(new JumpInstruction(-1));
        int falseTarget = instructions.size();
        instructions.add(new BooleanInstruction(false));
        int endTarget = instructions.size();
        leftIsFalse.patchTarget(falseTarget);
        rightIsFalse.patchTarget(falseTarget);
        end.patchTarget(endTarget);
    }

    private void compileOr(BinaryNode binary) {
        compileNode(binary.left);
        JumpIfTrueInstruction leftIsTrue = emit(new JumpIfTrueInstruction(-1));
        compileNode(binary.right);
        JumpIfTrueInstruction rightIsTrue = emit(new JumpIfTrueInstruction(-1));
        instructions.add(new BooleanInstruction(false));
        JumpInstruction end = emit(new JumpInstruction(-1));
        int trueTarget = instructions.size();
        instructions.add(new BooleanInstruction(true));
        int endTarget = instructions.size();
        leftIsTrue.patchTarget(trueTarget);
        rightIsTrue.patchTarget(trueTarget);
        end.patchTarget(endTarget);
    }

    private <T extends ExpressionInstruction> T emit(T instruction) {
        instructions.add(instruction);
        return instruction;
    }
}
