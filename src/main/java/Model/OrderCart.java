package Model;

import lombok.*;

@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString
@Setter

public class OrderCart {
    private String itemcode;
    private String desc;
    private Integer qty;
    private Double unitPrice;
    private Double total;
    private String orderId;

    public OrderCart(String itemcode, String desc, int qty, double unitPrice, Double total) {
        this.itemcode=itemcode;
        this.desc=desc;
        this.qty=qty;
        this.unitPrice=unitPrice;
        this.total=total;
    }
}
