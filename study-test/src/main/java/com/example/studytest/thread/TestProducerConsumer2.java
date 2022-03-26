package com.example.studytest.thread;

public class TestProducerConsumer2 {
    public static void main(String[] args) {
        Tv tv = new Tv();
        new Player(tv).start();
        new Watcher(tv).start();
    }

}

class Player extends Thread{
    Tv tv;

    public Player(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if(i%2 == 0){
                tv.play("动物世界");
            }else {
                tv.play("中国好声音");
            }
        }
    }
}

class Watcher extends Thread{
    Tv tv;
    public Watcher(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            tv.watch();
        }
    }
}

class Tv{
    String voice;
    boolean flag = true;

    public synchronized void play(String voice){
        if(!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("演员表演了："+voice);
        this.notifyAll();
        this.flag = !this.flag;
        this.voice = voice;
    }

    public synchronized void watch(){
        if(flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("观众观看了："+voice);
        this.notifyAll();
        this.flag = !this.flag;
    }
}