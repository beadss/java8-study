package beadss;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	
	
	private static int price = 1000;
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
        
        System.out.println("???????? ????? ?????.");
        
        //int amount = scan.nextInt();
        int amount = 14000;
        Stream<Stream<Integer>> expectedLottoList =
        		Stream.of(
	        		Stream.of(new Random().nextInt(44) + 1)
	        		.distinct()
					.limit(6)
				)
				.limit(amount/price);
        
        /*
        
        for(int i = 0; i < amount/price; i++) {
        	expectedLottoList.add(Stream.generate(() -> random.nextInt(44) + 1).distinct().limit(6).collect(Collectors.toList()));
        }
        */
        
        System.out.println("???? ?? ??¡À ????? ????? ?????.");
        //scan.nextLine();
        //String correctLottoString = scan.nextLine();
        
        String correctLottoString = "1,2,3,4,5,6";
       
        List<Integer> correctLotto = Arrays.stream(correctLottoString.split(","))
				.limit(6)
				.map(String::trim)
				.map(Integer::parseInt)
				.collect(Collectors.toList());

		expectedLottoList.forEach(lotto->lotto.forEach(System.out::println));
        
        Map<Long, List<Long>> result = new HashMap<>();
        /*
				expectedLottoList.map((lotto) -> lotto.peek(System.out::println).filter(expectedNumber -> correctLotto.contains(expectedNumber)).count())
								.filter(count ->  count == 3 || count == 5 || count == 6)
								.collect(Collectors.groupingBy((count) -> count));
        */
        System.out.println("??¡À ???");
        System.out.println("---------");
        
        
        System.out.println(String.format("3?? ??? (5000??)- %d??", getSize(result, 3)));
        System.out.println(String.format("4?? ??? (50000??)- %d??", getSize(result, 4)));
        System.out.println(String.format("5?? ??? (150000??)- %d??", getSize(result, 5)));
        System.out.println(String.format("6?? ??? (2000000000??)- %d??", getSize(result, 6)));
        System.out.println(String.format("?? ??????? %d%%????.", (getSize(result, 3)*5000+getSize(result, 4)*50000+getSize(result, 5)*150000+getSize(result, 6)*2000000000)/amount*100));
        
	}
	
	public static <T extends Number, K extends Number> int getSize(Map<T, List<T>> map, K key) {
		return map.containsKey(key)?map.get(key).size():0;
	}

}
