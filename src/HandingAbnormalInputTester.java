public class HandingAbnormalInputTester {
    public static void main(String[] args) {
        HandingAbnormalInput checker = new HandingAbnormalInput();
        String str1 = "1";
        String str2 = "99";
        String str3 = "7";
        String str4 = "yunzhe";
        String str5 = "&";
        String str6 = "1s@";
        String str7 = " 5070";
        System.out.println(checker.checkAbnormalInput(str7, "0", "6"));


    }
}
