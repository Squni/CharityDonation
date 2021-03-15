package pl.coderslab.charity.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Set;

@Data
public class CurrentUser extends User {
    private final pl.coderslab.charity.entity.User user;
    public CurrentUser(String email, String password,
                       Set<GrantedAuthority> authorities,
                       pl.coderslab.charity.entity.User user) {
        super(email, password, authorities);
        this.user = user;
    }
    public pl.coderslab.charity.entity.User getUser() {return user;}


}
