import java.util.Date;

public class AbstractFactory {
    public static void main(String[] args) {


        BuildingFactory superFactory = new SuperBuildingFactory();
        BuildingFactory mediumFactory = new MediumBuildingFactory();

        House superHouse = superFactory.getHouse();
        ((SuperHouse) superHouse).getHouseInfo();


        House mediumHouse = mediumFactory.getHouse();
        ((MediumHouse) mediumHouse).getHouseInfo();


        Condo superCondo = superFactory.getCondo();
        ((SuperCondo) superCondo).getCondoInfo();

        Condo mediumCondo = mediumFactory.getCondo();
        ((MediumCondo)mediumCondo).getCondoInfo();

        Detacher semiDetacher = superFactory.getDetacher();
        ((SemiDetacher)semiDetacher).getSemiDetacher();

    }
}

interface BuildingFactory{
    House getHouse();
    Condo getCondo();

    Detacher getDetacher();

}

class SuperBuildingFactory implements BuildingFactory{

    @Override
    public House getHouse() {
        return new SuperHouse();
    }

    @Override
    public Condo getCondo() {
        return new SuperCondo();
    }

    @Override
    public Detacher getDetacher(){return new SemiDetacher();}
}

class MediumBuildingFactory implements BuildingFactory{

    @Override
    public House getHouse() {
        return new MediumHouse();
    }

    @Override
    public Condo getCondo() {
        return new MediumCondo();
    }

    @Override
    public Detacher getDetacher(){return new SemiDetacher();}
}




interface House{}
class SuperHouse implements House{
    public House getHouseInfo()
    {
        System.out.printf("SuperHouseInfo\n");
        return null;
    }
}
class MediumHouse implements House{
    public House getHouseInfo()
    {
        System.out.printf("MediumHouseInfo\n");
        return null;
    }
}

interface Condo{}
class SuperCondo implements Condo{
    public House getCondoInfo()
    {
        System.out.printf("SuperCondoInfo\n");
        return null;
    }
}
class MediumCondo implements Condo{
    public House getCondoInfo()
    {
        System.out.printf("MediumCondoInfo\n");
        return null;
    }
}

interface Detacher{}
class SemiDetacher implements Detacher{
    public Detacher getSemiDetacher()
    {
        System.out.printf("SemiDetacherInfo\n");
        return null;
    }
}