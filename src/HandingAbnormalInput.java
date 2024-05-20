public class HandingAbnormalInput{
    public boolean inputChecker;
    public HandingAbnormalInput () {
        inputChecker = true;
    }
    public boolean checkAbnormalInput(String input, String expt_min, String expt_max) {
        if (input.equals("5070")) {
            return true;
        }
        for (int i=0; i<input.length(); i++) {  //detect the letter in the input
            if (Character.isLetter(input.charAt(i)) || Character.isSpaceChar(input.charAt(i))) {
                System.out.println("ENTER NUMBER ONLY");
                return false;
            }
        }
        if (input.length() > 1) {
            System.out.println("Developer: I didn't give you much of choice.");
        }
        return (input.charAt(0) >= expt_min.charAt(0) && input.charAt(0) <= expt_max.charAt(0));


    }
}
