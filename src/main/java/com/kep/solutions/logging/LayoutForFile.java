package com.kep.solutions.logging;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.LayoutBase;
import org.slf4j.MDC;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LayoutForFile extends LayoutBase<ILoggingEvent> {

	 public String doLayout(ILoggingEvent event) {
		 ObjectMapper mapper = new ObjectMapper();
		 			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		 			format.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		 			JsonOutput output = new JsonOutput();
		 			output.setTimestamp(format.format(new Date(event.getTimeStamp())));
		 			output.setLevel(event.getLevel().toString());
		 			output.setThread(event.getThreadName());
		 			output.setLoggerName(event.getLoggerName());
		 			output.setMessage(event.getFormattedMessage());
		 			output.setRequestURI(MDC.get("uri"));
		 			output.setTraceid(MDC.get("traceid"));
		 			output.setPort(MDC.get("port"));
		 			

		 			try {
		 				return mapper.writeValueAsString(output) + CoreConstants.LINE_SEPARATOR;
		 			} catch (JsonProcessingException e) {
		 				// TODO Auto-generated catch block
		 				e.printStackTrace();
		 			}
		 			return null;
		       }
}