import com.google.gson.Gson;
import datas.MealData;
import datas.MealRequest;
import datas.School;
import datas.SchoolRequest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainClass {

    public static Scanner sc = new Scanner(System.in);
    public static String location;
    public static String cookie = "";

    public static void main(String[] args) throws Exception {
        String edu_location = getEduLocation();
        String cookie = getCookie(edu_location);
        List<School> list = SchoolSearch(cookie, edu_location);
        School school = SchoolSelect(list);
        MealData mealData = getSchoolMeal(school, edu_location, cookie);

        for (int i = 0; i < mealData.getDate().size(); i++) {
            System.out.println(mealData.getDate().get(i) +  "\n" + mealData.getFood().get(i));
        }
    }

    public static String getEduLocation() throws Exception {
        System.out.println("*** 학교 지역 선택 ***");
        System.out.print("검색할 학교 지역을 입력하세요. (시/도) ");
        String edu_location = sc.nextLine();
        System.out.println(edu_location + " 입력되었습니다.\n");

        if (edu_location.equals("서울") || edu_location.equals("서울특별시")) return location = Constants.SEOUL;
        else if (edu_location.equals("부산") || edu_location.equals("부산광역시")) return location = Constants.BUSAN;
        else if (edu_location.equals("울산") || edu_location.equals("울산광역시")) return location = Constants.ULSAN;
        else if (edu_location.equals("대구") || edu_location.equals("대구광역시")) return location = Constants.DAEGU;
        else if (edu_location.equals("대전") || edu_location.equals("대전광역시")) return location = Constants.DAEJEON;
        else if (edu_location.equals("광주") || edu_location.equals("광주광역시")) return location = Constants.GWANGJU;
        else if (edu_location.equals("세종") || edu_location.equals("세종특별자치시")) return location = Constants.SEJONG;
        else if (edu_location.equals("제주") || edu_location.equals("제주도")) return location = Constants.JEJU;
        else if (edu_location.equals("강원") || edu_location.equals("강원도")) return location = Constants.KWANGWON;
        else if (edu_location.equals("경기") || edu_location.equals("경기도")) return location = Constants.GYEONGGI;
        else if (edu_location.equals("충북") || edu_location.equals("충청북도")) return location = Constants.CHUNGBUK;
        else if (edu_location.equals("충남") || edu_location.equals("충청남도")) return location = Constants.CHUNGNAM;
        else if (edu_location.equals("전남") || edu_location.equals("전라남도")) return location = Constants.JEONNAM;
        else if (edu_location.equals("전북") || edu_location.equals("전라북도")) return location = Constants.JEONBUK;
        else if (edu_location.equals("경남") || edu_location.equals("경상남도")) return location = Constants.GYEONGNAM;
        else if (edu_location.equals("경북") || edu_location.equals("경상북도")) return location = Constants.GYEONGBUK;
        else return location = null;
    }

    public static String getCookie(String location) throws Exception {
        URL url = new URL(Constants.DEFAULT_URL + location + Constants.DEFAULT_JSP);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();

        Map<String, List<String>> header = http.getHeaderFields();
        if (header.containsKey("Set-Cookie")) {
            List<String> cookies = header.get("Set-Cookie");
            for (int i = 0; i < cookies.size(); i++) {
                cookie += cookies.get(i);
                return cookie;
            }
        }
        return null;
    }

    public static ArrayList<School> SchoolSearch(String cookie, String location) throws Exception {
        System.out.println("*** 학교 이름 검색 ***");
        System.out.print("검색할 학교 이름을 입력하세요. ");
        String search = sc.nextLine();
        System.out.println(search + " 입력되었습니다.\n");

        SchoolRequest request = new SchoolRequest().setKraOrgNm(search);

        URL url = new URL(Constants.DEFAULT_URL + location + Constants.SCHOOL_SEARCH);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setDefaultUseCaches(false);
        http.setDoInput(true);
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json");
        http.setRequestProperty("Cookie", cookie);
        http.setRequestMethod("POST");

        OutputStreamWriter os = new OutputStreamWriter(http.getOutputStream(), StandardCharsets.UTF_8);
        PrintWriter writer = new PrintWriter(os);
        writer.write(new Gson().toJson(request));
        writer.flush();

        InputStreamReader is = new InputStreamReader(http.getInputStream(), StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(is);
        StringBuilder builder = new StringBuilder();
        String tmp;

        while ((tmp = reader.readLine()) != null) {
            builder.append(tmp);
        }

        String result = builder.toString();
        JSONObject jsonObject = new JSONObject(result);
        JSONObject resultSVO = jsonObject.getJSONObject("resultSVO");
        JSONArray orgDVOList = resultSVO.getJSONArray("orgDVOList");

        ArrayList<School> list = new ArrayList<>();

        System.out.println("*** 학교 이름 검색 결과 ***");
        for (int i = 0; i < orgDVOList.length(); i++) {
            JSONObject j = orgDVOList.getJSONObject(i);
            JSONObject data = j.getJSONObject("data");

            String kraOrgNm = data.getString("kraOrgNm");
            String zipAdres = data.getString("zipAdres");
            String schulCode = data.getString("orgCode");
            String schulKndScCode = data.getString("schulKndScCode");
            String schulCrseScCode = data.getString("schulCrseScCode");

            School school = new School().setKraOrgNm(kraOrgNm)
                    .setZipAdres(zipAdres)
                    .setSchulCode(schulCode)
                    .setSchulKndScCode(schulKndScCode)
                    .setSchulCrseScCode(schulCrseScCode);

            list.add(school);
        }
        return list;
    }

    public static School SchoolSelect(List<School> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i).getKraOrgNm() + " " + list.get(i).getZipAdres());
        }

        System.out.print("\n번호를 선택하세요. ");
        int num = sc.nextInt();
        School school = list.get(num - 1);
        System.out.println(school.getKraOrgNm() + " 선택되었습니다.\n");

        return school;
    }

    public static MealData getSchoolMeal(School school, String location, String cookie) throws Exception {
        URL url = new URL(Constants.DEFAULT_URL + location + Constants.SCHOOL_MEAL);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setDefaultUseCaches(false);
        http.setDoInput(true);
        http.setDoOutput(true);
        http.setRequestMethod("POST");
        http.setRequestProperty("Content-Type", "application/json");
        http.setRequestProperty("Cookie", cookie);

        MealRequest request = new MealRequest().setInsttNm(school.getKraOrgNm())
                .setSchulCode(school.getSchulCode())
                .setSchYmd(new SimpleDateFormat("yyyy.MM.dd").format(System.currentTimeMillis()))
                .setSchMmealScCode("2")
                .setSchulKndScCode(school.getSchulKndScCode())
                .setSchulCrseScCode(school.getSchulCrseScCode());

        OutputStreamWriter os = new OutputStreamWriter(http.getOutputStream(), "utf-8");
        PrintWriter writer = new PrintWriter(os);
        writer.write(new Gson().toJson(request));
        writer.flush();

        InputStreamReader is = new InputStreamReader(http.getInputStream(), "utf-8");
        BufferedReader reader = new BufferedReader(is);
        StringBuilder builder = new StringBuilder();
        String tmp;

        while ((tmp = reader.readLine()) != null) {
            builder.append(tmp);
        }

        String result = builder.toString();
        JSONObject j = new JSONObject(result);
        JSONObject resultSVO = j.getJSONObject("resultSVO");
        JSONArray weekDietList = resultSVO.getJSONArray("weekDietList");

        ArrayList<String> date = new ArrayList<>();
        ArrayList<String> meal = new ArrayList<>();

        JSONObject mealDates = weekDietList.getJSONObject(0);
        date.add(mealDates.getString("sun"));
        date.add(mealDates.getString("mon"));
        date.add(mealDates.getString("tue"));
        date.add(mealDates.getString("wed"));
        date.add(mealDates.getString("the"));
        date.add(mealDates.getString("fri"));
        date.add(mealDates.getString("sat"));

        JSONObject mealFoods = weekDietList.getJSONObject(2);
        meal.add(replaceMeal(mealFoods.getString("sun")));
        meal.add(replaceMeal(mealFoods.getString("mon")));
        meal.add(replaceMeal(mealFoods.getString("tue")));
        meal.add(replaceMeal(mealFoods.getString("wed")));
        meal.add(replaceMeal(mealFoods.getString("the")));
        meal.add(replaceMeal(mealFoods.getString("fri")));
        meal.add(replaceMeal(mealFoods.getString("sat")));

        MealData mealData = new MealData().setDate(date).setFood(meal);
        return mealData;
    }

    public static String replaceMeal(String meal) {
        return meal.replace("<br />", "\n");
    }
}
