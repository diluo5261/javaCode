package com.dilo.vo;

public class QueryParam {
    private String paraName;
    private Integer paraAge;

    public QueryParam() {
    }

    public String getParaName() {
        return paraName;
    }

    public void setParaName(String paraName) {
        this.paraName = paraName;
    }

    public Integer getParaAge() {
        return paraAge;
    }

    public void setParaAge(Integer paraAge) {
        this.paraAge = paraAge;
    }

    @Override
    public String toString() {
        return "QueryParam{" +
                "paraName='" + paraName + '\'' +
                ", paraAge=" + paraAge +
                '}';
    }
}
