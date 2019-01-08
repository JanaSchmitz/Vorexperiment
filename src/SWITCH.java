public class SWITCH extends Statement {


    public SWITCH(int level, String enumName, String enumInstance, String enumIdentifier, int maxDepth) {
        super(level, enumName, enumInstance,enumIdentifier, maxDepth);
    }

    @Override
    protected int getLineCount() {
        return 3;
    }

    @Override
    public CodeType getType() {
        return CodeType.SWITCH;
    }

    @Override
    public void setToLast() {

    }

    @Override
    public void setToFirst() {

    }
    @Override
    public void deletLastAndFirst() {

    }


    @Override
    public String toString() {
        String code = getSpaces() + "switch";

        for(Statement s: children) {
            code += s.toString();
        }

        return code;
    }


}
