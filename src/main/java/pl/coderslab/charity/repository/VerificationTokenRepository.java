package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.entity.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByUser(User user);

    VerificationToken findByToken(String token);

}
