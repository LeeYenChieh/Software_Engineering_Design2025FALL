import java.util.ArrayList;

public class Iterator{
    public int curIndex = -1;
    public ArrayList<Component> components;

    public Iterator(ArrayList<Component> components){
        this.components = components;
    }

    public <T> T next(){
        this.curIndex += 1;
        return (T) this.components.get(curIndex);
    }

    public boolean hasNext(){
        if(this.curIndex < this.components.size() - 1)
            return true;
        return false;
    }
    }