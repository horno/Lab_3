public class Human{
    public String name;

    public Human(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
    @Override
    public boolean equals(Object obj){
        return this.name.equals(((Human)obj).name);
    }

}