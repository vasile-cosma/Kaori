package es.iesclaradelrey.da2d1e.shopvlcdio.web.config;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.SecurityEvent;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.enums.SecurityEventType;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.SecurityEventService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SecurityMonitor implements LogoutSuccessHandler {

    private final SecurityEventService securityEventService;

    public SecurityMonitor(SecurityEventService securityEventService) {
        this.securityEventService = securityEventService;
    }

    @EventListener
    public void onSucces(AuthenticationSuccessEvent event){
        System.out.println("ESTADO: Login exitoso");
        Authentication auth = event.getAuthentication();
        SecurityEvent securityEvent = SecurityEvent.builder()
                .username(auth.getName())
                .type(SecurityEventType.LOGIN)
                .build();
        securityEventService.save(securityEvent);
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent event){
        Authentication auth = event.getAuthentication();

        if (auth.getName().isBlank() || auth.getName() == null){
            SecurityEvent securityEvent = SecurityEvent.builder()
                    .type(SecurityEventType.ERROR)
                    .build();
            securityEventService.save(securityEvent);
        } else {
            SecurityEvent securityEvent = SecurityEvent.builder()
                    .username(auth.getName())
                    .type(SecurityEventType.ERROR)
                    .build();
            securityEventService.save(securityEvent);
        }

    }


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, @Nullable Authentication authentication) throws IOException, ServletException {
        if (authentication != null){
            SecurityEvent securityEvent = SecurityEvent.builder()
                    .username(authentication.getName())
                    .type(SecurityEventType.LOGOUT)
                    .build();
            securityEventService.save(securityEvent);
        }
    }

}
