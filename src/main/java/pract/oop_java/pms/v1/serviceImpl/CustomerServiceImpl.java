package pract.oop_java.pms.v1.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pract.oop_java.pms.v1.dto.requests.CreateCustomerDTO;
import pract.oop_java.pms.v1.dto.requests.LoginRequest;
import pract.oop_java.pms.v1.exceptions.BadRequestException;
import pract.oop_java.pms.v1.exceptions.NotFoundException;
import pract.oop_java.pms.v1.models.Customer;
import pract.oop_java.pms.v1.repositories.CustomerRepository;
import pract.oop_java.pms.v1.services.CustomerService;
import pract.oop_java.pms.v1.utils.ExceptionUtils;
import pract.oop_java.pms.v1.utils.Hash;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private  final CustomerRepository customerRepository;
    @Override
    public List<Customer> getAllCustomers() {
        try {
            return customerRepository.findAll();
        }catch(Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public Customer createCustomer(CreateCustomerDTO customerDTO) {

         try {


             Customer customerByEmail = customerRepository.findCustomerByEmail(customerDTO.getEmail()).orElse(null);
             if (customerByEmail != null) {
                 throw new BadRequestException("The user with the given email already exists");
             }

             Customer customerEntity = new Customer();
             customerEntity.setEmail(customerDTO.getEmail());
             customerEntity.setPassword(Hash.hashPassword(customerDTO.getPassword()));
             customerEntity.setFirstName(customerDTO.getFirstName());
             customerEntity.setPhone(customerEntity.getPhone());
             customerRepository.save(customerEntity);


             return customerEntity;

         }catch (Exception e) {
             ExceptionUtils.handleServiceExceptions(e);
             return null;
         }
    }

    @Override
    public Customer getCustomerById(UUID uuid) {
        try {
            Optional<Customer> optionalCustomer = customerRepository.findById(uuid);
            return optionalCustomer.orElseThrow(() -> new NotFoundException("The customer with the given id was not found"));
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public Customer deleteCustomerById(UUID uuid) {
        try {
            Customer customer =  customerRepository
                    .findById(uuid)
                    .orElseThrow(() -> new NotFoundException("The customer with the given id was not found"));
            customerRepository.delete(customer);
            return customer;
        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public Customer updateCustomer(CreateCustomerDTO customerDTO, UUID id) {

         try {
             Customer customerEntity = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("The customer with the provided id was not found"));
             customerEntity.setEmail(customerDTO.getEmail());
             customerEntity.setFirstName(customerDTO.getFirstName());
             customerEntity.setPassword(customerDTO.getPassword());
             customerEntity.setPhone(customerDTO.getPhone());
             return customerEntity;
         }catch (Exception e) {
             ExceptionUtils.handleServiceExceptions(e);
             return null;
         }

    }

    @Override
    public Customer login(LoginRequest loginRequest) {
        try {
            String email = loginRequest.getEmail();
            String password = loginRequest.getPassword();

            Customer customer = customerRepository.findCustomerByEmail(email)
                    .orElseThrow(() -> new NotFoundException("Customer with this email does not exist"));

            if (!Hash.isTheSame(password, customer.getPassword())) {
                throw new BadRequestException("Incorrect password");
            }

            return customer;
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }


    @Override
    public Customer getCustomerByEmail(String email) {
        try {
            Customer customer =  customerRepository.findCustomerByEmail(email).orElseThrow(()-> new NotFoundException("The customer with the given email was not found"));

                return customer;

        } catch(Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }
}
