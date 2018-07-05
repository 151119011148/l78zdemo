package com.processexample.process.dao;

import com.alibaba.fastjson.JSON;
import com.processexample.process.bean.DO.ProcessConfig;
import com.processexample.process.bean.DTO.ProcessConfigDTO;
import com.processexample.process.bean.DTO.ProcessStepConfigDTO;
import com.processexample.process.bean.DTO.StepConfigDTO;
import com.processexample.process.bean.InterfaceDTO.StepInterfaceDTO;
import com.processexample.process.dao.impl.ProcessDaoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessConfigDaoTest {

    @Autowired
    private ProcessDaoImpl dao;

    @Test
    public void listProcessBySystemId() throws Exception {
        List<ProcessConfigDTO> list = dao.listProcessBySystemId("201");
        System.out.println(JSON.toJSON(list));
    }
    @Test
    public void listProcessStepConfigByProcessId() throws Exception {
        List<ProcessStepConfigDTO> list = dao.listProcessStepConfigByProcessId("1");
        System.out.println(JSON.toJSON(list));
    }
    @Test
    public void getStepById() throws Exception {
        StepInterfaceDTO stepDTO = dao.getStepById("1");
        System.out.println(JSON.toJSON(stepDTO));
    }

}