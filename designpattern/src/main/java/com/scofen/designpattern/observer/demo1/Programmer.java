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
public class Programmer {

	private String name;

	/**
	 * 接到加班通知，然后加班
	 */
	public void workOvertime() {
		System.out.println(name+"要加班");
	}
}

