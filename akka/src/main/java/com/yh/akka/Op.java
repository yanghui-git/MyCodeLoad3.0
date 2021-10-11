package com.yh.akka;

import lombok.Data;
import scala.Serializable;

/**
 * + - * / 操作 以及返回结果
 */
public class Op {

    public interface MathOp extends Serializable {
    }

    public interface MathResult extends Serializable {
    }

    //加法操作
    @Data
    static class Add implements MathOp {
        private static final long serialVersionUID = 1L;
        private final int n1;
        private final int n2;
    }

    @Data
    static class AddResult implements MathResult {
        private static final long serialVersionUID = 1L;
        private final int n1;
        private final int n2;
        private final int result;
    }


    //减法操作
    @Data
    static class Subtract implements MathOp {
        private static final long serialVersionUID = 1L;
        private final int n1;
        private final int n2;
    }

    @Data
    static class SubtractResult implements MathResult {
        private static final long serialVersionUID = 1L;
        private final int n1;
        private final int n2;
        private final int result;
    }

    //乘法操作
    @Data
    static class Multiply implements MathOp {
        private static final long serialVersionUID = 1L;
        private final int n1;
        private final int n2;
    }

    @Data
    static class MultiplicationResult implements MathResult {
        private static final long serialVersionUID = 1L;
        private final int n1;
        private final int n2;
        private final int result;
    }

    // 除法操作
    @Data
    static class Divide implements MathOp {
        private static final long serialVersionUID = 1L;
        private final double n1;
        private final int n2;
    }

    @Data
    static class DivisionResult implements MathResult {
        private static final long serialVersionUID = 1L;
        private final double n1;
        private final int n2;
        private final double result;
    }
}