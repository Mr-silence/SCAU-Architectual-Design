import java.util.ArrayList;
import java.util.List;

//接口
interface University{
    int count();
}

class Teacher implements University{
    private int salary=0;
    private String name;

    public Teacher(String name,int salary)
    {
        this.name=name;
        this.salary=salary;
    }

    @Override
    public int count()
    {
        return salary;
    }
}

class Organization implements University
{
    private List<University>universityList=new ArrayList<>();

    public void add(University university)
    {
        universityList.add(university);
    }

    public void delete(University university)
    {
        universityList.remove(university);
    }

    public List<University> getchild()
    {
        return universityList;
    }

    @Override
    public int count()
    {
            int salary=0;
            for(University university:universityList)
            {
                salary+=university.count();
            }
            return salary;
    }
}


public class CompositePattern {
    public static void main(String[] args) {
        Organization scau =new Organization();

        Organization organization0 = new Organization();
        Organization organization1 = new Organization();
        Organization organization3 = new Organization();
        Organization organization4 = new Organization();
        Organization organization2 = new Organization();

        scau.add(organization0);
        organization0.add(organization1);
        organization1.add(organization2);
        organization2.add(organization3);
        organization2.add(organization4);

        organization1.add(new Teacher("B001",4000));
        organization2.add(new Teacher("B002",5000));
        organization2.add(new Teacher("B003",7000));
        organization3.add(new Teacher("B004",6000));
        organization4.add(new Teacher("B005",8000));

        System.out.println(scau.count());
    }
}
