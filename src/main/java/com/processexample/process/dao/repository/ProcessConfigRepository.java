package com.processexample.process.dao.repository;

import com.processexample.process.bean.DO.ProcessConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ProcessConfigRepository extends JpaRepository<ProcessConfig,String> {

    List<ProcessConfig> findBySystemId(String systemId);

}
