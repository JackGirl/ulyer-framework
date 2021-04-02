package cn.ulyer.core.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

    public static void main(String[] args) {
       String s =  "01P(1-,30,40-67,099)";
       Pattern zero_patter = Pattern.compile("0\\d");
       Matcher matcher = zero_patter.matcher(s);
       while (matcher.find()){
            String picked = matcher.group();
           s = s.replace(picked,picked.replaceFirst("0",""));
       }
        System.out.println(s);
       Pattern regPattern = Pattern.compile("[(\\d{1,2}\\-\\d{1,2})|(\\d{1,2})](,\\d+)*$");
       Matcher m2 = regPattern.matcher(s);
       while (m2.find()){
           System.out.println("find");
           System.out.println(m2.group());
       }

        System.out.println("===============================");
        Pattern pattern = Pattern.compile("\\d{1,2}\\-\\d{1,2}");
        Matcher m = pattern.matcher("12-12");
        while (m.find()){
            System.out.println("find");
            System.out.println(m.group());
        }
    }





}
