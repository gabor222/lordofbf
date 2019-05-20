package hu.progtech.vizfacsarok.lordsofthebattlefield.buildings;

import hu.progtech.vizfacsarok.lordsofthebattlefield.Map;

public abstract class UnProdBuilding extends Building {

    private int roundcounter = -1;

    public UnProdBuilding(int[] buildCost, int size, int id, int positionX, int positionY, int maxHealth, int owner) {
        super(buildCost, size, id, positionX, positionY, maxHealth, owner);
    }

    public int getRoundcounter() {
        return roundcounter;
    }

    public void setRoundcounter(int roundcounter) {
        this.roundcounter = roundcounter;
    }

    protected abstract void releaseUnit(Map map);

}
