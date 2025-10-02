package org.example;

import java.util.HashMap;

public class ContextService {
    private static ContextService instance;

    private HashMap<String, Object> data = new HashMap<>();

    private ContextService() {}

    public static ContextService getInstance() {
        if(instance == null) {
            instance = new ContextService();
        }
        return instance;
    }
    public Object getData(String key){
        return data.get(key);
    }

    public void setData(String key,Object value){
        data.put(key,value);
    }
}
