package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.bo.custom.impl.ManageCustomerBOImpl;
import com.example.layeredarchitecture.bo.custom.impl.ManageItemBOImpl;
import com.example.layeredarchitecture.bo.custom.impl.PlaceOrderBOImpl;

public class BOFactory {
    public static BOFactory bofactory;

    public BOFactory(){

    }

    public static BOFactory getboFactory(){
        return (bofactory == null) ? bofactory = new BOFactory():bofactory;
    }

    public enum BOTypes{
        CUSTOMER, ITEM, PLACE_ORDER;
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return new ManageCustomerBOImpl();
            case ITEM:
                return new ManageItemBOImpl();
            case PLACE_ORDER:
                return new PlaceOrderBOImpl();
            default:
                return null;
        }
    }
}
