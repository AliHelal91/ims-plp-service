package com.channels.ims.plp.entity.tables;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "general_configuration", schema = "ims_plp_service_db")
public class GeneralConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "general_configuration_gen")
    @SequenceGenerator(name = "general_configuration_gen", sequenceName = "general_configuration_seq",
            allocationSize = 1, schema = "ims_plp_service_db")
    private Long id;

    @Column(name = "code" , nullable = false,unique = true)
    private String code;

    @Column(name = "value" , nullable = false)
    private String value;

    @Column(name = "status" , nullable = false)
    private String status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updateAt;
}
