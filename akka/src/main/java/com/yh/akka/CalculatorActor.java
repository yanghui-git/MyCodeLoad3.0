package com.yh.akka;

import akka.actor.UntypedActor;

/**
 * 接受消息并且处理
 */
public class CalculatorActor extends UntypedActor {

    public void onReceive(Object message) throws Throwable {
        if (message instanceof Op.Add) {
            Op.Add add = (Op.Add) message;
            System.out.println("Calculating " + add.getN1() + " + " + add.getN2());
            Op.AddResult result = new Op.AddResult(add.getN1(), add.getN2(),
                    add.getN1() + add.getN2());
            getSender().tell(result, getSelf());

        } else if (message instanceof Op.Subtract) {
            Op.Subtract subtract = (Op.Subtract) message;
            System.out.println("Calculating " + subtract.getN1() + " - "
                    + subtract.getN2());
            Op.SubtractResult result = new Op.SubtractResult(subtract.getN1(),
                    subtract.getN2(), subtract.getN1() - subtract.getN2());
            getSender().tell(result, getSelf());
        } else if (message instanceof Op.Multiply) {
            Op.Multiply multiply = (Op.Multiply) message;
            System.out.println("Calculating " + multiply.getN1() + " * "
                    + multiply.getN2());
            Op.MultiplicationResult result = new Op.MultiplicationResult(
                    multiply.getN1(), multiply.getN2(), multiply.getN1()
                    * multiply.getN2());
            getSender().tell(result, getSelf());

        } else if (message instanceof Op.Divide) {
            Op.Divide divide = (Op.Divide) message;
            System.out.println("Calculating " + divide.getN1() + " / "
                    + divide.getN2());
            Op.DivisionResult result = new Op.DivisionResult(divide.getN1(),
                    divide.getN2(), divide.getN1() / divide.getN2());
            getSender().tell(result, getSelf());

        } else {
            unhandled(message);
        }
    }
}
