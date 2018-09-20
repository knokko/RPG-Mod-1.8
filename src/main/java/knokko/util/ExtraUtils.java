package knokko.util;

import net.minecraft.util.Vec3;

public class ExtraUtils {
	
	public static int fromDouble(double d){
		double t = d - (int)d;
		int a;
		if(t >= 0.5){
			a  = 1;
		}
		else {
			a = 0;
		}
		return (int)d + a;
	}
	
	public static double divineAccurate(double d, double d2){
		double x = d * 100000000;
		double y = d2 * 100000000;
		return x / y;
	}
	
	public static boolean isBetween(double d, double t1, double t2){
		double min = Math.min(t1, t2);
		double max = Math.max(t1, t2);
		return d >= t1 && d <= t2;
	}
	
	public static double max(double... d){
		double e = -Double.MAX_VALUE;
		int times = 0;
		while(times < d.length){
			double f = d[times];
			if(f > e){
				e = f;
			}
			++times;
		}
		return e;
	}
	
	public static boolean isNormalNumber(double d){
		String s = d + "";
		return !s.contains("NaN") && !s.contains("Infinity");
	}
}
