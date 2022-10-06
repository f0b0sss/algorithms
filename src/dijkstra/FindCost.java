package dijkstra;

import java.util.Arrays;
import java.util.List;

public class FindCost {
    private final int MAX_SUM = 200000; //maximum possible cost
    private int numbersOfCities;
    private int[][] graph;  // input data to matrix, all variations cost per city
    private int[][] resultGraph;  //result matrix with final calculation

    public String findCost(City fromCity, City toCity, List<City> cities) {
        if (fromCity.getId() != toCity.getId()){
            numbersOfCities = cities.size();  // = numbers of cities
            int startId = fromCity.getId() - 1;  // get city id from where

            initGraph(cities); // generate matrix with input parameters: set value to cell with coordinates city to city if not have neighbors - set 0

            initResultGraph(); // generate result matrix and fill all cells with default MAX SUM

            int[] visited = new int[numbersOfCities]; //array to check what city id was visited
            Arrays.fill(visited, 0); //fill visited array with value 0
            int countVisited = 0; //get in memory how many id was already visited

            int startCell = startId; //before all iteration var contain number of cell with min value
            int nextStartCell = 0; //in calculation process this var will contain in memory cell with min number
            int lastMinCost = 0; //min cost from all cells after iteration
            int currentMinCost = 0; //min cost in iteration process

            for (int i = 0; i < resultGraph.length; i++) {
                for (int j = 0; j < resultGraph.length; j++) {
                    if (i == 0) { //first iter goes inside, all other - in line 'else'
                        if (graph[startCell][j] != 0) { //if value in coordinates city to city != 0
                            resultGraph[i][j] = graph[startCell][j] + lastMinCost; //set value, in result matrix, in first line = value from same input matrix coordinates + 0

                            if (currentMinCost == 0) { //if first iter
                                currentMinCost = resultGraph[i][j]; //currentMinCost = value set before
                                nextStartCell = j; //mark current cell as probably with min value
                            } else {
                                if (resultGraph[i][j] < currentMinCost) { //if value in new cell less than value in var currentMinCost set in past
                                    currentMinCost = resultGraph[i][j]; //currentMinCost = this value of that cell
                                    nextStartCell = j;  // and mark this cell as cell with min cost
                                }
                            }
                        }

                    } else {
                        if (visited[j] != 1 && graph[startCell][j] != 0 ) { //current cell must be not watched before as cell with min cost
                            // and value in city to city cost matrix not be '0'
                            resultGraph[i][j] = graph[startCell][j] + lastMinCost; //set value to result matrix in current coordinates

                            if (resultGraph[i][j] > resultGraph[i - 1][j]) { //if value in current line more then value in previous line at the same vertical line
                                resultGraph[i][j] = resultGraph[i - 1][j]; // current cell value = previous value
                            }

                            if (currentMinCost == 0) { //if first iter by new cycle
                                currentMinCost = resultGraph[i][j]; //currentMinCost = value set before
                                nextStartCell = j; //mark current cell as probably with min value
                            } else {
                                if (resultGraph[i][j] < currentMinCost) { //if value in new cell less than value in var currentMinCost set in past
                                    currentMinCost = resultGraph[i][j]; //currentMinCost = this value of that cell
                                    nextStartCell = j; // and mark this cell as cell with min cost
                                }
                            }
                        }
                    }
                }

                //after iter

                lastMinCost = currentMinCost; // set min cost to this var

                visited[startCell] = 1; //marked reviewed cell as visited

                startCell = nextStartCell; //set var next startCell with coordinates of cell with min cost
                currentMinCost = 0; //reset

                countVisited++; //increment visited cells

                if (countVisited == visited.length) { //exit from loop if all cities was reviewed
                    break;
                }
            }
            return "from " + fromCity.getName() + " to " + toCity.getName() +
                    " will be cost " + findResult(toCity.getId());
        }else {
            return "You ale already in " + fromCity.getName();
        }
    }

    //get last value less than MAX, by vertical line by city id where we go
    private int findResult(int cityId) {
        int vertical = cityId - 1;
        int result = 0;

        for (int i = numbersOfCities - 1; i != -1; i--) {

            if (resultGraph[i][vertical] != MAX_SUM){
                result =  resultGraph[i][vertical];
                break;
            }
        }
        return result;
    }

    private void initResultGraph() {
        resultGraph = new int[numbersOfCities][numbersOfCities];

        for (int i = 0; i < resultGraph.length; i++) {
            for (int j = 0; j < resultGraph.length; j++) {
                resultGraph[i][j] = MAX_SUM;
            }
        }
    }

    private void initGraph(List<City> cities) {
        graph = new int[numbersOfCities][numbersOfCities];

        for (int i = 0; i < numbersOfCities; i++) {
            for (int j = 0; j < numbersOfCities; j++) {
                if (cities.get(i).getNeighborWithCost().get(j + 1) != null) {
                    graph[i][j] = cities.get(i).getNeighborWithCost().get(j + 1);
                } else {
                    graph[i][j] = 0;
                }
          //      System.out.print(graph[i][j] + ", ");
            }
       //     System.out.println();
        }

    }

}
