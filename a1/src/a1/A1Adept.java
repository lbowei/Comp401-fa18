package a1;
import java.util.Scanner;

public class A1Adept {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int num_of_asm = scanner.nextInt();				
		int sum = 0;
		for(int i=0; i<num_of_asm; i++) {
			int asm_worth = scanner.nextInt();
			sum = asm_worth+ sum;						
		}
		
		int pgrade_max = scanner.nextInt();
		int num_of_stu = scanner.nextInt();
		
		String[] result = new String[num_of_stu];
		for(int m=0; m<num_of_stu; m++)
		{
			String f_name = scanner.next();
			String l_name = scanner.next();	
			int pgrade_raw = scanner.nextInt();			
		
			double a_raw_sum = 0;
			for(int n=0; n<num_of_asm; n++) {
			double asm_grade = scanner.nextDouble();
			a_raw_sum += asm_grade;						
			}
			
			double mid_grade = scanner.nextDouble();
			double final_grade = scanner.nextDouble();	
			
			double pgrade = process_pgrade(pgrade_raw, pgrade_max);
			
			double agrade = process_agrade(a_raw_sum, sum)   ;
					
					
					
			double WAv = weighted_average(agrade, pgrade, mid_grade, final_grade); 
			String letter_grade = process_grade (WAv);
			char f_initial = f_name.charAt(0);
			result[m] = (f_initial + ". " + l_name + " " + letter_grade);		

		}	
		for(int n=0; n<result.length; n++) {
			System.out.println(result[n]);  
		}
	
		
	}
	public static double process_pgrade(double raw, int max) {
		double pg =  (raw/(max*0.8));
		if (pg>=1) {
			return 1;
		}else {
			return pg;
		}	
	}
	
	public static double process_agrade(double raw, int max) {
		double ag = raw/max;
		return ag;
	}
	public static double weighted_average(double agrade, double pgrade, double mid_grade, double final_grade) {
	double wa = (40*agrade) + (15*pgrade) + (0.2*mid_grade) + (0.25*final_grade);
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
	}}
	
	
	
	
	
	
	
	
	
	
	
	
	
