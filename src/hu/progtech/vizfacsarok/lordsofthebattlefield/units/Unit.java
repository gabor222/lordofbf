package hu.progtech.vizfacsarok.lordsofthebattlefield.units;

import java.util.ArrayList;
import hu.progtech.vizfacsarok.lordsofthebattlefield.Map;

public abstract class Unit {
    private int actionPoint;
    private int armor;
    private int health;
    private int maxHealth;
    private int movementRange;
    private int owner;
    private int[] position;

    public Unit(int armor,  int maxHealth, int movementRange, int owner, int[] position) {
        this.actionPoint = 0;
        this.armor = armor;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.movementRange = movementRange;
        this.owner = owner;
        this.position = position;
    }

    public ArrayList<int[]> canMoveTo(Map map) {
        int[][] around = new int[][]{ {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1} };
        ArrayList<int[]> resultList = new ArrayList<int[]>();
        ArrayList<int[]> from = new ArrayList<int[]>();
        ArrayList<int[]> to = new ArrayList<int[]>();
        from.add(position);
        for(int i = 0; i < movementRange; i++){

            for(int[] pos : from){
                for(int[] push : around){
                    int[] newPos = {pos[0] + push[0], pos[1] + push[1]};
                    if(newPos[0] >= 0 && newPos[0] < map.getSize() && newPos[1] >= 0 && newPos[1] < map.getSize() &&
                            map.getPossess(newPos[0], newPos[1]) == 0 && !contains(from,newPos[0],newPos[1]) &&
                            !contains(to,newPos[0],newPos[1]) && !contains(resultList,newPos[0],newPos[1])){
                        to.add(newPos);
                    }
                }
            }
            resultList.addAll(to);
            from.clear();
            from.addAll(to);
            to.clear();
        }
        return resultList;
    }

    public boolean contains(ArrayList<int[]> positions, int i, int j){
        boolean contains = false;
        for(int k = 0;k < positions.size();k++){
            if(positions.get(k)[0] == i && positions.get(k)[1] == j)contains = true;
        }
        return contains;
    }


    public void move(Map map, int x, int y){
        map.setUnit(x, y, this);
        map.setPossess(x, y, owner);
        map.setPossess(position[0], position[1], 0);
        actionPoint--;
        int[] newPos = {x,y};
        position = newPos;
    }

    public void die(Map map){
        map.setPossess(position[0], position[1], 0);
        map.setUnit(position[0], position[1], null);
    }

    public abstract int[] getStats();

    public int[] getActions(){
        int[] actions = { 0,0,0,0,0,0,0,0,0,0,0,1};
        return actions;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getMovementRange() {
        return movementRange;
    }

    public void setMovementRange(int movementRange) {
        this.movementRange = movementRange;
    }

    public int getOwner() {
        return owner;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public int getActionPoint() {
        return actionPoint;
    }

    public void setActionPoint(int actionPoint) {
        this.actionPoint = actionPoint;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }
}