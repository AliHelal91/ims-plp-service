package com.channels.ims.plp.repository;

import com.channels.ims.plp.entity.tables.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductsRepository extends JpaRepository<Products, UUID> {


    Optional<Products> findByProductId(Integer productId);
}
