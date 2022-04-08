package com.models;

import java.util.List;
import com.enums.OrderStatus;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class RoomService {
    private HashMap<MenuItem, OrderStatus> orders;

    public Menu menu;
    public RoomService(HashMap<MenuItem, OrderStatus> orders){
        this.orders = orders;
    };

    
}
