public class StatementFactory {

    private static EnumBlock[] enums;
    private static GenerationParameter param;
    public Words words;


    public StatementFactory(EnumBlock[] enums, GenerationParameter param, Words words) {
        // System.out.println("enum length: " + enums.length);
        this.enums = enums;
        this.param = param;
        this.words = words;
    }

    public Statement appendNextToParent(Statement parent) {
        /** level = enum number
         Instance = children number **/
        int level = parent.getLevel()+1;
        int childNumber = parent.getChildNumber();

        if(param.getCodeType() == CodeType.IF){
            IF newIf = new IF(level, enums[level-1].getName(), enums[level-1].getInstances(childNumber), enums[level-1].getIdentifier(childNumber),param.getHorizontalDepth(), words, param.getArt());
            parent.addChild(newIf);
            return newIf;
        }else{
            CASE newCase = new CASE(level, enums[level-1].getName(), enums[level-1].getInstances(childNumber),enums[level-1].getIdentifier(childNumber), param.getHorizontalDepth());
            parent.addChild(newCase);
            return newCase;
        }
    }

    public  void injectThrashCode(Statement parent) {
        TRASH trashCode =   new TRASH(parent.getLevel()+1, "trash "/* parent.getEnumName() */, /*parent.getEnumInstance()*/ "Trash", "trash",  parent.getMaxDepth(), parent, words, param);
        trashCode.generateTrashCode();
        parent.addChild(trashCode);
    }

    public  void injectThrashIf(Statement parent) {
        TRASH trashIf =  new TRASH(parent.getLevel()+1, "trash", "trash", "trash", parent.getMaxDepth(), parent, words, param);
        trashIf.generateTrash();
        parent.addChild(trashIf);
    }
}
