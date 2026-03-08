package com.channels.ims.plp.repository;

import com.channels.ims.plp.entity.tables.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductsRepository extends JpaRepository<Products, UUID> {


    Products findByProductId(Integer productId);
}
