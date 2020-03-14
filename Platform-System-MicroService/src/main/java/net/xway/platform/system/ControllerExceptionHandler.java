package net.xway.platform.system;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionHandler {

	private Log log = LogFactory.getLog(ControllerExceptionHandler.class);
	
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Exception> errorHandler(Exception ex) {
		log.error(null, ex);
		return ResponseEntity.badRequest().body(ex);
	}

}
