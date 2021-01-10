# audit-lib
This is a common logging library which takes control of the logging of the spring boot application that dependent on this library. 

The library outputs logs in json. In addition, it adds more audit information into logs including trace id. For example,
```json
{
	"timestamp": "2019-04-16T13:28:12.211-04:00",
	"level": "INFO",
	"thread": "http-nio-8003-exec-2",
	"loggerName": "com.iconective.demo.DemoController",
	"message": "Inside Demo 3..",
	"requestURI": "/demo",
	"port": "8003",
	"traceid": "7bcd6a936731b49e"
}
```
