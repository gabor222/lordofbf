package hu.progtech.vizfacsarok.lordsofthebattlefield.buildings;

import hu.progtech.vizfacsarok.lordsofthebattlefield.Map;
import hu.progtech.vizfacsarok.lordsofthebattlefield.Player;

public abstract class UnProdBuilding extends Building {

    protected int roundcounter = 0;
    private static final int BUILD_TIME = 3;
    private int buildTimeLeft;

    public UnProdBuilding(int positionX, int positionY, int maxHealth, int owner) {
        super(positionX, positionY, maxHealth, owner);
        this.buildTimeLeft = BUILD_TIME;
    }

    public int getRoundcounter() {
        return roundcounter;
    }

    protected abstract void setRoundcounter(Player player);

    public static int getBUILD_TIME() {
        return BUILD_TIME;
    }

    public int getBuildTimeLeft() {
        return buildTimeLeft;
    }

    public void setBuildTimeLeft(int buildTimeLeft) {
        this.buildTimeLeft = buildTimeLeft;
    }
    
    
    
    

    protected abstract boolean releaseUnit(Map map);

}
