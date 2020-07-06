public class JavaMain {

    public static void main(String[] args) {
        double[] lpr = new double[]{0.049, 0.049, 0.049, 0.049,
                0.0475, 0.0465, 0.0465, 0.0455, 0.0475, 0.0485, 0.0495, 0.0485, 0.0495, 0.0505, 0.051, 0.052,
                0.051, 0.050, 0.049, 0.050, 0.051, 0.049, 0.049, 0.049, 0.049, 0.049, 0.049, 0.049, 0.049, 0.049};
        double zonge = dengebenxi(1690000, lpr);
        System.out.println(zonge);
        // 基准总额 3228941
        System.out.println("相比之前 多还款：" + (zonge - 3228941));
    }

    /**
     * @描述     等额本息还款 lpr浮动计算器
     * @参数     [benjin：本金总额, lpr：lpr变化数组]
     * @返回值   double
     * @创建人   SunJQ
     * @创建时间 2020/7/6
     */
    public static double dengebenxi(double benjin, double[] lpr){
        int years = lpr.length;
        double sum = 0.0;
        double left = benjin;
        for(int i = 0; i < years; i++){
            // 每一年的还款金额
            double thisyear_money = thisyear(left, lpr[i] / 12, (years - i) * 12);
            System.out.println(String.format("year[%d], money:%f", i + 2017, thisyear_money));
            sum += thisyear_money * 12;
            for(int j = 0; j < 12; j++){
                // 每个月的利率和本金
                double thismonth_lilv = thisMonthLiLv(left, lpr[i] / 12);
                left = left - (thisyear_money - thismonth_lilv);
            }
        }
        return sum;
    }

    private static double thisyear(double benjin_left, double newLpr, int month){
        return benjin_left*newLpr*Math.pow(1+newLpr, month) / (Math.pow(1+newLpr, month) - 1);
    }

    private static double thisMonthLiLv(double left, double newLpr){
        return left*newLpr;
    }
}
