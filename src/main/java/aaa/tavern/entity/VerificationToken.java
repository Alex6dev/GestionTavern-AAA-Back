package aaa.tavern.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class VerificationToken {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String token;
  
    @OneToOne(targetEntity = Player.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "player_id")
    private Player player;
    
    private Date expiryDate;
       
    protected VerificationToken() {
    }

    public Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public static int getExpiration() {
        return EXPIRATION;
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public Player getPlayer() {
        return player;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }
    
}
