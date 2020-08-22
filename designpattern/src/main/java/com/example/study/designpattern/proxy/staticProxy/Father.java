package com.scofen.study.designpattern.proxy.staticProxy;

import lombok.Data;
import org.junit.Test;

/**
 * Create by  GF  in  15:13 2018/11/14
 * Description:
 * Modified  By:
 */
@Data
public class Father {

    private Son son;

    private void findLove() {

        System.out.println("根据你的要求物色女票~");
        this.son.findLove();
        System.out.println("双方父母是否同意");

    }
    @Test
    public void testStaticProxy(){
        Father father = new Father();
        Son son = new Son();
        father.setSon(son);
        father.findLove();
    }

}
