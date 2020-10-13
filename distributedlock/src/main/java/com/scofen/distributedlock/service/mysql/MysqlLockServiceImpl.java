package com.scofen.distributedlock.service.mysql;


import com.scofen.distributedlock.bean.MysqlLock;
import com.scofen.distributedlock.dao.MysqlLockRepository;
import com.scofen.distributedlock.service.AbstractDistributedLockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Create by  GF  in  21:58 2019/1/27
 * Description:
 * Modified  By:
 */
@Service("mysqlLockService")
public class MysqlLockServiceImpl extends AbstractDistributedLockService {

    @Resource
    private MysqlLockRepository mysqlLockRepository;

    //非阻塞式加锁，向数据库写入数据，落表成功代表加锁成功
    public boolean tryLock(String key) {
        try {
            mysqlLockRepository.save(new MysqlLock(key));
        } catch (Exception e) {
            return false;
            //e.printStackTrace();
        }
        return true;
    }



    @Override
    public boolean releaseLock(String key) {
        mysqlLockRepository.removeByLockId(key);
        return true;
    }
}
