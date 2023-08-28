package workout.calendar.domain.AwareAudit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import workout.calendar.domain.entity.User;


import java.util.Optional;

public class UserAwareAudit implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null == authentication || !authentication.isAuthenticated() || authentication.getPrincipal() == "anonymousUser") {
            return null;
        }
        User user = (User) authentication.getPrincipal();
        return Optional.of(user.getUsername());
    }
}
