package com.maity.maityspositiveliving.POJO;

public class SubcategoryList {
    String id,category_name,category_desc,category_icon,parent_category,amount,status;

    public SubcategoryList(String id, String category_name, String category_desc, String category_icon, String parent_category, String amount, String status) {
        this.id = id;
        this.category_name = category_name;
        this.category_desc = category_desc;
        this.category_icon = category_icon;
        this.parent_category = parent_category;
        this.amount = amount;
        this.status = status;
    }

    public String getCategory_desc() {
        return category_desc;
    }

    public void setCategory_desc(String category_desc) {
        this.category_desc = category_desc;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_icon() {
        return category_icon;
    }

    public void setCategory_icon(String category_icon) {
        this.category_icon = category_icon;
    }

    public String getParent_category() {
        return parent_category;
    }

    public void setParent_category(String parent_category) {
        this.parent_category = parent_category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
