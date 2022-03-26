package com.example.studytest.thread;

/**
 * 测试生产者和消费者的问题 --- 管程法
 */
public class TestProducerConsumer {
    public static void main(String[] args) {
        SynContainer synContainer = new SynContainer();
        new Producer(synContainer).start();
        new Consumer(synContainer).start();

    }

}

//生产者
class Producer extends Thread{

    SynContainer synContainer;

    public Producer(SynContainer synContainer){
        this.synContainer = synContainer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("消费了"+synContainer.pop().getId()+"产品");
        }
    }
}

//消费者
class Consumer extends Thread{

    SynContainer synContainer;

    public Consumer(SynContainer synContainer){
        this.synContainer = synContainer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("生产了"+(i+1)+"个产品");
            synContainer.push(new Product(i));
        }
    }
}

//产品
class Product{

    int id;
    public Product(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}

//缓冲区
class SynContainer{
    //需要一个容器大小
    Product [] products = new Product[10];
    int count = 0;

    //生产者放入产品
    public synchronized void push(Product product){
        //如果容器满了，就需要等待消费者消费，如果没有满，则存入产品
        if(count == products.length){
            //通知消费者消费，生产者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            products[count] = product;
            count++;
            this.notifyAll();
        }
    }

    //消费者消费产品
    public synchronized Product pop(){
        if(count == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        count --;
        this.notifyAll();
        return products[count];
    }
}