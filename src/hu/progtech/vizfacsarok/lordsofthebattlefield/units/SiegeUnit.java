package hu.progtech.vizfacsarok.lordsofthebattlefield.units;

import hu.progtech.vizfacsarok.lordsofthebattlefield.Map;
import hu.progtech.vizfacsarok.lordsofthebattlefield.buildings.Building;

public class SiegeUnit extends Attacker{
    private static int[] productionCost = {0,0,0,0};
    private static int productionTime = 4;

    public SiegeUnit(int owner, int[] position) {
        super(2, 10, 6, owner, position, 6, 6, 3);
    }

    @Override
    public int[] getStats() {
        int[] stats = { getMaxHealth(), getHealth(), getArmor(), getActionPoint(), getDamage(), getMinRange(),
                getMaxRange()};
        return stats;
    }

    @Override
    public void attack(Map map, Building enemy){
        setAttacked(true);
        setActionPoint(getActionPoint()-1);
        enemy.setCurrentHealth(enemy.getCurrentHealth()-(getDamage()+3));
        if(enemy.getCurrentHealth() <=0){
            enemy.demolish(map);
        }
    }

    public static int getProductionTime() {
        return productionTime;
    }

    public static void setProductionTime(int productionTime) {
        SiegeUnit.productionTime = productionTime;
    }

    public static int[] getProductionCost() {
        return productionCost;
    }

    public static void setProductionCost(int[] productionCost) {
        SiegeUnit.productionCost = productionCost;
    }
}
