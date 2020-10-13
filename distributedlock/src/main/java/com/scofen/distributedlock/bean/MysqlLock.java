package com.scofen.distributedlock.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Create by  GF  in  13:00 2019/1/27
 * Description:
 * Modified  By:
 */
@Entity
@Table(name="demo_distributed_mysql_lock")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MysqlLock {

        @Id
        private String lockId;

}
