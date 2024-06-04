package pract.oop_java.pms.v1.services;

import org.springframework.stereotype.Service;
import pract.oop_java.pms.v1.dto.requests.CreateCustomerDTO;
import pract.oop_java.pms.v1.dto.requests.LoginRequest;
import pract.oop_java.pms.v1.models.Customer;

import java.util.List;
import java.util.UUID;


public interface CustomerService {

  List <Customer> getAllCustomers() ;

  Customer createCustomer (CreateCustomerDTO customerDTO);
  Customer getCustomerById(UUID uuid);
  Customer deleteCustomerById(UUID uuid);

  Customer updateCustomer (CreateCustomerDTO customerDTO, UUID id);



    Customer login(LoginRequest loginRequest);

    Customer getCustomerByEmail (String email);
}
