import java.time.*;

public class Utility {
	public static final int SCREEN_WIDTH = 656;
	public static final int SCREEN_HEIGHT = 519;
	public static double time_since_start() {
		LocalTime localtime = LocalTime.now();
		
		return (double)(localtime.getHour()*3600) + (double)(localtime.getMinute()*60) + (double)(localtime.getSecond()) + (double)(localtime.getNano())/1000000000;
	}
}