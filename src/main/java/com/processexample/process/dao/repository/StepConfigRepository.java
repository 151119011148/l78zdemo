package com.processexample.process.dao.repository;

import com.processexample.process.bean.DO.ProcessStepConfig;
import com.processexample.process.bean.DO.StepConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Create by  GF  in  23:31 2018/6/7
 * Description:
 * Modified  By:
 */
@Repository
public interface StepConfigRepository extends JpaRepository<StepConfig,Integer> {

    StepConfig findByStepId(Integer stepId);
}
