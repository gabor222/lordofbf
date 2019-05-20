package hu.progtech.vizfacsarok.lordsofthebattlefield.buildings;

import hu.progtech.vizfacsarok.lordsofthebattlefield.Map;
import hu.progtech.vizfacsarok.lordsofthebattlefield.units.Peasant;
import hu.progtech.vizfacsarok.lordsofthebattlefield.units.Unit;

public abstract class MatProdBuilding extends Building {

    private int matProdRate;
    private int peasantNum;
    private static final int BUILD_TIME = 1;
    private int buildTimeLeft;


    public MatProdBuilding(int positionX, int positionY, int maxHealth, int owner, int matProdRate, int peasantNum) {
        super(positionX, positionY, maxHealth, owner);
        this.matProdRate = matProdRate;
        this.peasantNum = peasantNum;
        this.buildTimeLeft = BUILD_TIME;
    }

    public int getMatProdRate() {
        return matProdRate;
    }

    public int getPeasantNum() {
        return peasantNum;
    }

    public int getBuildTimeLeft() {
        return buildTimeLeft;
    }

    private void setMatProdRate(int matProdRate) {
        this.matProdRate = matProdRate;
    }

    public void setPeasantNum(int peasantNum) {
        this.peasantNum = peasantNum;
    }

    public void setBuildTimeLeft(int buildTimeLeft) {
        this.buildTimeLeft = buildTimeLeft;
    }

    public void incProdRate(int amount) {
        setMatProdRate(getMatProdRate() + amount);
    }

    @Override
    public int[] getStats() {
        int[] stats = new int[18];
        if(this.getBuildTimeLeft() > 0){
            stats[0] = 0;
        } else{
            stats[0] = 9; //remove peasant
        }
        for(int i = 1; i < 11; ++i){
            stats[i] = 0; //nincs tobb parancs
        }
        stats[11] = 1;//demolish
        stats[12] = this.getCurrentHealth();
        stats[13] = this.getMaxHealth();
        stats[14] = -1;
        stats[15] = -1;
        stats[16] = this.getMatProdRate();//körönkénti termelékenység
        stats[17] = this.getPeasantNum(); //hány paraszt van bent
        return stats;
    }

    public static int getBUILD_TIME() {
        return BUILD_TIME;
    }
    
    public void removePeasant(Map map){
        if(this.getPositionY() - 1 >= 0 && map.getPossess(this.getPositionX(), this.getPositionY() - 1) == 0){
            if(this.getPeasantNum() > 0){
                this.setPeasantNum(this.getPeasantNum() - 1);
                int[] position = {this.getPositionX(), this.getPositionY() - 1};
                Unit peasant = new Peasant(this.getOwner(), position);
                map.setUnit(this.getPositionX(), this.getPositionY() -1, peasant);
                map.setPossess(this.getPositionX(), this.getPositionY() -1, 1);
            }
        }
    }
}
