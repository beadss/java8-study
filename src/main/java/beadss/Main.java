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
        
        System.out.println("구입금액을 입력해 주세요.");
        
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
        
        
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
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
        
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println(String.format("3개 일치 (5000원)- %d개", threeCount));
        System.out.println(String.format("4개 일치 (50000원)- %d개", fourCount));
        System.out.println(String.format("5개 일치 (150000원)- %d개", fiveCount));
        System.out.println(String.format("6개 일치 (2000000000원)- %d개", sixCount));
        System.out.println(String.format("총 수익률은 %d%%입니다.", (threeCount*5000+fourCount*50000+fiveCount*150000+sixCount*2000000000)/amount*100));
        
	}

}
