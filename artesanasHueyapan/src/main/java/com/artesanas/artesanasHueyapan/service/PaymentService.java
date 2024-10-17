package com.artesanas.artesanasHueyapan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.artesanas.artesanasHueyapan.model.Payment;
import com.artesanas.artesanasHueyapan.repository.PaymentRepository;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAll(){
        return paymentRepository.findAll();
    }

    public void save(Payment payment){
        paymentRepository.save(payment);
    }

    public Payment getIdPayment(Long idPayment){
        return paymentRepository.findById(idPayment).get();
    }

    public void delete(Long idPayment){
        paymentRepository.deleteById(idPayment);
    }
}
