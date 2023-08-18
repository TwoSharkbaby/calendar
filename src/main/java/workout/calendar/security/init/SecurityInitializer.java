/*
package workout.calendar.security.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SecurityInitializer implements ApplicationRunner {

    private final RoleHierarchyImpl roleHierarchy;
    private final String allHierarchy = "ROLE_ADMIN > ROLE_MANAGER"
            + "\n" + "ROLE_MANAGER > ROLE_USER";
    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        roleHierarchy.setHierarchy(allHierarchy);
    }
}
*/
