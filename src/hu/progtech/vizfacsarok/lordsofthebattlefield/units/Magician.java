package hu.progtech.vizfacsarok.lordsofthebattlefield.units;

import hu.progtech.vizfacsarok.lordsofthebattlefield.Map;

import java.util.ArrayList;

public class Magician extends Attacker {
    private static int[] productionCost = {0,0,0,0};
    private static int productionTime = 4;

    public Magician(int owner, int[] position) {
        super(0, 7, 4, owner, position, 5, 5, 2);
    }

    public ArrayList<int[]> canCastEnemy(Map map){
        ArrayList<int[]> resultList = new ArrayList<int[]>();
        int owner = this.getOwner();
        int enemy = 2;
        if(owner == 2){
            enemy = 1;
        }
        int[] position = this.getPosition();
        for(int i = position[0] - 5; i < position[0] + 5 + 1; i++){
            for (int j = position[1] - 5; j < position[1] + 5 + 1; j++) {
                if(i >= 0 && j >= 0 && i < map.getSize() && j < map.getSize() &&
                        map.getPossess(i, j) == enemy){
                    int[] enemyPos = {i, j};
                    resultList.add(enemyPos);
                }
            }
        }
        return resultList;
    }

    public void disarm(Attacker target){
        target.setAttacked(true);
        setAttacked(true);
        setActionPoint(getActionPoint()-1);
    }

    public ArrayList<int[]> canMakeDesert(Map map){
        ArrayList<int[]> resultList = new ArrayList<int[]>();
        int[] position = this.getPosition();
        for(int i = position[0] - 5; i < position[0] + 5 + 1; i++){
            for (int j = position[1] - 5; j < position[1] + 5 + 1; j++) {
                if(i >= 0 && j >= 0 && i < map.getSize() && j < map.getSize() &&
                        map.getPossess(i, j) == 0 && map.getTerrain(i,j) == 0){
                    int[] enemyPos = {i, j};
                    resultList.add(enemyPos);
                }
            }
        }
        return resultList;
    }

    public void makeDesert(Map map, int x, int y){
        setAttacked(true);
        setActionPoint(getActionPoint()-1);
        map.setPossess(x,y, 3);
        map.setTerrain(x,y, 4);
    }

    public ArrayList<int[]> canReverseDesert(Map map){
        ArrayList<int[]> resultList = new ArrayList<int[]>();
        int[] position = this.getPosition();
        for(int i = position[0] - 5; i < position[0] + 5 + 1; i++){
            for (int j = position[1] - 5; j < position[1] + 5 + 1; j++) {
                if(i >= 0 && j >= 0 && i < map.getSize() && j < map.getSize() &&
                        map.getPossess(i, j) == 3 && map.getTerrain(i,j) == 4){
                    int[] enemyPos = {i, j};
                    resultList.add(enemyPos);
                }
            }
        }
        return resultList;
    }

    public void reverseDesert(Map map, int x, int y){
        setAttacked(true);
        setActionPoint(getActionPoint()-1);
        map.setPossess(x,y, 0);
        map.setTerrain(x,y, 0);
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
        Magician.productionTime = productionTime;
    }

    public static int[] getProductionCost() {
        return productionCost;
    }

    public static void setProductionCost(int[] productionCost) {
        Magician.productionCost = productionCost;
    }
}
