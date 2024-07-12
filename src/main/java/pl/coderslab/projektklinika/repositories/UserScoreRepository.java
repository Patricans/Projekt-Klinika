package pl.coderslab.projektklinika.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.coderslab.projektklinika.models.User;
import pl.coderslab.projektklinika.models.UserScore;

public interface UserScoreRepository extends CrudRepository<UserScore, Integer> {
    @Query("SELECT AVG(us.score) FROM UserScore us WHERE us.user = :user")
    Float getAverageScore(User user);
    @Query("SELECT us FROM UserScore us WHERE us.issuer = :issuer AND us.user = :user")
    UserScore getUserScore(User issuer, User user);
}

