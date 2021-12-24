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
public class OrderDetail {
    private int id;
    private int oderId;
    private int productId;
    private String productName;
    private Double productPrice;
    private int quantity;
}
