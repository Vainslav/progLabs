package org.example.managers;

import java.util.HashMap;

public class RecursionManager {
    private static Integer recursionDepth;
    private static final HashMap<String,Integer> recursionMap = new HashMap<>();

    public static void setRecursionDepth(Integer recursion){
        recursionDepth = recursion;
    }

    public static HashMap<String, Integer> getRecursionMap() {
        return recursionMap;
    }

    public static Integer getRecursionDepth() {
        return recursionDepth;
    }

    public static void cleaRecursionMap(){
        recursionMap.clear();
    }
}
