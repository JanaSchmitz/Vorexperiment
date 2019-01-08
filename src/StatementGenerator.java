import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class StatementGenerator {
    private final GenerationParameter param;
    private final Random r;
    private final StatementFactory factory;

    public StatementGenerator( GenerationParameter param, StatementFactory factory) {
        this.param = param;
        this.factory = factory;
        this.r = new Random();
    }


    public Statement generateTree() {
        final Statement root = generateRootStatement();
        int currentLines = root.getAllLines();

        while(currentLines < param.getMaxLines()) {
            //System.out.println(((RootStatement) root).getCode());
            if (horizontaldepthisReached(root)) {
                deleteFirstAndLastflags(root);
                setFirstAndLastflags(root);
                final List<Statement> appendStatements = getPossibleAppendStatements(root);
                if(appendStatements.isEmpty()){
                    final List<Statement> appendStatement = getPossibleTrashBranch(root);
                    Statement choosen = chooseRandomStatement(appendStatement);
                    //System.out.println("choosen " + choosen.getEnumInstance());
                    appendRandomTrash(choosen.getParent(), currentLines);
                    deleteFirstAndLastflags(root);
                    setFirstAndLastflags(root);
                }
                appendStatementSmallerThanLineLimit(chooseRandomStatement(appendStatements), param.getMaxLines()- currentLines, currentLines);
                deleteFirstAndLastflags(root);
                setFirstAndLastflags(root);
            }
            else {
                appendToMaxHorizontalDepth(root, currentLines);
                deleteFirstAndLastflags(root);
                setFirstAndLastflags(root);
            }
            currentLines = root.getAllLines();
            deleteFirstAndLastflags(root);
            setFirstAndLastflags(root);
        }
        randomizePositions(root);
        deleteFirstAndLastflags(root);
        setFirstAndLastflags(root);
        return root;
    }


    private List<Statement> getPossibleTrashBranch(Statement root) {
        List<Statement> possibleStatements = new ArrayList<Statement>();

        if(root.getType()== CodeType.TRASH) {
            possibleStatements.add(root);
        }
        for (Statement child : root.children) {
            possibleStatements.addAll(getPossibleTrashBranch(child));
        }
        return possibleStatements;
    }
    private void setFirstAndLastflags(Statement root) {
        if(root.children.size() != 0) {
            root.children.forEach(child -> setFirstAndLastflags(child));
            root.children.get(0).setToFirst();
            root.children.get(root.children.size() - 1).setToLast();
        }
    }
    private void deleteFirstAndLastflags(Statement root) {
        if(root.children.size() != 0) {
            root.children.forEach(child -> deleteFirstAndLastflags(child));
            for (Statement s: root.children) {
                s.deletLastAndFirst();
            }
            root.children.get(0).setToFirst();
            root.children.get(root.children.size() - 1).setToLast();
        }
    }

    private void appendRandomTrash(Statement parent, int lineCount){
        Random r = new Random();
        if(param.getMaxLines() < lineCount){
            factory.injectThrashCode(parent);
        }
        if(param.getMaxLines()-lineCount == 2) {
            factory.injectThrashIf(parent);
        }
        if(param.getMaxLines()-lineCount == 1) {
            factory.injectThrashCode(parent);
        }
        if(param.getMaxLines()-lineCount > 2) {

            if(r.nextBoolean()) {
                factory.injectThrashIf(parent);
            }
            else{
                factory.injectThrashCode(parent);
            }
        }

    }

    private Statement chooseRandomStatement(List<Statement> appendStatements) {
        if (appendStatements.size() == 0) return null;
        final int index = ThreadLocalRandom.current().nextInt(0, appendStatements.size());
        return appendStatements.get(index);
    }

    private void randomizePositions(Statement root) {
        root.children.forEach(child -> randomizePositions(child));
        root.children.sort((a,b) -> a.equals(b)? 0 : ThreadLocalRandom.current().nextInt(-10, 11));
    }

    private void appendToMaxHorizontalDepth(Statement root, int lineCount) {
        Statement appendStatement = getStatementWithHighestHorizontalDepth(root);
        Statement st = factory.appendNextToParent(appendStatement);
        if(st.getLevel() == appendStatement.getMaxDepth()) {
            appendRandomTrash(st, lineCount);
            deleteFirstAndLastflags(root);
            setFirstAndLastflags(root);

        }


    }
    private Statement getStatementWithHighestHorizontalDepth(Statement root) {
        Statement st = root;
        for(Statement child : st.children) {
            Statement maxDepthStatement = getStatementWithHighestHorizontalDepth(child);
            if (maxDepthStatement.getLevel() > st.getLevel()) {
                st = maxDepthStatement;
            }
        }
        return st;
    }
    private void appendStatementSmallerThanLineLimit(Statement parent, int lineLimit, int lineCount) {
        if (parent != null) {

            if(param.getMaxLines() - lineCount <= 3){
                if(!parent.children.isEmpty()) {
                    if(parent.children.get(parent.children.size()-1).getType() == CodeType.TRASH){
                        appendRandomTrash(parent, lineCount);
                    }else {
                        System.out.println("im else");
                        System.out.println("line count " + lineCount);
                        final List<Statement> appendStatement = getPossibleTrashBranch(parent);
                        Statement choosen = chooseRandomStatement(appendStatement);
                        if(choosen.isSwitchTrash() == true){

                        }else{
                            appendRandomTrash(choosen.getParent(), lineCount);
                        }
                        //System.out.println("choosen " + choosen.getEnumInstance());

                        /*
                        appendRandomTrash(parent.children.get(parent.children.size() - 1), lineCount);
                        getPossibleTrashBranch(parent);
                        */

                    }
                }else{
                    appendRandomTrash(parent, lineCount);
                }
            }else {

                if (!parent.children.isEmpty() && parent.children.get(parent.children.size() - 1).getType() == CodeType.TRASH) {

                    parent.children.remove(parent.children.size() - 1);
                    if (param.getCodeType().statementLineCount <= lineLimit) {
                        Statement ment = factory.appendNextToParent(parent);
                        appendRandomTrash(ment, lineCount);
                    }
                } else {
                    if (param.getCodeType().statementLineCount <= lineLimit) {
                        Statement ment = factory.appendNextToParent(parent);
                        appendRandomTrash(ment, lineCount);
                    } else {
                        factory.injectThrashCode(parent.children.get(parent.children.size() - 1));
                    }
                }

            }
        } else{
            return;
        }
    }

    private boolean horizontaldepthisReached(Statement root) {
        Statement maxDepth = getStatementWithHighestHorizontalDepth(root);
        return maxDepth.getLevel() >= root.getMaxDepth();
    }

    private List<Statement> getPossibleAppendStatements(Statement root) {
        Statement st = root;
        List<Statement> possibleStatements = new ArrayList<Statement>();

        if(root.getLevel() != param.getHorizontalDepth() && root.children.size() != param.getVerticalDepth() && root.getLevel() != param.getHorizontalDepth()+1) {

            possibleStatements.add(root);
        }
        for (Statement child : root.children) {
            possibleStatements.addAll(getPossibleAppendStatements(child));
        }
        return possibleStatements;
    }

    private Statement generateRootStatement() {
        RootStatement root = new RootStatement(0, "root", "root" , "root", param.getHorizontalDepth());
        root.setChildrenType(param.getCodeType());
        return root;
    }
}





