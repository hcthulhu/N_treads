package ru.mipt.edu;

import java.util.*;
import java.lang.Integer;

class MyThread extends Thread {
    private int Num;

    MyThread(int val){
        Num = val;
    }

    @Override
    public void run(){
        while(!isInterrupted()){}
        System.out.println(Num);
    }
}

public class Main {

    private static void delayedPrint(Thread t) throws InterruptedException {
        t.interrupt();
        Thread.sleep(200);
    }

    public static void main(String[] args) {
        Map<Thread, Integer> map = new HashMap<>();
        Scanner in = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            int num = in.nextInt();
            Thread waitThread = new MyThread(num);
            waitThread.start();
            map.put(waitThread, num);
        }

        map.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach( k -> {    // sorting map by values
            try {                                                                       // interrupting sorted threads
                delayedPrint(k.getKey());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
