package workout.calendar.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import workout.calendar.domain.entity.ScheduleEntity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

//@Getter
@NoArgsConstructor
//@ToString
@Data
public class Date {

    private String year = "";
    private String month = "";
    private String date = "";
    private String value = "";

    private String startDate = "";
    private String endDate = "";

    private ScheduleEntity[] schedules = new ScheduleEntity[4];

    public Map<String, Integer> todayInfo(Date date) {
        Map<String, Integer> todayDate = new HashMap<String, Integer>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(date.getYear()), Integer.parseInt(date.getMonth()), 1);
        int startDay = calendar.getMinimum(Calendar.DATE);
        int endDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int start = calendar.get(Calendar.DAY_OF_WEEK);

        Calendar todayCal = Calendar.getInstance();
        SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
        SimpleDateFormat msdf = new SimpleDateFormat("M");
        int todayYear = Integer.parseInt(ysdf.format(todayCal.getTime()));
        int todayMonth = Integer.parseInt(msdf.format(todayCal.getTime()));
        int searchYear = Integer.parseInt(date.getYear());
        int searchMonth = Integer.parseInt(date.getMonth()) + 1;
        int today = -1;

        if (todayYear == searchYear && todayMonth == searchMonth) {
            SimpleDateFormat dsdf = new SimpleDateFormat("dd");
            today = Integer.parseInt(dsdf.format(todayCal.getTime()));
        }

        searchMonth = searchMonth - 1;
        Map<String, Integer> before_after_calendar = before_after_calendar(searchYear, searchMonth);
        todayDate.put("start", start);
        todayDate.put("startDay", startDay);
        todayDate.put("endDay", endDay);
        todayDate.put("today", today);
        todayDate.put("searchYear", searchYear);
        todayDate.put("searchMonth", searchMonth);
        todayDate.put("beforeYear", before_after_calendar.get("beforeYear"));
        todayDate.put("beforeMonth", before_after_calendar.get("beforeMonth"));
        todayDate.put("afterYear", before_after_calendar.get("afterYear"));
        todayDate.put("afterMonth", before_after_calendar.get("afterMonth"));

        this.startDate = String.valueOf(searchYear)
                + "-" + String.valueOf(searchMonth + 1) + "-" + String.valueOf(startDay);
        this.endDate = String.valueOf(searchYear)
                + "-" + String.valueOf(searchMonth + 1) + "-" + String.valueOf(endDay);

        return todayDate;
    }

    private Map<String, Integer> before_after_calendar(int searchYear, int searchMonth) {
        Map<String, Integer> beforeAfterData = new HashMap<String, Integer>();
        int beforeYear = searchYear;
        int beforeMonth = searchMonth - 1;
        int afterYear = searchYear;
        int afterMonth = searchMonth + 1;

        if (beforeMonth < 0) {
            beforeMonth = 11;
            beforeYear = searchYear - 1;
        }
        if (afterMonth > 11) {
            afterMonth = 0;
            afterYear = searchYear + 1;
        }

        beforeAfterData.put("beforeYear", beforeYear);
        beforeAfterData.put("beforeMonth", beforeMonth);
        beforeAfterData.put("afterYear", afterYear);
        beforeAfterData.put("afterMonth", afterMonth);

        return beforeAfterData;
    }

    public Date(String year, String month, String date, String value, ScheduleEntity[] schedules) {
        if ((month != null && month != "") && (date != null && date != "")) {
            this.year = year;
            this.month = month;
            this.date = date;
            this.value = value;
            this.schedules = schedules;
        }
    }

}
