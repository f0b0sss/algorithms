package parentheses;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Service {

    private char[] arr; //arr to collect combination of Parentheses
    private int open; //number of open parentheses
    private int close; //number of close parentheses
    private int index; //arr index where need to put next parentheses

    public Service(int n) {
        this.arr = new char[n * 2]; //max array length is number of same parentheses multiply two
        this.open = 0;
        this.close = 0;
        this.index = 0;
    }

    public Service() {
    }

    public static int getAllVariation(int number) {
        int max = number; //max number of same parentheses
        int count = 0;  //for counting already closed individual combination

        List<Service> list = new LinkedList<>();  //collect all objects
        Service service = new Service(max);
        list.add(service);

        boolean loop = true;

        while (loop) {

            for (int i = 0; i < list.size(); i++) {
                Service var = list.get(i);

                if (list.size() == 1) {  //if first time in loop
                    var.arr[0] = '(';  // add '(' to index 0
                    var.index++; //to put next parentheses in next cell
                    var.open++;  //number of open parentheses + 1
                }

                if (list.get(i).arr[max * 2 - 1] == ')') {  //if last cell in arr = ')', count of completed variant + 1
                    count++;
                }
                if (count == list.size()) { //if count of completed variants == list size then exit loop
                    loop = false;
                } else {
                    //if count of open parentheses more then count of close parentheses
                    //and count of open parentheses not equals max allowable in arr
                    if (var.open > var.close && var.open != max) {
                        //we need to split this variant arr in two different
                        Service newObj = new Service();  //create new Object
                        newObj.arr = Arrays.copyOf(var.arr, max * 2); //copy arr in loop to this new object
                        newObj.open = var.open;
                        newObj.close = var.close;
                        newObj.index = var.index;
                        newObj.arr[newObj.index] = ')'; //and put in next free cell ')'
                        newObj.close++; //++ close parentheses
                        newObj.index++; //++ shifting the cell index

                        list.add(newObj); //add new object to list

                        var.arr[var.index] = '(';  // and '(' to current arr in loop
                        var.open++;
                        var.index++;
                    } else {
                        if (var.close == var.open) {  //if count of open parentheses = count of close parentheses, need to add '(' to thin cell
                            var.arr[var.index] = '(';
                            var.open++;
                            var.index++;
                        }
                        if (var.open == max) { //if count of open parentheses = max allowable value, need to add ')' in quantity of remaining free cells
                            for (int j = var.index; j < max * 2; j++) {
                                var.arr[j] = ')';
                            }
                        }
                    }
                }
            }
        }

        System.out.println(list);

        return list.size();
    }

    @Override
    public String toString() {
        return Arrays.toString(arr) + "\n";
    }
}
