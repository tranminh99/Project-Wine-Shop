/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
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

public class Cart implements Serializable{
    private String productName,productImg;
    private double prodictPrice;
    private int productQuantity;
    private int Quantity,productId;
    private double totalPrice;
}
