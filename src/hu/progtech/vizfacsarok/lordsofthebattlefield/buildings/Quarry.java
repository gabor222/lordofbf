/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.progtech.vizfacsarok.lordsofthebattlefield.buildings;

import hu.progtech.vizfacsarok.lordsofthebattlefield.Player;

/**
 *
 * @author Fruzsina
 */
public class Quarry extends MatProdBuilding {
    
    private static final int[] BUILD_COST = new int[]{0, 10, 20, 25};
    
    public Quarry(int positionX, int positionY, int maxHealth, int owner, int matProdRate, int peasantNum) {
        super(positionX, positionY, maxHealth, owner, matProdRate, peasantNum);
    }
    
    public void addStone(Player player){
        player.setStone(player.getStone() + (this.getPeasantNum() * this.getMatProdRate()));
    }

    public static int[] getBUILD_COST() {
        return BUILD_COST;
    }
    
    
    
}
