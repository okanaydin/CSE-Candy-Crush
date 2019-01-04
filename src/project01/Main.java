package project01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    // Properties of Candy
    public static final String WHITE = "\033[0;30m";   // WHITE
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String BLUE = "\033[0;34m";    // BLUE

    public static final String SHAPE1 = String.valueOf('\u00a5');
    public static final String SHAPE2 = String.valueOf('\u01FC');
    public static final String SHAPE3 = String.valueOf('\u0391');
    public static final String SHAPE4 = String.valueOf('\u03A9');

    static  int totalPoint;
    //Shape and Color List
    public static String[] arrayShape = {SHAPE1, SHAPE2, SHAPE3, SHAPE4};
    public static String[] arrayColor = {WHITE, RED, GREEN, BLUE};

    // Create Candy List
    public static Queue<Candy> candyQueue = createCandyQueue(40);

    // Create Stacks
    public static Stack<Candy> stackOne = new Stack<>(20);
    public static Stack<Candy> stackTwo = new Stack<>(20);
    public static Stack<Candy> stackThree = new Stack<>(20);

    // Main Loop
    public static void main(String[] args) {

        while (true) {
            printFirstFiveCandyInQueue();
            System.out.println(" ");
            getUserInput();
        }

    }


    public static Queue<Candy> createCandyQueue(int size) {
        Queue<Candy> queue = new Queue<>(size);
        for (int i = 0; i < size; i++) {
            queue.enqueue(generatorRandomCandy());
        }
        return queue;
    }


    public static Candy generatorRandomCandy() {
        Random colorRandom = new Random();
        Random shapeRandom = new Random();

        String color = arrayColor[colorRandom.nextInt(arrayColor.length)];
        String shape = arrayShape[shapeRandom.nextInt(arrayShape.length)];

        return new Candy(shape, color);
    }


    public static ArrayList<Stack<Candy>> createStackArray() {
        ArrayList<Stack<Candy>> arrayList = new ArrayList<>();
        arrayList.add(stackOne);
        arrayList.add(stackTwo);
        arrayList.add(stackThree);
        return arrayList;
    }

    public static void printStacks(List<Stack<Candy>> stackList) {

        System.out.println(WHITE +"\n ***************** Stacks *****************");

        // stackler arasındaki en büyük size ı hesapla
        int maxStack = stackList.get(0).size();

        for (int i = 0; i < stackList.size(); i++) {
            if (stackList.get(i).size() > maxStack) {
                maxStack = stackList.get(i).size();
            }
        }

        //her bir satırı yazdırmak için döngü
        for (int i = maxStack - 1; i >= 0; i--) {

            //Her satırı yazarken stacklerin en üstteki elemanlarına bak
            for (int j = 0; j < stackList.size(); j++) {
                Stack<Candy> candyStack = stackList.get(j);
                if (candyStack.size() > i) {
                    printCandy(candyStack.elementAt(i));
                } else {
                    System.out.printf("   ");
                }
            }
            System.out.println();
        }

    }

    public static void printCandy(Candy candy) {
        System.out.printf(" " + candy.getColor() + candy.getShape() + " ");
    }

    public static void printFirstFiveCandyInQueue() {
        System.out.println();
        System.out.println(WHITE + "*************** Candies in the queue ***************");
        System.out.printf("[ ");
        for (int i = 0; i < 5; i++) {
            Candy candy = candyQueue.getData(i);
            if (candy == null) {
                System.out.printf(WHITE + " ]");
                break;
            }
            System.out.printf(" " + candy.getColor() + candy.getShape() + " ");

            if (i == 4) {
                System.out.printf(WHITE + " ]");
            }
        }
    }

    public static void getCandyToStackOne() {
        if (candyQueue.isEmpty()) {
            return;
        }

        Candy candy = candyQueue.dequeue();
        stackOne.push(candy);

        printStacks(createStackArray());
    }

    public static void pushPopBetweenStacks(int popStack, int pushStack) {
        Candy popCandy = null;
        if (popStack == 1) {
            popCandy = stackOne.pop();
        } else if (popStack == 2) {
            popCandy = stackTwo.pop();
        } else if (popStack == 3) {
            popCandy = stackThree.pop();
        }


        if (pushStack == 1) {
            stackOne.push(popCandy);
        } else if (pushStack == 2) {
            stackTwo.push(popCandy);
        } else if (pushStack == 3) {
            stackThree.push(popCandy);
        }

        printStacks(createStackArray());
    }

    public static int crush(Stack tobeCrushedStack) {

        Candy candy1, candy2;

        candy1 = (Candy) tobeCrushedStack.pop();
        candy2 = (Candy) tobeCrushedStack.pop();

        if (checker(candy1,candy2)){
            totalPoint = calculateCrushPoint(candy1, candy2);
            candy1 = candy2;

        }else {
            tobeCrushedStack.push(candy1);
            tobeCrushedStack.push(candy2);
            return 0;
        }

        while (!tobeCrushedStack.isEmpty()) {

            candy2 = (Candy) tobeCrushedStack.pop();
            boolean shouldCrush = checker(candy1, candy2);

            if (shouldCrush) {
                totalPoint += calculateCrushPoint(candy1, candy2);
                candy1 = candy2;
            } else {
                tobeCrushedStack.push(candy2);
                break;
            }

        }
        System.out.println("Total Point:" + totalPoint);
        printStacks(createStackArray());
        return totalPoint;
    }

    public static boolean checker(Candy candyOne, Candy candyTwo) {
        return candyOne.canCrush(candyTwo);
    }

    private static int calculateCrushPoint(Candy candy1, Candy candy2) {
        if (candy1.equals(candy2)) {
            return 2;
        }
        return 1;
    }

    public static int remainingCandyPointInStacks() {
        return 0;
    }


    public static void getUserInput() {
        Scanner reader = new Scanner(System.in);
        System.out.println(WHITE + "Enter a command('pp12, pp13, pp21, pp23, pp31, pp32, crush1, crush2, crush3, getCandy'):");
        String command = reader.next();

        System.out.println(WHITE + "your command:" + command);

        switch (command) {
            case "pp12":
                pushPopBetweenStacks(1, 2);
                break;
            case "pp13":
                pushPopBetweenStacks(1, 3);
                break;
            case "pp21":
                pushPopBetweenStacks(2, 1);
                break;
            case "pp23":
                pushPopBetweenStacks(2, 3);
                break;
            case "pp31":
                pushPopBetweenStacks(3, 1);
                break;
            case "pp32":
                pushPopBetweenStacks(3, 2);
                break;
            case "crush1":
                crush(stackOne);
                break;
            case "crush2":
                crush(stackTwo);
                break;
            case "crush3":
                crush(stackThree);
                break;
            case "getCandy":
                getCandyToStackOne();
                break;
            case "stop":
                remainingCandyPointInStacks();
                break;
            default:
                System.out.println(RED + "Try again, please ! ");

        }

    }

}
