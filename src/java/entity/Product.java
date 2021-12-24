/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author dell
 */
@Builder
@Getter
@Setter
@ToString
public class Product {
    private int id;
    private int cateid;
    private String name;
    private double price;
    private int quantity;
    private String size;
    private String color;
    private String descripton;
    private int status;
    
    public String getdisplayStatus(){
        return status == 1 ? "Con hang" : status == 2 ? "Giam Gia" :"Het Hang";
    }

}
