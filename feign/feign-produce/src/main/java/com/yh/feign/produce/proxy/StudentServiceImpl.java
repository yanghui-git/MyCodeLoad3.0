package com.yh.feign.produce.proxy;

//TODO 如何增加审查日志  遵循开闭原则 对扩展开放 修改关闭
public class StudentServiceImpl implements StudentService {

    @Override
    public void add() {
        System.out.println("正在添加学生...................");
    }
}
