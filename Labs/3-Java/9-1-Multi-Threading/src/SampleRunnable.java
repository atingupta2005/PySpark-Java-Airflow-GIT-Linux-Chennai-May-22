public class SampleRunnable implements Runnable{
 
    public void run() {
    	try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
        System.out.println("In run method - SampleRunnable");
    }
}