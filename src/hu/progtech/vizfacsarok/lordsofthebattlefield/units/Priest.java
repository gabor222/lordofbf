package hu.progtech.vizfacsarok.lordsofthebattlefield.units;

import hu.progtech.vizfacsarok.lordsofthebattlefield.Map;

import java.util.ArrayList;

public class Priest extends Attacker {
    private static int[] productionCost = {0,0,0,0};
    private static int productionTime = 3;
    private int healAmount;

    public Priest(int owner, int[] position) {
        super(0, 8, 3, owner, position,3, 4, 2);
        healAmount = 3;
    }

    public ArrayList<int[]> canHeal(Map map){
        ArrayList<int[]> resultList = new ArrayList<int[]>();
        int owner = this.getOwner();
        int[] position = this.getPosition();
        for(int i = position[0] - 3; i < position[0] + 3 + 1; i++){
            for (int j = position[1] - 3; j < position[1] + 3 + 1; j++) {
                if(i >= 0 && j >= 0 && i < map.getSize() && j < map.getSize() &&
                        map.getPossess(i, j) == owner){
                    int[] friendlyPos = {i, j};
                    resultList.add(friendlyPos);
                }
            }
        }
        return resultList;
    }

    public void heal(Unit unit){
        setActionPoint(getActionPoint()-1);
        unit.setHealth(unit.getHealth()+healAmount);
    }

    public int getHealAmount() {
        return healAmount;
    }

    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }

    public static int[] getProductionCost() {
        return productionCost;
    }

    public static void setProductionCost(int[] productionCost) {
        Priest.productionCost = productionCost;
    }

    public static int getProductionTime() {
        return productionTime;
    }

    public static void setProductionTime(int productionTime) {
        Priest.productionTime = productionTime;
    }

    @Override
    public int[] getStats() {
        int[] stats = { getMaxHealth(), getHealth(), getArmor(), getActionPoint(), getDamage(), getHealAmount(),
                getMinRange(), getMaxRange(), 3/*healRange*/};
        return stats;
    }
}
