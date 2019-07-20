package a1;
import java.util.Scanner;

public class A1Novice {

	public static void main(String[] args) {
	 Scanner scanner = new Scanner(System.in);
	
	 int num_of_stu = scanner.nextInt();
	 String[] result = new String[num_of_stu];

	 
	 
	 for(int i=0; i<num_of_stu; i++)
	 {
		 String f_name = scanner.next();
		 String l_name = scanner.next();	 
		 double agrade = scanner.nextDouble();
		 double pgrade = scanner.nextDouble();
		 double mid_grade = scanner.nextDouble();
		 double final_grade = scanner.nextDouble();
		
		 double WAv = weighted_average(agrade, pgrade, mid_grade, final_grade); 
		 String letter_grade = process_grade (WAv);
		 char f_initial = f_name.charAt(0);
		 
		 result[i] = (f_initial + ". " + l_name + " " + letter_grade);		
	}
	 
	for (int n=0; n<result.length; n++) {
		System.out.println(result[n]);
	}
		}//sfsdf
	
	
	public static double weighted_average(double agrade, double pgrade, double mid_grade, double final_grade) {
		double wa = (0.4*agrade) + (0.15*pgrade) +(0.2*mid_grade) + (0.25*final_grade);	
		return wa;
	}
	
	public static String process_grade (double WAv) {
		if (WAv>=94) {
			return "A";
		}else if (WAv>=90) {
			return "A-";
		}else if (WAv>=86) {
			return "B+";
		}else if (WAv>=83) {
			return "B";
		}else if (WAv>=80) {
			return "B-";
		}else if (WAv>76) {
			return "C+";
		}else if (WAv>73) {
			return "C";
		}else if (WAv>70) {
			return "C-";
		}else if (WAv>65) {
			return "D+";
		}else if (WAv>60) {
			return "D";
		}else {
			return "F";
		}
		
	}
			}
	
	
