package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    private String orderId;
    private String itemCode;
    private Integer qty;
    private Double unitPrice;
}
