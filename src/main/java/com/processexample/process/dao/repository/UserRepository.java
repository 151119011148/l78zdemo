package com.processexample.process.dao.repository;

import com.processexample.process.bean.DO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by  GF  in  16:03 2018/6/20
 * Description:
 * Modified  By:
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {


    public User save(User user);


}
