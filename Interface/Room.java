import java.util.*;
import javax.swing.*;

abstract class Room{
    protected int info[];
    protected String name[];
    public String roomName;

    abstract JButton createButton();
    abstract void openInfoWindow();
    //Info and name have the same size, because the info[i] is associated to a name[i]
    public int getInfoSize(){
        return info.length;
    }

    public int getInfo(int index){
        if(index >= 0 && index < this.getInfoSize())
            return info[index];

        return -1; //Mudar para Exception... algum dia :P
    }

    public String getName(int index){
        if(index >= 0 && index < this.getInfoSize())
            return name[index];

        return null; //Mudar para Exception... algum dia :P
    }

    public int getInfo(String name){
        for(int i = 0; i < this.name.length; i++)
            if(this.name[i].equals(name)) return info[i];

        return -1; //Mudar para Exception... algum dia :P
    }

    public void setInfo(int index, int value){
        if(index >= 0 && index < this.getInfoSize())
            info[index] = value;
    }

    public void setInfo(String name, int value){
        for(int i = 0; i < this.name.length; i++){
            if(this.name[i].equals(name)){
                info[i] = value;
                return;
            }
        }
    }

}
