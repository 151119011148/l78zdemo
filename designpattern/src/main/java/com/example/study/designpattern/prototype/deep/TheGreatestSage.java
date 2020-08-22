package com.scofen.study.designpattern.prototype.deep;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.Date;

/**
 * Create by  GF  in  20:03 2018/8/14
 * Description:齐天大圣
 * Modified  By:
 */

public class TheGreatestSage extends Monkey implements Cloneable , Serializable{

    /**
     * 金箍棒
     */
    @Getter
    @Setter
    private GoldRingedStaff goldRingedStaff;

    /**
     * 石头缝里蹦出来
     */
    public TheGreatestSage(){
        this.goldRingedStaff = new GoldRingedStaff();
        this.birthday = new Date();
        this.height = 150;
        this.weight = 30;
        this.transientValue = 10000;
        System.out.println("克隆走不走构造方法呢");
    }
    /**
     * 分身技能
     */
    public Object clone(){

        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;

        try {
            //not good idea
            //return super.clone();

            //序列化
             bos = new ByteArrayOutputStream();
             oos = new ObjectOutputStream(bos);
             oos.writeObject(this);

            //反序列化
             bis = new ByteArrayInputStream(bos.toByteArray());
             ois = new ObjectInputStream(bis);
            TheGreatestSage copy = (TheGreatestSage) ois.readObject();
            copy.birthday = new Date();
            return copy;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                bos.close();
                oos.close();
                bis.close();
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 变化  深克隆
     */
    public void change(){
        TheGreatestSage copySage = (TheGreatestSage) clone();
        System.out.println("大圣本尊生日：" + this.getBirthday().getTime());
        System.out.println("克隆大圣本尊生日：" + copySage.getBirthday().getTime());
        System.out.println("本尊和克隆是否同一个对象：" + (this == copySage));
        System.out.println("本尊棒子和克隆棒子是否同一个对象:" + (this.getGoldRingedStaff() == copySage.getGoldRingedStaff()));
        System.out.println("transient关键字测试" + copySage.transientValue);
    }



}
