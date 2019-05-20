package hu.progtech.vizfacsarok.lordsofthebattlefield.buildings;

import hu.progtech.vizfacsarok.lordsofthebattlefield.Player;

public class Farm extends MatProdBuilding {
    public Farm(int[] buildCost, int id, int positionX, int positionY, int maxHealth, int owner, int matProdRate, int peasantNum) {
        super(buildCost, 1, id, positionX, positionY, maxHealth, owner, matProdRate, peasantNum);
    }

    public void addFood(Player player){
        player.setFood(player.getFood() + (this.getPeasantNum() * this.getMatProdRate()));
    }



}
