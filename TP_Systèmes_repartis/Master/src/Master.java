import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Master {

	public static void main(String[] args) throws InterruptedException, TimeoutException, IOException {
		// TODO Auto-generated method stub
		ProcessBuilder pb = new ProcessBuilder();
		System.out.println(pb);
	    //pb.command("ls", "-al", "/tmp");
	    pb.command("java", "-jar", "Slave.jar");
	    //pb.command("ls", "/jesuisunhero");
	   // processBuilder.directory(new File("C:\\users"));

        // can also run the java file like this
        // processBuilder.command("java", "Hello");
	   //ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10); 
        

        
            Process process = pb.inheritIO().start();
            boolean success =  process.waitFor(15, TimeUnit.SECONDS);
            //boolean success =  process.waitFor(15, TimeUnit.SECONDS);
            System.out.println(success);
            
            if (!success) {
                //throw new TimeoutException();
                process.destroy();
             }
            
            else {
            	
	            try {
	            
	            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	            BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
	            String line;        
	            
	            
	            while ((line = reader.readLine()) != null) {
	                System.out.println(line);
	            }
	            
	            while ((line = error.readLine()) != null) {
	                System.out.println(line);
	            }
	
	            int exitCode = process.waitFor();
	            System.out.println("\nExited with error code : " + exitCode);
	
	        } catch (IOException e) {
	            e.printStackTrace();
	        }catch (InterruptedException e) {
	            e.printStackTrace();
	        }
        }

	}
}
