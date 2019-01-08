import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public abstract class Statement {


    protected final int level;
    protected final String enumName;
    protected final String enumInstance;
    public List<Statement> children;
    protected  int count;
    protected int maxDepth;
    public boolean isMax = false;
    protected CodeType type;
    protected Statement parent;
    protected String enumIdentifier;
    private boolean isSwitchTrash = false;


    public Statement(int level, String enumName, String enumInstance, String enumIdentifier, int maxDepth){
        this.level = level;
        this.enumInstance = enumInstance;
        this.enumIdentifier = enumIdentifier;
        this.enumName = enumName;
        this.children = new ArrayList<>();
        this.maxDepth = maxDepth;

    }


    public boolean isSwitchTrash() {
        return isSwitchTrash;
    }

    public int getLevel() {
        return level;
    }

    protected abstract int getLineCount();


    public String getEnumInstance() {
        return this.enumInstance;
    }

    public String getEnumName() {
        return this.enumName;
    }

    public int getMaxDepth(){ return maxDepth; }

    public int getChildNumber(){
        return children.size();
    }

    public List<Statement> getChildren() {
        return children;
    }


    public void setIsMax(boolean state){
        this.isMax = state;
    }

    public void addChild(Statement child) {
        this.children.add(child);
    }

    public void removeChild(Statement child) {
        this.children.remove(child);
    }

    public int getLines(){
        int lines = this.getLineCount();
        for (Statement child : this.children) {
            lines += child.getLines();
        }
        return lines;
    }

    public int getChildIndex(){


        return 0;
    }
    public Statement getParent(){
        return this.parent;
    }

    public abstract CodeType getType();






    public int getMyLines(){
        int lines = countLines(this.toString());
        return lines;
    }

    protected String getSpaces(){
        String spaces = "";

        for (int i = 0; i < this.level; i++) {
            spaces += ("    ");
        }
        return spaces;
    }



    protected String getSpaces(int depth){
        String spaces = "";

        for (int i = 0; i < depth; i++) {
            spaces += ("    ");
        }
        return spaces;
    }

    protected Statement getChild(int i){
        if(i < children.size()) {
            return  children.get(i);
        }
        return null;
    }


    public boolean isLastStatement() {
        if (type == CodeType.TRASH){
            return true;
        }

        return false;
    }


    public static int countLines(String str) {
        if(str == null || str.isEmpty())
        {
            return 0;
        }
        int lines = 1;
        int pos = 0;
        while ((pos = str.indexOf("\n", pos) + 1) != 0) {
            lines++;
        }
        return lines;
    }


    public int getAllLines(){
        int lines = 0;
        for(Statement s : children){
            lines  += s.getMyLines();
        }
        return lines;
    }

    public void findMax(RootStatement root){
        for(int i = 0; i < children.size(); i++){
            if(children.get(i).getLevel() == maxDepth-1){
                children.get(i).setIsMax(true);
                System.out.println("true "+ children.get(i).getEnumInstance());
            }

            children.get(i).findMax(root);
        }
    }
    public void countMax(RootStatement root) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).isMax) {
                root.setNbMax();
            }
            children.get(i).countMax(root);
        }
    }
    public abstract void setToLast();

    public abstract void setToFirst();

    public abstract void deletLastAndFirst();







}




