import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Player")
public class PlayerEntidad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Player")
    private int ID_Player;
    @Column(name = "Nick")
    private String Nick;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;

    public PlayerEntidad(){

    }

    public PlayerEntidad(String nick, String password, String email) {
        Nick = nick;
        this.password = password;
        this.email = email;
    }

    public int getID_Player() {
        return ID_Player;
    }

    public void setID_Player(int ID_Player) {
        this.ID_Player = ID_Player;
    }

    public String getNick() {
        return Nick;
    }

    public void setNick(String nick) {
        Nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
