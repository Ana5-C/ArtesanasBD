package com.artesanas.artesanasHueyapan.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artesanas.artesanasHueyapan.model.Product;
import com.artesanas.artesanasHueyapan.repository.ProductRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    
    public Product save(Product product){
        return productRepository.save(product);
    }
    /*public void save(Product product){
        productRepository.save(product);
    }*/

    public Product getByIdProduct(Long idProduct){
        return productRepository.findById(idProduct).get();
    }

    public void delete(Long idProduct){
        productRepository.deleteById(idProduct);
    }
}
