package hu.progtech.vizfacsarok.lordsofthebattlefield.units;

public class Peasant extends Unit {
    private static int[] productionCost = {0,0,0,0};
    private static int productionTime = 1;

    public Peasant(int owner, int[] position) {
        super(0, 5, 5, owner, position);
    }

    public void build(){

    }

    @Override
    public int[] getStats() {
        int[] stats = { getMaxHealth(), getHealth(), getArmor(), getActionPoint()};
        return stats;
    }

    public int[] getActions(){
        int[] actions = { 2,3,4,5,6,7,8,9,10,11,12,1};
        return actions;
    }

    public static int getProductionTime() {
        return productionTime;
    }

    public static void setProductionTime(int productionTime) {
        Peasant.productionTime = productionTime;
    }

    public static int[] getProductionCost() {
        return productionCost;
    }

    public static void setProductionCost(int[] productionCost) {
        Peasant.productionCost = productionCost;
    }
}
