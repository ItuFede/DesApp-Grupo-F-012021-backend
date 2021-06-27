package ar.edu.unq.desapp.grupof012021.backenddesappapi.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Aspect
@Component
@Order(0)
public class LogControllerMethodAspect {
    private ObjectMapper mapper = new ObjectMapper();

    @Around("execution(* ar.edu.unq.desapp.grupof012021.backenddesappapi.webservice..*(..))")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String model = this.getModel(signature);
        String httpHeader = this.getHttpHeader(signature);
        String username = this.getAuthenticatedUsername();
        String method = signature.getMethod().getName();
        String argsParams = this.getMethodArguments(
                joinPoint.getArgs(), signature.getParameterNames(), signature.getParameterTypes()
        );

        long execStartTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long execTime = System.currentTimeMillis() - execStartTime;
        this.log(model, httpHeader, method, Long.toString(execTime), username, argsParams, this.getExecStatusText(proceed));
        return proceed;
    }

    private String getModel(MethodSignature signature) {
        String methodPath = signature.toString();
        String[] packages = methodPath.split("\\.");
        String model = packages[packages.length - 2];
        return model.replace("Controller","");
    }

    private String getMethodArguments(Object[] args, String[] paramNames, Class[] paramTypes) {
        List<Map<String, String>> argsParams = Arrays.stream(args).map(argument -> {
            Map<String, String> argParam = new HashMap<>();
            String[] paramTypePackages = paramTypes[Arrays.asList(args).indexOf(argument)].getName().split("\\.");
            argParam.put("type", paramTypePackages[paramTypePackages.length-1]);
            argParam.put("param", paramNames[Arrays.asList(args).indexOf(argument)]);
            argParam.put("arg", String.valueOf(argument));
            return argParam;
        }).collect(Collectors.toList());

        final String[] methodArguments = {""};

        argsParams.forEach(argParam -> {
            methodArguments[0] += "(" + argParam.get("type") + ") " + argParam.get("param") + " ${ " + argParam.get("arg") + " }; ";
        });

        if(methodArguments[0] == "") {
            return "NONE";
        } else {
            return methodArguments[0];
        }
    }

    private String getAuthenticatedUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() == "anonymousUser") {
            return "NOT AUTHENTICATED";
        } else {
            return auth.getName();
        }
    }

    private String getCurrentTime() {
        long seconds = (long) (System.currentTimeMillis() / 1000.0);
        long s = seconds % 60;
        long m = (seconds / 60) % 60;
        long h = (seconds / (60 * 60)) % 24;
        return String.format("%d:%02d:%02d", h,m,s);
    }

    private String getHttpHeader(MethodSignature signature) {
        return signature.getMethod().getAnnotation(RequestMapping.class).method()[0].name();
    }

    private String getExecStatusText(Object proceed) {
        int execStatus = ((ResponseEntity) proceed).getStatusCode().value();
        return List.of(200, 201, 202, 204).contains(execStatus) ? "SUCCESS" : "FAILED";
    }

    private void log(String model, String httpHeader, String method, String execTime,
    String username, String argsParams, String execStatus) {
        // [00:35:24] [Health] GET /health, executed in 500ms, called by user:  "", called with: ""
        String timestamp = this.getCurrentTime();
        String log = "[" + timestamp + "] " + "[" + execStatus + "] " +
                "[" + model + "] " + httpHeader + " /" + method +
                ", executed in " + execTime + "ms, called by user: \"" +
                username + "\", called with: " + argsParams;
        System.out.println(log);
    }
}
