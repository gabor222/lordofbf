package hu.progtech.vizfacsarok.lordsofthebattlefield.buildings;

import hu.progtech.vizfacsarok.lordsofthebattlefield.Player;

public class Farm extends MatProdBuilding {
    
    private static final int[] BUILD_COST = new int[]{0, 15, 15, 10};
    
    public Farm(int positionX, int positionY, int maxHealth, int owner, int matProdRate, int peasantNum) {
        super(positionX, positionY, maxHealth, owner, matProdRate, peasantNum);
    }

    public void addFood(Player player){
        player.setFood(player.getFood() + (this.getPeasantNum() * this.getMatProdRate()));
    }
    
    public static int[] getBUILD_COST() {
        return BUILD_COST;
    }

}
