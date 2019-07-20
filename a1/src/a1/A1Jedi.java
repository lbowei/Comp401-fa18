package a1;
import java.util.Scanner;

public class A1Jedi{

	public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);		
	
	int num_of_asm = scanner.nextInt();				
	int sum = 0;	
	
	for(int i=0; i<num_of_asm; i++) {
		int asm_worth = scanner.nextInt();			
		sum += asm_worth;							
	}
	
	int pgrade_max = scanner.nextInt();				
	int num_of_stu = scanner.nextInt();				
	
	double[] mg = new double[num_of_stu];			
	double[] fg = new double[num_of_stu];			
	double[] pgrade = new double[num_of_stu];		
	double[] agrade = new double[num_of_stu];		
	
	double mid_sum=0;
	double final_sum=0;
	for(int m=0;m<num_of_stu; m++)
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
		
		mg[m] = mid_grade;
		fg[m] = final_grade;
		mid_sum += mg[m];						
		final_sum += fg[m];					
	    
	
		pgrade[m] = process_pgrade(pgrade_raw, pgrade_max);		
		agrade[m] = process_agrade(a_raw_sum, sum);				
	}
	
	double mid_avg = mid_sum/num_of_stu;				
	double final_avg = final_sum/num_of_stu;			
	double sum_a = 0;
	double sum_b = 0;
	
	for (int n=0; n<num_of_stu; n++) {
		double a = (mg[n]-mid_avg)*(mg[n]-mid_avg);		 
		double b = (fg[n]-final_avg)*(fg[n]-final_avg); 
		sum_a += a;
		sum_b += b;
	}
	
	double stdDev_mid = Math.sqrt(sum_a/num_of_stu);		
	double stdDev_final = Math.sqrt(sum_b/num_of_stu);	
	
	
	double[] nor_mid = new double [num_of_stu];
	double[] nor_final = new double [num_of_stu];
	double[] cur_mid = new double [num_of_stu];
	double[] cur_final = new double[num_of_stu];
	
	for (int q=0; q<num_of_stu; q++) {
		nor_mid[q] = (mg[q]-mid_avg)/stdDev_mid;
		nor_final[q] = (fg[q]-final_avg)/stdDev_final;
		cur_mid[q] = curve_grade(nor_mid[q]);				
		cur_final[q] = curve_grade(nor_final[q]);			
	}
	
	
	double[] grade = new double [num_of_stu];
	String[] letter_grade = new String[num_of_stu];
	int A=0, Am=0, Bp=0, B=0, Bm=0, Cp=0, C=0, Cm=0, Dp=0, D=0, F=0;
	for(int x=0;x<num_of_stu; x++) {
		grade[x] = process_grade(pgrade[x], agrade[x], cur_mid[x], cur_final[x]);
		letter_grade[x] = letter(grade[x]);
		if (letter_grade[x].equals("A")) {
			A++;
		}else if (letter_grade[x].equals("A-")) {
			Am++;
		}else if (letter_grade[x].equals("B+")) {
			Bp++;
		}else if (letter_grade[x].equals("B")) {
			B++;
		}else if (letter_grade[x].equals("B-")) {
			Bm++;
		}else if (letter_grade[x].equals("C+")) {
			Cp++;
		}else if (letter_grade[x].equals("C")) {
			C++;
		}else if (letter_grade[x].equals("C-")) {
			Cm++;
		}else if (letter_grade[x].equals("D+")) {
			Dp++   ;
		}else if (letter_grade[x].equals("D")) {
			D++;
		}else {
			F++;
	}
	}
	
	
	System.out.println("A :"+ " "+ A);
	System.out.println("A-:"+ " "+ Am);
	System.out.println("B+:"+ " "+ Bp);
	System.out.println("B :"+ " "+ B);
	System.out.println("B-:"+ " "+ Bm);
	System.out.println("C+:"+ " "+ Cp);
	System.out.println("C :"+ " "+ C);
	System.out.println("C-:"+ " "+ Cm);
	System.out.println("D+:"+ " "+ Dp);
	System.out.println("D :"+ " "+ D);
	System.out.println("F :"+ " "+ F);

	
	

	
	
		
	}
	
	
	public static double curve_grade (double nor_grade) {
		if(nor_grade>=2.0) {
			return nor_grade;
		}else if(nor_grade>1.0) {
			return ((nor_grade-1.0)/(2.0-1.0)*(100-94)+94);
		}else if(nor_grade>0.0) {
			return ((nor_grade-0.0)/(1.0-0.0)*(94-85)+85);
		}else if(nor_grade>(-1.0)) {
			return ((nor_grade-(-1.0))/(0.0-(-1.0))*(85-75)+75);		
		}else if(nor_grade>(-1.5)) {
			return ((nor_grade-(-1.5))/((-1.0)-(-1.5))*(75-65)+65);
		}else if(nor_grade>(-2.0)) {
			return ((nor_grade-(-2.0))/((-1.5)-(-2.0))*(65-55)+55);
		}else if(nor_grade>(-3.0)) {
			return ((nor_grade-(-3.0))/((-2.0)-(-3.0))*(55-30)+30);
		}else if(nor_grade>(-4.0)) {
			return ((nor_grade-(-4.0))/((-3.0)-(-4.0))*(30-0)+0);
		}else return 0;	
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
	public static double process_grade(double pgrade, double agrade, double cur_mid, double cur_final) {
		double wa =  (40*agrade) + (15*pgrade) + (0.2*cur_mid) + (0.25*cur_final);
		return wa;
	}
	public static String letter (double grade) {
		if (grade>=94) {
			return "A";
		}else if (grade>=90) {
			return "A-";
		}else if (grade>=86) {
			return "B+";
		}else if (grade>=83) {
			return "B";
		}else if (grade>=80) {
			return "B-";
		}else if (grade>76) {
			return "C+";
		}else if (grade>73) {
			return "C";
		}else if (grade>70) {
			return "C-";
		}else if (grade>65) {
			return "D+";
		}else if (grade>60) {
			return "D";
		}else {
			return "F";
			//some changes
		}
	
}
}
