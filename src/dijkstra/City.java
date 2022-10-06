package dijkstra;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class City {
    private int id;
    private String name;
    private Map<Integer, Integer> neighborWithCost = new HashMap();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Integer> getNeighborWithCost() {
        return neighborWithCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id && Objects.equals(name, city.name) && Objects.equals(neighborWithCost, city.neighborWithCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, neighborWithCost);
    }
}
