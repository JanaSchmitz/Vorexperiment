import java.util.ArrayList;
import java.util.List;

public class RootStatement extends Statement{
    public int  nbMax = 0;
    public CodeType childrenType;
    private boolean isFirst;
    private boolean isLast;
    protected Words words;
    private IdentifierArt art;

    public RootStatement(int level, String enumName, String enumInstance, String enumIdentifier, int maxDepth) {
        super(level, enumName, enumInstance,enumIdentifier, maxDepth);
        this.art = art;

    }

    public CodeType getChildrenType() {
        return childrenType;
    }

    public void setChildrenType(CodeType type){
        this.childrenType = type;
    }

    @Override
    protected int getLineCount() {
        return 0;
    }

    @Override
    public CodeType getType() {
        return null;
    }

    public void setToLast() {
        this.isLast = true;
    }

    public void setToFirst() {
        this.isFirst = true;
    }

    public void deletLastAndFirst() {
        this.isLast = false;
        this.isFirst = false;
    }

    public void setNbMax(){
        nbMax++;
    }

    public int getNbMax(){
        return this.nbMax;
    }

    public String getCode(){
        String code ="";
        for(Statement c: children){
            code += c.toString();
        }
        return code;
    }
}
