public class IF extends Statement{


    private static final int lineCount = 2;
    private Words words;
    public IdentifierArt art;

    public IF(int level, String enumName, String enumInstance, String enumIdentifier, int maxDepth, Words words, IdentifierArt art) {
        super(level, enumName, enumInstance,enumIdentifier, maxDepth);
        this.words = words;
        this.art = art;
    }

    @Override
    protected int getLineCount() {
        return lineCount;
    }

    @Override
    public CodeType getType() {
        return CodeType.IF;
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
        String code;
        if(art == IdentifierArt.randomIdentifier){
            String identifier = words.getFreeWord();
            code = "     " + getSpaces() + "if(" + identifier.toLowerCase() + " == " + enumName + "." + this.enumInstance + ") {\n";
        }else {
            code = "     " + getSpaces() + "if(" + this.enumName.toLowerCase() + " == " + enumName + "." + this.enumInstance + ") {\n";
        }

        for(Statement s: children) {
            code += s.toString();
        }

        code += "     " + getSpaces() + "}\n";

        return code;
    }


}
