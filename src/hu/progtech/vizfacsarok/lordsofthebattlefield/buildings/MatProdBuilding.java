package hu.progtech.vizfacsarok.lordsofthebattlefield.buildings;

public abstract class MatProdBuilding extends Building {

    private int matProdRate;
    private int peasantNum;


    public MatProdBuilding(int[] buildCost, int size, int id, int positionX, int positionY, int maxHealth, int owner, int matProdRate, int peasantNum) {
        super(buildCost, size, id, positionX, positionY, maxHealth, owner);
        this.matProdRate = matProdRate;
        this.peasantNum = peasantNum;
    }

    public int getMatProdRate() {
        return matProdRate;
    }

    public int getPeasantNum() {
        return peasantNum;
    }

    private void setMatProdRate(int matProdRate) {
        this.matProdRate = matProdRate;
    }

    public void setPeasantNum(int peasantNum) {
        this.peasantNum = peasantNum;
    }

    public void incProdRate(int amount) {
        setMatProdRate(getMatProdRate() + amount);
    }

    @Override
    public int[] getStats() {
        int[] stats = new int[4];
        stats[0] = this.getCurrentHealth();
        stats[1] = this.getMaxHealth();
        stats[2] = this.getMatProdRate();
        stats[3] = this.getPeasantNum();
        return stats;
    }
}
