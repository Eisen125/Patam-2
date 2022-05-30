package Model;

import java.util.List;
import java.util.Observable;


public class model extends Observable {
    public List<command> Clist;
    public SymbolTable Stable;
    


    public model(List<command> clist, SymbolTable stable) {
        Clist = clist;
        Stable = stable;
    }

    public void setClist(List<command> clist) {
        Clist = clist;
    }

    public SymbolTable getStable() {
        return Stable;
    }

    public List<command> getClist() {
        return Clist;
    }
}
