package models;

import java.util.Map;
import java.util.TreeMap;

public class AWResponse {
	 
    private Map<String, Object> data = new TreeMap<>();
    private boolean ok;
 
    public Map<String, Object> getdata()
    {
        return data;
    }
 
    public void setdata(Map<String, Object> data)
    {
        this.data = data;
    }
    
    public boolean getok()
    {
        return ok;
    }
    
    public void setok(boolean ok)
    {
        this.ok = ok;
    }
 
   /* @Override
    public String toString() {
        return " AWResponse[data=" + data + "]";
    }*/
}
