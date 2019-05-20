package hu.progtech.vizfacsarok.lordsofthebattlefield.units;

public class Archer extends Attacker {
    private static int[] productionCost = {0,0,0,0};
    private static int productionTime = 2;

    public Archer(int owner, int[] position) {
        super(1, 8, 5, owner, position, 5, 6, 2);
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
        Archer.productionTime = productionTime;
    }

    public static int[] getProductionCost() {
        return productionCost;
    }

    public static void setProductionCost(int[] productionCost) {
        Archer.productionCost = productionCost;
    }
}
