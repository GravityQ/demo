package com.gravity.demo;

/**
 * @author qijunlin
 * @date 2019/9/29 3:20 下午
 */

public class Demo {
//    public static void main(String[] args) {
//        for (int i = 0; i < 100; i++) {
//            Thread thread = new Thread(() -> {
//                try {
//                    Thread.sleep(100);
//                    if (Counter.addOne() == 100) {
//                        System.out.println("counter = 100");
//                    }else {
//                        System.out.println(Counter.counter);
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//            thread.start();
//        }
//        AtomicLong atomicLong = new AtomicLong();
//        long counter = atomicLong.incrementAndGet();
//        System.out.println(counter);
//    }

    public static class Counter {
        private static long counter = 0;

        public static long addOne() {
            return ++counter;
        }
    }

//    public static void main(String[] args) {
//        for (int i = 0; i < 10000; i++) {
//            repeatMessage("hello", 100);
//        }
//    }

    public static void repeatMessage(String text, int count) {
        Runnable r = () -> {
            for (int i = 0; i < count; i++) {
                System.out.println(text);
                Thread.yield();
            }
        };
        new Thread(r).start();
    }


//    public static void main(String[] args) {
//        String grabRequestStr="";
//        Pattern pattern = Pattern.compile("(?<=redtext=)(.+?)(?=&content)");
//        Matcher matcher = pattern.matcher(s);
//        if (matcher.find()) {
//            System.out.println(matcher.group());
//        }
//        String s1 = grabRequestStr.substring(grabRequestStr.indexOf("redtext=")+8, grabRequestStr.indexOf("&content"));
//        System.out.println(s1);
//    }
}
