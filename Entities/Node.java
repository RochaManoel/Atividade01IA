package Entities;

public class Node {
    public int id;
    public int level;
    public int quantMissionariesStart;
    public int quantCannibalsStart;
    public int quantMissionariesEnd;
    public int quantCannibalsEnd;

    public Node(Integer id, Integer level,Integer  quantMissionariesStart,Integer  quantCannibalsStart,Integer  quantMissionariesEnd,Integer  quantCannibalsEnd){
        this.id  = id;
        this.level = level;
        this.quantMissionariesStart = quantMissionariesStart;
        this.quantCannibalsStart = quantCannibalsStart;
        this.quantMissionariesEnd = quantMissionariesEnd;
        this.quantCannibalsEnd = quantCannibalsEnd;
    }

    public boolean validationStateEnd(){
        return (quantMissionariesStart == 0 && quantCannibalsStart == 0 && quantMissionariesEnd == 3 && quantCannibalsEnd == 3) ? true : false;
    }

}
