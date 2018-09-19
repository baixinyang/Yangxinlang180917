package yangxinlang.bawei.com.yangxinlang180917.bean;

public class ChildBean {
    private String id;
    private String name;
    private String price;
    private boolean isGoodsChock;

    public ChildBean(String id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setGoodsChock(boolean goodsChock) {
        isGoodsChock = goodsChock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public boolean isGoodsChock() {
        return isGoodsChock;
    }
}
