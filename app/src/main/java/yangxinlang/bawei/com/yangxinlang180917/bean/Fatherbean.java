package yangxinlang.bawei.com.yangxinlang180917.bean;

public class Fatherbean {
    private String id;
    private String name;
    private boolean isChock;
    public Fatherbean(String id, String name) {
        this.id = id;
        this.name = name;

    }
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChock(boolean chock) {
        isChock = chock;
    }



    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isChock() {
        return isChock;
    }
}
