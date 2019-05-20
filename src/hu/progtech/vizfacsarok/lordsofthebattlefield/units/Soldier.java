package hu.progtech.vizfacsarok.lordsofthebattlefield.units;

public class Soldier extends Attacker {
    private static int[] productionCost = {0,0,0,0};
    private static int productionTime = 2;

    public Soldier(int owner, int[] position) {
        super(2, 10, 4, owner, position, 4, 4, 1);
    }

    @Override
    public int[] getStats() {
        int[] stats = { getMaxHealth(), getHealth(), getArmor(), getActionPoint(), getDamage(), getMinRange(),
                getMaxRange()};
        return stats;
    }

    public static int getProductionTime() {
        return productionTime;
    }

    public static void setProductionTime(int productionTime) {
        Soldier.productionTime = productionTime;
    }
    public static int[] getProductionCost() {
        return productionCost;
    }

    public static void setProductionCost(int[] productionCost) {
        Soldier.productionCost = productionCost;
    }
}
