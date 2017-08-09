package kz.epam.javalab22.tag;

import java.util.HashSet;
import java.util.Iterator;

public class MySet extends HashSet {
    Iterator it;

    public MySet(){
        this.add("Sun");
        this.add("Microsoft");
        this.add("IBM");
    }

    public String getSize(){
        it = this.iterator();
        return Integer.toString(this.size());
    }

    public String getElement(){
        return it.next().toString();
    }

}
