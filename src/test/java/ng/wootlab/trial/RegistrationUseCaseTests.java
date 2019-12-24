package ng.wootlab.trial;

import ng.wootlab.trial.model.Customer;
import ng.wootlab.trial.repository.CustomerRepository;
import ng.wootlab.trial.repository.RoleRepository;
import ng.wootlab.trial.service.CustomerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RegistrationUseCaseTests {

    @Autowired CustomerRepository customerRepository;
    @Autowired RoleRepository roleRepository;

    private CustomerService customerService;

    @BeforeEach
    void init() {
        customerService = new CustomerService(customerRepository, roleRepository);
    }

    @AfterEach
    void destroy() {
        roleRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Test
    void testCustomerRegistration() {
        // given
        Customer customer = new Customer("John", "Doe", "j.doe@testing.net",
                "password");
        customer = customerService.registerCustomer(customer);

        // when
        Customer foundCustomer = customerRepository.findByEmail(customer.getEmail());

        // then
        Assertions.assertThat(foundCustomer).isNotNull();
        Assertions.assertThat(foundCustomer.getEmail()).isEqualTo(customer.getEmail());
    }

}
