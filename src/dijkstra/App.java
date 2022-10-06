package dijkstra;

import java.util.ArrayList;
import java.util.List;

public class App {
    private static List<City> cities = new ArrayList<>();

    public static void main(String[] args) {
        FindCost findCost = new FindCost();

        City gdansk = new City();
        gdansk.setId(1);
        gdansk.setName("gdansk");
        gdansk.getNeighborWithCost().put(2, 1);
        gdansk.getNeighborWithCost().put(3, 3);

        City bydgoszcz = new City();
        bydgoszcz.setId(2);
        bydgoszcz.setName("bydgoszcz");
        bydgoszcz.getNeighborWithCost().put(1, 1);
        bydgoszcz.getNeighborWithCost().put(3, 1);
        bydgoszcz.getNeighborWithCost().put(4, 4);

        City torun = new City();
        torun.setId(3);
        torun.setName("torun");
        torun.getNeighborWithCost().put(1, 3);
        torun.getNeighborWithCost().put(2, 1);
        torun.getNeighborWithCost().put(4, 1);

        City warszawa = new City();
        warszawa.setId(4);
        warszawa.setName("warszawa");
        warszawa.getNeighborWithCost().put(2, 4);
        warszawa.getNeighborWithCost().put(3, 1);

        cities.add(gdansk);
        cities.add(bydgoszcz);
        cities.add(torun);
        cities.add(warszawa);

       System.out.println(findCost.findCost(gdansk, warszawa, cities));
       System.out.println(findCost.findCost(warszawa, gdansk, cities));
       System.out.println(findCost.findCost(bydgoszcz, warszawa, cities));
       System.out.println(findCost.findCost(warszawa, bydgoszcz, cities));
       System.out.println(findCost.findCost(bydgoszcz, gdansk, cities));
       System.out.println(findCost.findCost(torun, warszawa, cities));
       System.out.println(findCost.findCost(torun, torun, cities));

    }


}

