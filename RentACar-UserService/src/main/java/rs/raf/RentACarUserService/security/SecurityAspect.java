package rs.raf.RentACarUserService.security;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import rs.raf.RentACarUserService.security.service.TokenService;

import java.lang.reflect.Method;
import java.util.Arrays;

@AllArgsConstructor
@Aspect
@Configuration
public class SecurityAspect {

    private TokenService tokenService;

    @Around("@annotation(rs.raf.RentACarUserService.security.CheckRole)")
    public Object checkRole(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        String token = null;
        for (int i = 0; i < methodSignature.getParameterNames().length; i++) {
            if (methodSignature.getParameterNames()[i].equals("authorization") && joinPoint.getArgs()[i].toString().startsWith("Bearer"))
                token =  joinPoint.getArgs()[i].toString().split(" ")[1];
        }
        if (token == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Claims claims = tokenService.parseToken(token);
        if (claims == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        String role = claims.get("role", String.class);

        CheckRole checkRole = method.getAnnotation(CheckRole.class);

        if (Arrays.asList(checkRole.roles()).contains(role))
            return joinPoint.proceed();

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @Around("@annotation(rs.raf.RentACarUserService.security.CheckID)")
    public Object checkID(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        String token = null;
        String id = null;
        for (int i = 0; i < methodSignature.getParameterNames().length; i++) {
            if (methodSignature.getParameterNames()[i].equals("authorization") && joinPoint.getArgs()[i].toString().startsWith("Bearer"))
                token =  joinPoint.getArgs()[i].toString().split(" ")[1];
            if (methodSignature.getParameterNames()[i].equals("id"))
                id =  joinPoint.getArgs()[i].toString();
        }
        if (token == null || id == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        Claims claims = tokenService.parseToken(token);
        if (claims == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        String tokenId = claims.get("id", String.class);
        String tokenRole = claims.get("role", String.class);

        if (tokenRole.equals("ADMIN") || tokenId.equals(id))
            return joinPoint.proceed();

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
