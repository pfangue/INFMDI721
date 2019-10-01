import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Deploy {
	


	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		List<String> file = Files.readAllLines(Paths.get("/var/www/machines.txt"));
		ProcessBuilder pb = new ProcessBuilder();
		ProcessBuilder pb1 = new ProcessBuilder();
		
		for (String machine : file) 
		{	
			/*pb.command("ping", machine);
			Process process = pb.start();
			System.out.println(process);
			
			try {
	            
	            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	            BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
	            String line;        
	            
	            if ((line = reader.readLine()) != null) {
	            	
	                System.out.println(line);
	                continue;
	            }
	            
	            if ((line = error.readLine()) != null) {
	                System.out.println(line);
	                
	            }
	
	            int exitCode = process.waitFor();
	            System.out.println("\nExited with error code : " + exitCode);
	
	        } catch (IOException e) {
	            e.printStackTrace();
	        }catch (InterruptedException e) {
	            e.printStackTrace();
	        }*/
			pb.command("ping", machine);
			Process process = pb.inheritIO().start();
            boolean success =  process.waitFor(2, TimeUnit.SECONDS);
            //boolean success =  process.waitFor(15, TimeUnit.SECONDS);
            System.out.println(success);
            
            if (!success) {
                //throw new TimeoutException();
                process.destroy();
             }
            
            else {
            	
	            try {
	            pb1.command("ssh", machine, "mkdir -p /tmp/pfangue/", "scp", "Slave.jar", machine, "/tmp/pfangue/");
	            pb1.start();
	            System.out.println(pb1);
	            
	            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	            BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
	            String line;        
	            
	            
	            if ((line = reader.readLine()) != null) {
	                System.out.println(line);
	            }
	            
	            if ((line = error.readLine()) != null) {
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

}
