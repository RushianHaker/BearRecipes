package com.service.bearrecipes.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.service.bearrecipes.utils.Utils;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigDecimal;
import java.util.Date;

import static com.service.bearrecipes.utils.Utils.parseDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long id;

    @Column(name = "order_number", nullable = false)
    private String number;

    @Column(name = "order_price", nullable = false)
    private BigDecimal orderPrice;

    @Column(name = "delivery_address", nullable = false)
    private String deliveryAddress;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @Fetch(FetchMode.SELECT)
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Fetch(FetchMode.SELECT)
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Order(String number, BigDecimal orderPrice, String deliveryAddress, Date orderDate, Stock stock, User user) {
        this.number = number;
        this.orderPrice = orderPrice;
        this.deliveryAddress = deliveryAddress;
        this.orderDate = orderDate;
        this.stock = stock;
        this.user = user;
    }

    @JsonGetter("order_date")
    @JsonFormat(pattern = Utils.DATE_FORMAT, timezone = Utils.TIME_ZONE_GMT3)
    @Nullable
    public Date getOrderDate() {
        return orderDate;
    }

    @JsonSetter("order_date")
    public void orderDateFromJson(@Nullable String orderDate) {
        this.orderDate = parseDate(orderDate);
    }
}
