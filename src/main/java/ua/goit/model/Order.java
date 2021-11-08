package ua.goit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order  {

    private Integer id;
    private Integer petId;
    private Integer quantity;
    private String shipDate;
    private OrderStatus status;
    private boolean complete = false;

}
