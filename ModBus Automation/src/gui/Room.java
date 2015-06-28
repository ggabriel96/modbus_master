package gui;
import java.util.*;

class Room{
    protected ArrayList<DataStruct> data;
    public String roomName;

    void openInfoWindow(){
        new InfoWindow(this);
    }
    //Info and name have the same size, because the info[i] is associated to a name[i]

    public Room() {
    	this.data = new ArrayList<DataStruct>();
    }

    public Room(String name) {
        this();
        this.roomName = name;
    }
    
    public void setName(String name) {
    	this.roomName = name;
    }

    public int getDataSize(){
        return data.size();
    }

    public int getInfo(int index){
        if(index >= 0 && index < this.getDataSize())
            return data.get(index).info;

        return -1;
    }

    public String getName(int index){
        if(index >= 0 && index < this.getDataSize())
            return data.get(index).name;

        return null;
    }

    public DataStruct getInfo(String name){
        for(int i = 0; i < this.getDataSize(); i++)
            if(data.get(i).name.contains(name)) return data.get(i);

        return null;
    }

    public void setInfo(int index, int value){
        if(index >= 0 && index < this.getDataSize()){
            DataStruct tmp = data.get(index);
            tmp.info = value;
            data.set(index, tmp);
        }
    }

    public void setInfo(String name, int value){
        for(int i = 0; i < getDataSize(); i++){
            if((data.get(i).name).equals(name)){
                this.setInfo(i, value);
                return;
            }
        }
    }
    
    public void newLamp(int num, int reg, int bit) {
        DataStruct tmp = new DataStruct(0, reg, bit, "Lampada " + num);
        data.add(tmp);
    }
    
    public void newAlarm(int reg, int bit){
        DataStruct tmp = new DataStruct(0, reg, bit, "Sensor de Alarme");
        data.add(tmp);
    }

    public void newTemperature(int reg, String s) {
        DataStruct tmp = new DataStruct(28, reg, 0, "Temperatura " + s);
        data.add(tmp);
    }

    public void newBath(int reg) {
    	DataStruct tmp = new DataStruct(0, reg, 0, "Nivel da agua da banheira");
    	data.add(tmp);
    }

    public void newWaterIO(int reg, int bit, String s) {
        DataStruct tmp = new DataStruct(0, reg, bit, "Agua " + s);
        data.add(tmp);
    }

}
