package cn.cq1s.materialtest.db;

import org.litepal.crud.DataSupport;

public class PersonData extends DataSupport {
    private int id;
    private String personName;
    private String personCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }
}
