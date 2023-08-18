package workout.calendar.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("CustomAuthenticationSuccessHandler.onAuthenticationSuccess");
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        setDefaultTargetUrl("/");
        if (savedRequest != null) {
            // 인증이 필요한 자원에 접근을 시도해서 로그인을 하는 경우
            // 로그인이 성공하면 시도했던 자원으로 리다이렉트
            String targetUrl = savedRequest.getRedirectUrl();
            redirectStrategy.sendRedirect(request, response, targetUrl);
        } else {
            // 로그인을 클릭해서 로그인 하는 경우
            redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl());
        }
    }
}
