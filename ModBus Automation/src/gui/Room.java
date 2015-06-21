package gui;
import java.util.*;

class Room{
    protected ArrayList<DataStruct> data;

    public String roomName;

    void openInfoWindow(){
        new InfoWindow(this);
    }
    //Info and name have the same size, because the info[i] is associated to a name[i]


    public Room(){
        data = new ArrayList<DataStruct>();
    }


    public int getDataSize(){
        return data.size();
    }

    public int getInfo(int index){
        if(index >= 0 && index < this.getDataSize())
            return data.get(index).info;

        return -1; //Mudar para Exception... algum dia :P
    }

    public String getName(int index){
        if(index >= 0 && index < this.getDataSize())
            return data.get(index).name;

        return null; //Mudar para Exception... algum dia :P
    }

    public DataStruct getInfo(String name){
        for(int i = 0; i < this.getDataSize(); i++)
            if((data.get(i).name).equals(name)) return data.get(i);

        return null; //Mudar para Exception... algum dia :P
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

    public void newLamp(int num, int reg, int bit){
        DataStruct tmp = new DataStruct(0, reg, bit, "Lampada" + num);
        data.add(tmp);
    }

    public void newAlarm(int reg, int bit){
        DataStruct tmp = new DataStruct(0, reg, bit, "Sensor de Alarme");
        data.add(tmp);
    }

    public void newTemperature(int reg){
        DataStruct tmp = new DataStruct(28, reg, 0, "Temperatura ambiente");
        data.add(tmp);
    }

    public void newBath(){
        //DataStruct tmp = new DataStruct(0, "Nível de água da banheira");
       // data.add(tmp);
    }

    public void newWaterIO(){
        //Trabalhar com porcentagem... (Vão entender?)
        //DataStruct tmp = new DataStruct(0, "Porcentagem de água quente");
        //data.add(tmp);
    }

}