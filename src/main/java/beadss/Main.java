package beadss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	
	
	private static int price = 1000;
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
        
        System.out.println("���Աݾ��� �Է��� �ּ���.");
        
        //int amount = scan.nextInt();
        int amount = 14000;
        
        Vector<List<Integer>> expectedLottoList = new Vector<List<Integer>>(amount/price); 
        
        Random random = new Random();
        
        for(int i = 0; i < amount/price; i++) {
        	expectedLottoList.add(Stream.generate(() -> random.nextInt(44) + 1).distinct().limit(6).collect(Collectors.toList()));
        }
        
        expectedLottoList.stream().forEach(System.out::println);
        
        System.out.println("���� �� ��÷ ��ȣ�� �Է��� �ּ���.");
        //scan.nextLine();
        //String correctLottoString = scan.nextLine();
        
        String correctLottoString = "1,2,3,4,5,6";
       
        List<Integer> correctLotto = new ArrayList<Integer>();
        
        Arrays.asList(correctLottoString.split(","))
        .subList(0, 6)
        .stream()
        .forEach((numberStr)->{correctLotto.add(Integer.parseInt(numberStr.trim()));});
        
        int threeCount = 0;
        int fourCount = 0;
        int fiveCount = 0;
        int sixCount = 0;
        
        
        for(List<Integer> lotto : expectedLottoList) {        	
        	long matchCount = lotto.stream().filter((expectedNumber) -> correctLotto.stream().anyMatch((number) -> number == expectedNumber)).count();
        	
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
