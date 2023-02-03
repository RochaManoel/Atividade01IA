package Entities;

public class Queue {
    public int indexStart = -1;
    public int indexEnd = -1;
    public int sizeMax = 32;
    public int sizeCurrent = 0;
    public Node group[] = new Node[sizeMax];

    public boolean isEmpty(){
        return (sizeCurrent == 0) ?  true : false;
    }

    public boolean isFull(){
        return (sizeCurrent == sizeMax - 1) ? true : false;
    }

    public void enqueue(Node node){
        if(!this.isFull()){
            if(indexStart == -1){
                indexStart = 0;
            }
            indexEnd++;
            group[indexEnd] = node;
            sizeCurrent++;
        }
    }

    public void dequeue(){
        if(!this.isEmpty()){
            indexStart++;
            sizeCurrent--;
        }
    }

    public Node getFirst(){
        return this.group[indexStart];
    }

    public boolean stateInQueue(int mStart, int cStart, int mEnd, int cEnd){
        for(int i = 0; i < sizeCurrent; i++){
            Node node = group[i];
            if((node.quantMissionariesStart == mStart) && (node.quantCannibalsStart == cStart) && (node.quantMissionariesEnd == mEnd) && (node.quantCannibalsEnd == cEnd)){
                return true;
            }
        }
        return false;
    }
}
