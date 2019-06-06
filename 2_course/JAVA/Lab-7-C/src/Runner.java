public class Runner {

    public static void main(String[ ] args) {
       function();
    }


    public static void function(){
        String input1 = "diteteststma";
        String input2 = "roseesorroser";
        String input3 = "poseroseesrr";
        MaxSubstrWithEqualsFirstLastChar maxSubstr1 = new MaxSubstrWithEqualsFirstLastChar(input1);
        MaxSubstrWithEqualsFirstLastChar maxSubstr2 = new MaxSubstrWithEqualsFirstLastChar(input2);
        MaxSubstrWithEqualsFirstLastChar maxSubstr3 = new MaxSubstrWithEqualsFirstLastChar(input3);
        System.out.println("Inputing strokes: " + input1 + ", " + input2 + ", " + input3);
        System.out.println("Result after deleting: " + maxSubstr1.deleteMaxSubstring()
                + ", " + maxSubstr2.deleteMaxSubstring() + ", " +
                maxSubstr3.deleteMaxSubstring());
    }


}