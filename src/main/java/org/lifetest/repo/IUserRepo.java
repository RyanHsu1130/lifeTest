package org.lifetest.repo;

import org.lifetest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User, Long> {

  User getUserByUserName(String userName);

}
