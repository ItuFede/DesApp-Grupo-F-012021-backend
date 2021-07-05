package ar.edu.unq.desapp.grupof012021.backenddesappapi.aspect;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.FirebaseService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Aspect
@Component
@Order(0)
public class LogControllerMethodAspect {

    @Autowired
    FirebaseService firebaseService;

    @Around("execution(* ar.edu.unq.desapp.grupof012021.backenddesappapi.webservice..*(..))")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        long execStartTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long execTime = System.currentTimeMillis() - execStartTime;

        this.log(
                this.getModel(signature),
                this.getHttpHeader(signature),
                signature.getMethod().getName(),
                Long.toString(execTime),
                this.getAuthenticatedUsername(),
                this.getMethodArguments(
                        joinPoint.getArgs(), signature.getParameterNames(), signature.getParameterTypes()
                ),
                this.getExecStatusText(proceed)
        );
        return proceed;
    }

    private void log(String model, String httpHeader, String method, String execTime,
                     String username, String argsParams, String execStatus) {
        String timestamp = this.getCurrentTime();
        String log = "[" + timestamp + "] " + "[" + execStatus + "] " +
                "[" + model + "] " + httpHeader + " /" + method +
                ", executed in " + execTime + "ms, called by user: \"" +
                username + "\", called with: " + argsParams;
        System.out.println(log);
        if (!username.equals("NOT AUTHENTICATED")) {
            this.generateMetric(log, username);
        }
    }

    private void generateMetric(String log, String username) {
        HashMap<String, Object> metric = new HashMap<>();
        metric.put("log", log);
        metric.put("user", username);
        try {
            firebaseService.post("metrics", metric);
        } catch (Exception e) {
            String errTimestamp = this.getCurrentTime();
            String firePostLog = "[" + errTimestamp + "] [FAILED] [Firebase] failed to post metric to metrics collection. Original log: {{{ " +
                    log + " }}}";
            System.out.println(firePostLog);
            e.printStackTrace();
        }
    }

    private String getCurrentTime() {
        long seconds = (long) (System.currentTimeMillis() / 1000.0);
        long s = seconds % 60;
        long m = (seconds / 60) % 60;
        long h = (seconds / (60 * 60)) % 24;
        return String.format("%d:%02d:%02d", h, m, s);
    }

    private String getModel(MethodSignature signature) {
        String methodPath = signature.toString();
        String[] packages = methodPath.split("\\.");
        String model = packages[packages.length - 2];
        return model.replace("Controller", "");
    }

    private String getMethodArguments(Object[] args, String[] paramNames, Class[] paramTypes) {
        List<Map<String, String>> argsParams = Arrays.stream(args).map(argument -> {
            Map<String, String> argParam = new HashMap<>();
            String[] paramTypePackages = paramTypes[Arrays.asList(args).indexOf(argument)].getName().split("\\.");
            argParam.put("type", paramTypePackages[paramTypePackages.length - 1]);
            argParam.put("param", paramNames[Arrays.asList(args).indexOf(argument)]);
            argParam.put("arg", String.valueOf(argument));
            return argParam;
        }).collect(Collectors.toList());

        final String[] methodArguments = {""};

        argsParams.forEach(argParam -> {
            methodArguments[0] += "(" + argParam.get("type") + ") " + argParam.get("param") + " ${ " + argParam.get("arg") + " }; ";
        });

        if (methodArguments[0] == "") {
            return "NO ARGUMENTS";
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

    private String getHttpHeader(MethodSignature signature) {
        return signature.getMethod().getAnnotation(RequestMapping.class).method()[0].name();
    }

    private String getExecStatusText(Object proceed) {
        int execStatus = ((ResponseEntity) proceed).getStatusCode().value();
        return List.of(200, 201, 202, 204).contains(execStatus) ? "SUCCESS" : "FAILED";
    }
}
