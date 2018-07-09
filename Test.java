import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class Test {
    private final int buyCarMonth;//上牌时间 月份
    private final int currYear; //当前年份
    private final int currMonth;//当前月份
    private int diffYear;//差值年份

    public static void main(String[] args) {

        //传入上牌时间
        Test o = new Test(1999, 1);

        System.out.println(Arrays.toString(o.getTime()[0]));// 年检有效期
        System.out.println(Arrays.toString(o.getTime()[1]));//  保险有效期

    }

    /**
     *
     * @param buyCarYear 上牌年份
     * @param buyCarMonth 上牌月份
     */
    public Test(int buyCarYear, int buyCarMonth) {
        this.buyCarMonth = buyCarMonth;
        Calendar c = Calendar.getInstance();//获取时间
        this.currYear = c.get(Calendar.YEAR);
        this.currMonth = c.get(Calendar.MONTH);
        this.diffYear = this.currYear - buyCarYear;
    }

    //年检有效期计算
    private int[] initExamineExpireDate(int year, int month) {

        if (year == this.currYear && month < this.currMonth) {
            if (this.diffYear <= 6) {
                year = year + 2;
            } else {
                if (diffYear > 6 && diffYear <= 15) {
                    year = year + 1;
                } else {
                    month = month + 6;
                    if (month > 12) {
                        month = month - 12;
                        year = year + 1;
                    }
                }
            }
        }
        int[] result = new int[2];
        result[0] = year;
        result[1] = month;
        return result;
    }


    //保险有效期计算
    private int[] initInsuranceExpireDate(int year, int month) {
        if (year == currYear && month < currMonth) {
            if (diffYear <= 6) {
                year = year + 2;
            } else {
                year = year + 1;
            }
        }
        int[] result = new int[2];
        result[0] = year;
        result[1] = month;
        return result;
    }


    public int[][] getTime() {
        int[] ExamineExpireDate = new int[2];
        int[] InsuranceExpireDate = new int[2];

        if (diffYear == 0) {
            ExamineExpireDate = this.initExamineExpireDate(currYear + 2, buyCarMonth);
            InsuranceExpireDate = this.initInsuranceExpireDate(currYear + 1, buyCarMonth);
        } else {
            if (diffYear == 1 || diffYear == 3 || diffYear == 5) {

                ExamineExpireDate = this.initExamineExpireDate(currYear + 1, buyCarMonth);
                InsuranceExpireDate = this.initInsuranceExpireDate(currYear, buyCarMonth);

            } else {
                if (diffYear == 2 || diffYear == 4 || diffYear >= 6) {
                    ExamineExpireDate = this.initExamineExpireDate(currYear, buyCarMonth);
                    InsuranceExpireDate = this.initInsuranceExpireDate(currYear, buyCarMonth);
                }
            }
        }
        return new int[][]{ExamineExpireDate, InsuranceExpireDate};
    }
}
