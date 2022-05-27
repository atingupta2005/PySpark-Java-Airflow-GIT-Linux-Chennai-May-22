public class AnotherRunnable implements Runnable{
 
    public void run() {
    	try {
			Thread.sleep(0);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("In run method - AnotherRunnable");
    }
}