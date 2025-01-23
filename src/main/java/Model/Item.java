package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Item {
    private String code;
    private String description;
    private Double unitPrice;
    private Integer qtyOnHand;
}
