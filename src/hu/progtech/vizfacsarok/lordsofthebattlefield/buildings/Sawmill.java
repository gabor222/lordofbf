
package hu.progtech.vizfacsarok.lordsofthebattlefield.buildings;

import hu.progtech.vizfacsarok.lordsofthebattlefield.Player;

/**
 *
 * @author Fruzsina
 */
public class Sawmill extends MatProdBuilding {
    
    private static final int[] BUILD_COST = new int[]{0, 20, 5, 15};
    
    public Sawmill(int positionX, int positionY, int maxHealth, int owner, int matProdRate, int peasantNum) {
        super(positionX, positionY, maxHealth, owner, matProdRate, peasantNum);
    }
    
    public void addWood(Player player){
        player.setWood(player.getWood() + (this.getPeasantNum() * this.getMatProdRate()));
    }
    
    public static int[] getBUILD_COST() {
        return BUILD_COST;
    }
    
}
