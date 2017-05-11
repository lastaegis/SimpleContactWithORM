package com.example.ianno.simplecontactwithorm.Entity;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Table;

@Table
public class Contact extends SugarRecord{

    private String contactName;
    private String contactPhone;
    private String contactEmail;

    public Contact()
    {

    }

    public Contact(String contactName, String contactPhone, String contactEmail) {
        this.contactName  = contactName;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
