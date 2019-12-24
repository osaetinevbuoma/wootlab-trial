package ng.wootlab.trial.security;

import ng.wootlab.trial.Utilities;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithCustomerDetailsSecurityContextFactory.class,
        setupBefore = TestExecutionEvent.TEST_EXECUTION)
public @interface WithMockCustomer {
    int id() default 1;
    String email() default Utilities.EMAIL;
    String firstName() default Utilities.FIRST_NAME;
    String surname() default Utilities.SURNAME;
    String password() default Utilities.PASSWORD;
    String role() default Utilities.ROLE_USER;
}
