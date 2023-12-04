package com.service.bearrecipes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "steps_of_receipt")
public class StepInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long id;

    @Column(name = "step", nullable = false)
    private String step;

    @Column(name = "step_image")
    private byte[] image;

    @ManyToOne(targetEntity = Receipt.class, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;

    public StepInfo(String step, byte[] image, Receipt receipt) {
        this.step = step;
        this.image = image;
        this.receipt = receipt;
    }
}
