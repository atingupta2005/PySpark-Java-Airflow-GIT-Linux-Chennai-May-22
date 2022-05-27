public class JavaRunnableMain {
 
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable1=new SampleRunnable();
        // Pass Runnable to constructor of thread class
        Thread t1=new Thread(runnable1);
        
        t1.setName("t1");
        
        Runnable runnable2=new SampleRunnable();
        // Pass Runnable to constructor of thread class
        Thread t2=new Thread(runnable2);
        
        
        Runnable runnable3=new AnotherRunnable();
        // Pass Runnable to constructor of thread class
        Thread t3=new Thread(runnable3);
        
        
        System.out.println("In main method-1");
        
        t1.start();
        t2.start();
        t3.start();
        
        t1.join();
        //t2.join();
        //t3.join();
        
        
        
        
        System.out.println("In main method-2");
    }
}