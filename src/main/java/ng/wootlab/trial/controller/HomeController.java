package ng.wootlab.trial.controller;

import ng.wootlab.trial.model.Customer;
import ng.wootlab.trial.service.AuthenticationService;
import ng.wootlab.trial.service.CustomerService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.validation.Valid;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
public class HomeController {

    private static final Logger log = Logger.getLogger(HomeController.class.getName());

    private AuthenticationService authenticationService;
    private CustomerService customerService;

    public HomeController(AuthenticationService authenticationService,
                          CustomerService customerService) {
        this.authenticationService = authenticationService;
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String login(@RequestParam("failed") Optional<String> failed, Model model) {
        if (failed.isPresent()) {
            model.addAttribute("error", "Invalid email and password " +
                    "combination");
        }

        return "home/login";
    }

    @GetMapping("/logout")
    private String logout(HttpServletRequest httpServletRequest) {
        Authentication authentication = authenticationService.getAuthentication();
        if (null != authentication) {
            try {
                new HttpServletRequestWrapper(httpServletRequest).logout();
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }

        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("customer", new Customer());
        return "home/register";
    }

    @PostMapping("/register")
    public String saveCustomer(@ModelAttribute @Valid Customer customer,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "home/register";
        }

        customer = customerService.registerCustomer(customer);
        if (customer == null) {
            redirectAttributes.addFlashAttribute("error", "A customer " +
                    "with this email address already exist.");
            return "redirect:/register";
        }

        redirectAttributes.addFlashAttribute("success", "Registration " +
                "successful. Please sign in.");
        redirectAttributes.addFlashAttribute("customerEmail", customer.getEmail());
        return "redirect:/";
    }
}
