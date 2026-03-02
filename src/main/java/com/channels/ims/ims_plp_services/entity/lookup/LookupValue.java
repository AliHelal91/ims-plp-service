package com.channels.ims.ims_plp_services.entity.lookup;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "lookup_value", schema = "ims_plp_service_db")
public class LookupValue {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lookup_value_gen")
    @SequenceGenerator(name = "lookup_value_gen", sequenceName = "lookup_value_seq",
            allocationSize = 1, schema = "ims_plp_service_db")
    private Long id;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "display_en", nullable = false)
    private String displayEN;

    @Column(name = "display_ar")
    private String displayAR;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime creationDate;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updateDate;

    @ManyToOne
    @JoinColumn(name = "lookup_category_id",referencedColumnName = "id")
    private LookupCategory lookupCategory;
}
