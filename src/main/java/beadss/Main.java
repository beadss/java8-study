package beadss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
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
        Stream<Stream<Integer>> expectedLottoList =
        		Stream.of(
	        		Stream.of(new Random().nextInt(44) + 1)
	        		.distinct()
					.limit(6)
					.peek(System.out::println)
				)
				.limit(amount/price);
        
        /*
        
        for(int i = 0; i < amount/price; i++) {
        	expectedLottoList.add(Stream.generate(() -> random.nextInt(44) + 1).distinct().limit(6).collect(Collectors.toList()));
        }
        */
        
        System.out.println("���� �� ��÷ ��ȣ�� �Է��� �ּ���.");
        //scan.nextLine();
        //String correctLottoString = scan.nextLine();
        
        String correctLottoString = "1,2,3,4,5,6";
       
        List<Integer> correctLotto = new ArrayList<Integer>();
        
        Arrays.asList(correctLottoString.split(","))
        .subList(0, 6)
        .stream()
        .forEach(numberStr -> {correctLotto.add(Integer.parseInt(numberStr.trim()));});
        
        Map<Long, List<Long>> result = expectedLottoList
        .map(
    		(lotto) -> 
    			lotto.filter(
					expectedNumber -> correctLotto.stream().anyMatch((number) -> number == expectedNumber)
				)
			.count()
		)
        .collect(Collectors.groupingBy((count) -> count));
        
        System.out.println("��÷ ���");
        System.out.println("---------");
        
        
        System.out.println(String.format("3�� ��ġ (5000��)- %d��", getSize(result, 3)));
        System.out.println(String.format("4�� ��ġ (50000��)- %d��", getSize(result, 4)));
        System.out.println(String.format("5�� ��ġ (150000��)- %d��", getSize(result, 5)));
        System.out.println(String.format("6�� ��ġ (2000000000��)- %d��", getSize(result, 6)));
        System.out.println(String.format("�� ���ͷ��� %d%%�Դϴ�.", (getSize(result, 3)*5000+getSize(result, 4)*50000+getSize(result, 5)*150000+getSize(result, 6)*2000000000)/amount*100));
        
	}
	
	public static <T extends Number, K extends Number> int getSize(Map<T, List<T>> map, K key) {
		return map.containsKey(key)?map.get(key).size():0;
	}

}
