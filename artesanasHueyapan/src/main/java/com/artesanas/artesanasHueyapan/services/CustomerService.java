package com.artesanas.artesanasHueyapan.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.artesanas.artesanasHueyapan.model.Customer;
import com.artesanas.artesanasHueyapan.repository.CustomerRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    public void save(Customer customer){
        customerRepository.save(customer);
    }

    public Customer getIdCustomer(Long idCustomer){
        return customerRepository.findById(idCustomer).get();
    }

    public void delete(Long idCustomer){
        customerRepository.deleteById(idCustomer);
    }
}

