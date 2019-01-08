public class CASE extends Statement {
    private boolean isFirst;
    private boolean isLast;

    public CASE(int level, String enumName, String enumInstance, String enumIdentifier, int maxDepth) {
        super(level, enumName, enumInstance, enumIdentifier, maxDepth);
        this.isFirst = false;
        this.isLast = false;
    }

    @Override
    protected int getLineCount() {
        return 2;
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

    public boolean getLastStatement(){
        return this.isLast;
    }
    public boolean isFirstStatement(){
        return this.isFirst;
    }

    public String generateString(Words words){
        String code ="";
        return code;
    }
    @Override
    public String toString() {
        String code ="";

        if (isFirst){
            code +=   "         " + getSpaces() + "switch (" + enumName + "){\n";
        }

        code = getCaseBlock(code);

        if (isLast){
            code += "       "+ "      " + getSpaces() + "break; } \n";
            // code +=  "      "+"      " + getSpaces() + " }\n";
        }
        else {
            code +="       " + "      " + getSpaces() + "break; \n";
        }

        return code;
    }

    private String getCaseBlock(String code) {
        code += "     "+"      " + getSpaces() + "case " + this.enumInstance + ":" + "\n";

        for(Statement s: children) {
            code +=  s.toString();
        }
        return code;
    }

    @Override
    public CodeType getType() {
        return CodeType.SWITCH;
    }

}
