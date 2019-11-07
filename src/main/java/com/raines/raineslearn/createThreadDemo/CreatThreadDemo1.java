package com.raines.raineslearn.createThreadDemo;

/**
 * 继承Thread类，作为线程对象存在（继承Thread对象）
 */
public class CreatThreadDemo1 extends Thread{
    /**
     * 构造方法： 继承父类方法的Thread(String name)；方法
     * @param name
     */
    public CreatThreadDemo1(String name){
        super(name);
    }

    @Override
    public void run() {
        //interrupted方法，是来判断该线程是否被中断
        while (!interrupted()){
            System.out.println(getName()+"线程执行了...");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CreatThreadDemo1 d1 = new CreatThreadDemo1("first");
        CreatThreadDemo1 d2 = new CreatThreadDemo1("second");

        d1.start();
        d2.start();

        //让线程进入等待，直到调用Object的notify或者notifyAll时，线程停止休眠
//        d1.wait();
//        d1.notify();

        d1.interrupt();  //中断第一个线程
    }
}