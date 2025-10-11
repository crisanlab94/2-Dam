package ProbandoLogs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 
public class PorbandoLog {
	

	private static final Logger logger = LogManager.getLogger(PorbandoLog.class);

	public static void main(String[] args) {
		logger.debug("Hola jijij");
		
	}}