package ng.wootlab.trial;

import ng.wootlab.trial.model.Customer;
import ng.wootlab.trial.repository.CustomerRepository;
import ng.wootlab.trial.repository.RoleRepository;
import ng.wootlab.trial.service.CustomerService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

//@SpringBootTest
@RunWith(SpringRunner.class)
@DataJpaTest
class WootlabTechnicalTrialApplicationTests {

    @Autowired CustomerRepository customerRepository;
    @Autowired RoleRepository roleRepository;
    @Autowired TestEntityManager testEntityManager;

    private CustomerService customerService;

    @Before
    public void setUp() {

    }

    @BeforeEach
    void initServices() {
        customerService = new CustomerService(customerRepository, roleRepository);
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
