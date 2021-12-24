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
public class Images {
    private int id;
    private int productId;
    private String imageName;
    private boolean cover;
}
