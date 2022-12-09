package com.scofen.designpattern.observer.demo1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 使用线程进行监控老板类，查看老板是否发出加班通知，如果发出就通知所有程序员加班
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GrilFriend extends Thread{

	private Boss boss;

	private Programmer programmer;

	@Override
	public void run(){
		while (true){
			if(this.boss.getIsNotifyProgrammer()){
				this.programmer.workOvertime();
			}
		}
	}
}

