package id.thesis.shumishumi.common.database;


import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.model.context.PagingContext;
import id.thesis.shumishumi.common.model.datastructure.Pair;

import java.util.ArrayList;
import java.util.List;

public class StatementBuilder {
    private final StringBuilder statementBuilder = new StringBuilder();
    private final String statementType;
    private final String tableName;
    private final List<Pair<String, String>> selectList;
    private final List<String> setList;
    private final List<Pair<String, Pair<String, String>>> whereList;
    private final List<String> valueList;
    private PagingContext pagingContext;

    public StatementBuilder(String tableName, String statementType) {
        this.statementType = statementType;
        this.tableName = tableName;
        selectList = new ArrayList<>();
        setList = new ArrayList<>();
        whereList = new ArrayList<>();
        valueList = new ArrayList<>();
        pagingContext = null;
    }

    public String buildStatement() {
        initStatement();
        buildValueStatement();
        buildSetStatement();
        buildWhereStatement();
        buildLimitStatement();
        return statementBuilder.toString();
    }

    private void initStatement() {
        if (DatabaseConst.STATEMENT_INSERT.equals(statementType)) {
            statementBuilder.append("insert into ").append(tableName);
        } else if (DatabaseConst.STATEMENT_UPDATE.equals(statementType)) {
            statementBuilder.append("update ").append(tableName);
        } else if (DatabaseConst.STATEMENT_DELETE.equals(statementType)) {
            statementBuilder.append("delete from ").append(tableName);
        } else if (DatabaseConst.STATEMENT_SELECT.equals(statementType)) {
            buildSelectStatement();
        }
    }

    private void buildSelectStatement() {

        statementBuilder.append("select ");

        boolean needComma = false;
        for (Pair<String, String> select : selectList) {
            if (needComma) {
                statementBuilder.append(", ");
            }

            statementBuilder.append(select.getFirst());
            if(!"-".equalsIgnoreCase(select.getSecond())) {
                statementBuilder.append(String.format(" as %s", select.getSecond()));
            }
            needComma = true;
        }

        statementBuilder.append(String.format(" from %s", tableName));
    }

    private void buildValueStatement() {
        if (!DatabaseConst.STATEMENT_INSERT.equals(statementType)) {
            return;
        }

        boolean needComma = false;
        statementBuilder.append("(");
        for (String value : valueList) {
            if (needComma) {
                statementBuilder.append(", ");
            }

            statementBuilder.append(value);
            needComma = true;
        }
        statementBuilder.append(") VALUES(");

        needComma = false;
        for (int i = 0; i < valueList.size(); ++i) {
            if (needComma) {
                statementBuilder.append(", ");
            }

            statementBuilder.append("?");
            needComma = true;
        }

        statementBuilder.append(")");
    }

    private void buildSetStatement() {
        if (!DatabaseConst.STATEMENT_UPDATE.equals(statementType)) {
            return;
        }

        statementBuilder.append(" SET ");

        boolean needComma = false;
        for (String set : setList) {
            if (needComma) {
                statementBuilder.append(", ");
            }

            statementBuilder.append(String.format("%s = ?", set));
            needComma = true;
        }
    }

    private void buildWhereStatement() {
        if (DatabaseConst.STATEMENT_INSERT.equals(statementType) || whereList.isEmpty()) {
            return;
        }

        statementBuilder.append(" WHERE ");
        boolean isFirst = true;
        for (Pair<String, Pair<String, String>> where : whereList) {
            String key = where.getFirst();
            String appendOperator = where.getSecond().getFirst();
            String comparator = where.getSecond().getSecond();
            if (!isFirst) {
                statementBuilder.append(String.format(" %s ", appendOperator));
            }

            if (DatabaseConst.COMPARATOR_IN.equals(comparator)) {
                statementBuilder.append(String.format("%s %s (?)", key, comparator));
            } else {
                statementBuilder.append(String.format("%s %s ?", key, comparator));
            }
            isFirst = false;
        }
    }

    private void buildLimitStatement() {
        if (pagingContext == null) {
            return;
        }

        int offset = pagingContext.calculateOffset();
        int numberOfItem = pagingContext.getNumberOfItem();
        statementBuilder.append(String.format(" LIMIT %s,%s ", offset, numberOfItem));
    }

    public StatementBuilder addSelectStatement(String value) {
        return addSelectStatement(value, "-");
    }

    public StatementBuilder addSelectStatement(String value, String as) {
        selectList.add(new Pair<String, String>(value, as));
        return this;
    }

    public StatementBuilder addWhereStatement(String appendOperator, String key, String comparator) {
        whereList.add(new Pair<>(key, new Pair<>(appendOperator, comparator)));
        return this;
    }

    public StatementBuilder addValueStatement(String key) {
        valueList.add(key);
        return this;
    }

    public StatementBuilder addSetStatement(String key) {
        setList.add(key);
        return this;
    }

    public StatementBuilder addLimitStatement(PagingContext pagingContext) {
        this.pagingContext = pagingContext;
        return this;
    }
}
