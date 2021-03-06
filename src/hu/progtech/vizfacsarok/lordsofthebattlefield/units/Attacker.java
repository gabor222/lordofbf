package hu.progtech.vizfacsarok.lordsofthebattlefield.units;

import hu.progtech.vizfacsarok.lordsofthebattlefield.Map;
import hu.progtech.vizfacsarok.lordsofthebattlefield.buildings.Building;

import java.util.ArrayList;

public abstract class Attacker extends Unit{
    private int damage;
    private int maxRange;
    private int minRange;
    private boolean attacked;

    public Attacker(int armor, int maxHealth, int movementRange, int owner, int[] position,  int damage, int maxRange, int minRange) {
        super(armor, maxHealth, movementRange, owner, position);
        this.damage = damage;
        this.maxRange = maxRange;
        this.minRange = minRange;
        attacked = false;
    }

    public ArrayList<int[]> canAttack(Map map){
        ArrayList<int[]> resultList = new ArrayList<int[]>();
        int owner = this.getOwner();
        int enemy = 2;
        if(owner == 2){
            enemy = 1;
        }
        int[] position = this.getPosition();

        for(int i = position[0] - maxRange; i < position[0] + maxRange + 1; i++){
            for (int j = position[1] - maxRange; j < position[1] + maxRange + 1; j++) {
                if(i >= 0 && j >= 0 && i < map.getSize() && j < map.getSize() &&
                        map.getPossess(i, j) == enemy){
                    int[] enemyPos = {i, j};
                    resultList.add(enemyPos);
                }
            }
        }
        return resultList;
    }

    public void attack(Map map, Unit enemy){
        attacked = true;
        setActionPoint(getActionPoint()-1);
        int hit = damage - enemy.getArmor();
        if(hit < 0) { hit = 0;}
        enemy.setHealth(enemy.getHealth()-hit);
        if(enemy.getHealth() <= 0){
            enemy.die(map);
        }
    }

    public void attack(Map map, Building enemy){
        attacked = true;
        setActionPoint(getActionPoint()-1);
        enemy.setCurrentHealth(enemy.getCurrentHealth()-damage);
        if(enemy.getCurrentHealth() <=0){
            enemy.demolish(map);
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(int maxRange) {
        this.maxRange = maxRange;
    }

    public int getMinRange() {
        return minRange;
    }

    public void setMinRange(int minRange) {
        this.minRange = minRange;
    }

    public boolean isAttacked() {
        return attacked;
    }

    public void setAttacked(boolean attacked) {
        this.attacked = attacked;
    }
}
