package com.processexample.process.dao.repository;

import com.processexample.process.bean.DO.ProcessStepConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Create by  GF  in  23:22 2018/6/7
 * Description:
 * Modified  By:
 */
@Repository
public interface ProcessStepConfigRepository extends JpaRepository<ProcessStepConfig,Integer> {

    List<ProcessStepConfig> findProcessStepConfigsByProcessId(Integer processId);
}
