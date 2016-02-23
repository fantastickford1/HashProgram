package core;

public class NodeS <T>{
    //Referencia al siguiente elemento o a NULL si es el ultimo
    NodeS<T> next;
    NodeS<T> collision = null;
    T data;
    // NodeS constructor
    public NodeS(T dataValue) {
        next = null;
        data = dataValue;
    }
    // Constructor que incluye el siguiente nodo
    public NodeS(T dataValue, NodeS<T> nextValue) {
        next = nextValue;
        data = dataValue;
    }

    public T getData() {
        return data;
    }

    public void setData(T dataValue) {
        data = dataValue;
    }

    public NodeS<T> getNext() {
        return next;
    }

    public void setNext(NodeS<T> nextValue) {
        next = nextValue;
    }

    public void setCollision(NodeS<T> nextValue){
        collision = nextValue;
    }

    public NodeS<T> getCollision(){
        return collision;
    }

}
