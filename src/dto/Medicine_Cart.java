package dto;

import java.math.BigDecimal;

public class Medicine_Cart {
    private String id;
    private String name;
    private BigDecimal p_p_unit;
    private int qty;
    private double total;

    public Medicine_Cart(String id, String name, BigDecimal p_p_unit, int qty, double total) {
        this.id = id;
        this.name = name;
        this.p_p_unit = p_p_unit;
        this.qty = qty;
        this.total = total;
    }

    public Medicine_Cart() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getP_p_unit() {
        return p_p_unit;
    }

    public void setP_p_unit(BigDecimal p_p_unit) {
        this.p_p_unit = p_p_unit;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Medicine_Cart{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", p_p_unit='" + p_p_unit + '\'' +
                ", qty='" + qty + '\'' +
                ", total=" + total +
                '}';
    }
}
