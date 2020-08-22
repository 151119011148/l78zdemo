package com.scofen.study.designpattern.chainOfResponsibility.handler;

/**
 * Create by  GF  in  17:00 2018/11/21
 * Description:
 * Modified  By:
 */
public class PriceHandlerFactory {


    public static PriceHandler createPriceHandler() {
        PriceHandler sales = new Sales();
        PriceHandler manager = new Manager();
        PriceHandler director = new Director();
        PriceHandler vicePresident = new VicePresident();
        PriceHandler ceo = new CEO();

        sales.setSuccessor(manager);
        manager.setSuccessor(director);
        director.setSuccessor(vicePresident);
        vicePresident.setSuccessor(ceo);
        return sales;


    }
}
