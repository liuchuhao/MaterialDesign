package cn.cq1s.materialtest;

public class Fruit{
    private String name;
    private String imageId;
public Fruit(String name,String imageId){
    this.name=name;
    this.imageId=imageId;
}

    public String getName(){
    return name;
}

    public String getImageId() {
        return imageId;
    }
}
