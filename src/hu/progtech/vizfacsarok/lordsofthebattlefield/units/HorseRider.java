package hu.progtech.vizfacsarok.lordsofthebattlefield.units;

public class HorseRider extends Attacker {
    private static int[] productionCost = {0,0,0,0};
    private static int productionTime = 4;

    public HorseRider(int owner, int[] position) {
        super(2, 12, 7, owner, position, 4, 4, 1);
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
        HorseRider.productionTime = productionTime;
    }

    public static int[] getProductionCost() {
        return productionCost;
    }

    public static void setProductionCost(int[] productionCost) {
        HorseRider.productionCost = productionCost;
    }
}
