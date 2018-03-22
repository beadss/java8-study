package beadss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import beadss.lotto.NumberGenerator;

public class Main {
	
	
	private static int price = 1000;
	
	public static void main(String[] args) {
		NumberGenerator generator = new NumberGenerator();
		
		Scanner scan = new Scanner(System.in);
        
        System.out.println("���Աݾ��� �Է��� �ּ���.");
        
        int amount = scan.nextInt();
        
        Vector<List<Integer>> lottoList = new Vector<List<Integer>>(amount/price); 
        
        for(int i = 0; i < amount/price; i++) {
        	List<Integer> lotto = new ArrayList<Integer>();
        	lottoList.add(lotto);
        	
        	for(int j = 0; j < 6; j++) {
        		lotto.add(generator.generate());
        	}
        	
        	System.out.println(lotto);
        }
        
        
        System.out.println("���� �� ��÷ ��ȣ�� �Է��� �ּ���.");
        scan.nextLine();
        String lottoExpectString = scan.nextLine();
       
        List<Integer> expectedLotto = new ArrayList<Integer>();
        
        Arrays.asList(lottoExpectString.split(","))
        .subList(0, 6)
        .stream()
        .forEach((numberStr)->{expectedLotto.add(Integer.parseInt(numberStr.trim()));});
        
        int threeCount = 0;
        int fourCount = 0;
        int fiveCount = 0;
        int sixCount = 0;        
        
        for(List<Integer> lotto : lottoList) {
        	int matchCount = 0;
        	for(Integer number : lotto) {
        		boolean isMatched = false;
        		for(Integer expectedNumber : expectedLotto) {
            		if(expectedNumber == number) {
            			isMatched = true;
            			break;
            		}
            	}
        		if(isMatched) {
        			matchCount++;
        		}
        	}
        	if(matchCount == 3) {
        		threeCount++;
        	} else if(matchCount == 4) {
        		fourCount++;
        	} else if(matchCount == 5) {
        		fiveCount++;
        	} else if(matchCount == 6) {
        		sixCount++;
        	}
        }
        
        System.out.println("��÷ ���");
        System.out.println("---------");
        System.out.println(String.format("3�� ��ġ (5000��)- %d��", threeCount));
        System.out.println(String.format("4�� ��ġ (50000��)- %d��", fourCount));
        System.out.println(String.format("5�� ��ġ (150000��)- %d��", fiveCount));
        System.out.println(String.format("6�� ��ġ (2000000000��)- %d��", sixCount));
        System.out.println(String.format("�� ���ͷ��� %d%%�Դϴ�.", (threeCount*5000+fourCount*50000+fiveCount*150000+sixCount*2000000000)/amount*100));
        
	}

}
