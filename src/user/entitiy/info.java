package user.entitiy;

public class info {
    int type;
    String context;

    public info(int type, String context) {
        this.type = type;
        this.context = context;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "info{" +
                "type=" + type +
                ", context='" + context + '\'' +
                '}';
    }
}
