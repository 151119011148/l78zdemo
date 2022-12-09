package com.scofen.designpattern.observer.demo1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Boss {

	/**
	 * 用来定义今晚是否加班
	 */
	private boolean isNotifyProgrammer = false;

	public boolean getIsNotifyProgrammer() {
		return isNotifyProgrammer;
	}

	/**
	 * 通知所有程序员加班
	 */
	public void notifyProgrammer() {
		System.out.println("所有程序员今晚加班");
		this.isNotifyProgrammer = true;
	}
}

