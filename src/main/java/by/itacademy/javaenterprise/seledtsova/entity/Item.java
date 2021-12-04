package by.itacademy.javaenterprise.seledtsova.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;

}
