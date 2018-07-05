/**
 * 大陆地区根据车辆上牌时间 预估交强险/年检 到期时间
 * @date: 180705
 * @来源：易车网车源上传页面中
 * @url：http://dealer.taoche.com/Scripts/SellCar/initfunction.min.js?a=26&v=20180730
 *
 * @param buyCarYear  上牌年份
 * @param buyCarMonth 上牌月份
 * @returns {{ExamineExpireDate: *, InsuranceExpireDate: *}}
 * ExamineExpireDate：年检到期时间
 * InsuranceExpireDate：交强险到期时间
 */
;(function (buyCarYear, buyCarMonth) {
//        //上牌年份
// var buyCarYear = '2016';

// //上牌月份
// var buyCarMonth = '01';

    if (parseInt(buyCarYear) > 0 && parseInt(buyCarMonth) > 0) {
        var currDate = new Date();//当前时间
        var currYear = currDate.getFullYear();
        var currMonth = currDate.getMonth() + 1;
        var buyCarMonth = parseInt(buyCarMonth);
        var diffYear = parseInt(currYear) - parseInt(buyCarYear);


//年检有效期计算
        initExamineExpireDate = function (year, month) {
            if (year == currYear && month < currMonth) {
                if (diffYear <= 6) {
                    year = parseInt(year) + 2
                } else {
                    if (diffYear > 6 && diffYear <= 15) {
                        year = parseInt(year) + 1
                    } else {
                        month = parseInt(month) + 6;
                        if (month > 12) {
                            month = parseInt(month) - 12;
                            year = parseInt(year) + 1
                        }
                    }
                }
            }
            return [year, month];
        }

        //保险有效期计算
        initInsuranceExpireDate = function (year, month) {
            if (year == currYear && month < currMonth) {
                if (diffYear <= 6) {
                    year = parseInt(year) + 2
                } else {
                    year = parseInt(year) + 1
                }
            }

            return [year, month];
        }


        if (diffYear == 0) {
            return {
                'ExamineExpireDate': initExamineExpireDate(currYear + 2, buyCarMonth),
                'InsuranceExpireDate': initInsuranceExpireDate(currYear + 1, buyCarMonth),
            }
        } else {
            if (diffYear == 1 || diffYear == 3 || diffYear == 5) {


                return {
                    'ExamineExpireDate': initExamineExpireDate(currYear + 1, buyCarMonth),
                    'InsuranceExpireDate': initInsuranceExpireDate(currYear, buyCarMonth),
                };

            } else {
                if (diffYear == 2 || diffYear == 4 || diffYear >= 6) {


                    return {
                        'ExamineExpireDate': initExamineExpireDate(currYear, buyCarMonth),
                        'InsuranceExpireDate': initInsuranceExpireDate(currYear, buyCarMonth),
                    };
                }
            }
        }
    }
})(2016,1)

