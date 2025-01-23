package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor

public class Login {
    private Integer id;
    private String userName;
    private String email;
    private String password;

    public Login(String userName, String password) {
        this.userName=userName;
        this.password=password;
    }
    public Login(String userName, String email,String password) {
        this.userName=userName;
        this.password=password;
        this.email=email;
    }


}
