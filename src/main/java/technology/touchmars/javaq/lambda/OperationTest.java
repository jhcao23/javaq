package technology.touchmars.javaq.lambda;

public class OperationTest {
    public static void main(String[] args){
        //plus5 is an expressive static helper method that adds 5 to a given number
        Operation<Integer> calc = (x) -> Operation.plus5(2);

        Operation complexOp = calc.add(3) // x -> 7+3=10
                .multiply(4) // x -> 10*4=40
                .multiply(2) // x -> 40*2=80
                .multiply(2) // x -> 80*2=160
                .add(4); // x -> 160+4 = 164

        complexOp.print();

        int result = complexOp.getResult();
    }
}
