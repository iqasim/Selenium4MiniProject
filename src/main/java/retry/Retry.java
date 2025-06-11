package retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	
	int count = 0;
	int maxLimit = 1;
	
	 public boolean retry(ITestResult result) {
		if(count<maxLimit) {
			count++;
			return true;
		}
		 
		 return false;
		 
	 }
	
	

}
