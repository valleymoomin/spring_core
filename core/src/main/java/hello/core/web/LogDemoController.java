package hello.core.web;


import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor //생성자에 autowired 자동주입
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger; //mylogger 주입받는게 아니라
    // mylogger 찾을수 있는 Dependency lookup할수있는애가 주입
//    private final ObjectProvider<MyLogger> myLoggerProvider;
    //주입시점에 주입받을 수 있다.

    @RequestMapping("log-demo")
    @ResponseBody //화면 뷰 없이 바로 문자반환
    public String logDemo(HttpServletRequest request) throws InterruptedException {

        String requestURL = request.getRequestURL().toString();
//        MyLogger myLogger = myLoggerProvider.getObject();//필요한시점에
        //컨트롤러에 고객요청이 왔다. = Http가 살아있따. 그 상태에서 scope를 쓸수있음. myLogger꺼낼수있다.

        System.out.println("myLogger = " + myLogger.getClass());
        //스프링이 조작한애가 스프링빈으로 등록되어있음. 마치 Provider처럼 동작 껍데기myLogger
        //myLogger = class hello.core.common.MyLogger$$EnhancerBySpringCGLIB$$f64f9548
        //실제 기능호출하는 시점에 진짜를 찾아서 동작

        myLogger.setRequestURL(requestURL); //myLogger은 request Scope

        myLogger.log("controller test");//여기는 컨트롤러야
        Thread.sleep(1000);
        logDemoService.logic("testID"); //여러번 요청. service까지 uuid가 그대로 유지된다.
        return "OK";

    }
}
