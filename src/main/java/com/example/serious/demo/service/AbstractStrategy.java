package com.example.serious.demo.service;

public abstract class AbstractStrategy {
    public abstract void init();

    public  void run(){
        init();
        doRun();
        after();
    }

    public abstract  void doRun();

    public abstract  void after();
}
