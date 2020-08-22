package com.example.distributedlock.dao;



import com.example.distributedlock.bean.MysqlLock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MysqlLockRepository extends JpaRepository<MysqlLock,String> {

     MysqlLock findByLockId(Integer lockId);

     MysqlLock save(MysqlLock mysqlLock);

     @Transactional
     void removeByLockId(String lockId);


}
