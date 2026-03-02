package com.channels.ims.ims_plp_services.repository;

import com.channels.ims.ims_plp_services.entity.tables.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductsRepository extends JpaRepository<Products, UUID> {


    Products findByProductId(Integer productId);
}
