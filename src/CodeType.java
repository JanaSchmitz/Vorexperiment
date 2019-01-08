ppublic enum CodeType { IF(2), SWITCH(2), TRASH(1), CASE(2), ENUM(1), ROOT(0);

    public int statementLineCount;

    CodeType(int statementLineCount) {
        this.statementLineCount = statementLineCount;
    }
}
