package vn.iotstar.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import vn.iotstar.model.Customer;

@RestController
@EnableMethodSecurity
public class CustomerController {
	final private List<Customer> customers = List.of(Customer.builder().id("001").name("Ka Phúc").email("phucka004@gmail.com").build(),
			Customer.builder().id("002").name("Ka Phúc").email("22110398@student.hcmute.edu.vn").build());
	@GetMapping("/hello")
	public ResponseEntity<String> hello() {
		return ResponseEntity.ok("hello is Gest");
	}
	@GetMapping("customer/all")
	@PreAuthorize("hasAuthorize('ROLE_ADMIN')")
	public ResponseEntity<List<Customer>> getCustomerList() {
		List<Customer> list = this.customers;
		return ResponseEntity.ok(list);
	}
	@GetMapping("/customer/{id}")
	@PreAuthorize("hasAuthorize('ROLE_USER')")
	public ResponseEntity<Customer> getCustomerList(@PathVariable("id") String id ) {
		List<Customer> customers = this.customers.stream().filter(customer -> customer.getId().equals(id)).toList();
		return ResponseEntity.ok(customers.get(0));
	}
}
