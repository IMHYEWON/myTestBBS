package enums;


public enum TableStatus {

    Y("1"),
    N("0");

    private String table1Value;

    TableStatus(String table1Value) {
        this.table1Value = table1Value;
    }

    public String getTable1Value() {
        return table1Value;
    }

}