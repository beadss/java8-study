package sseonmi;

import sseonmi.step1.LottoGame;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Set<Integer> winningList = getRandomNumber();

        System.out.println("winningNumber : " + winningList);

        LottoGame lottoGame = new LottoGame(3000, winningList);

        System.out.println("result" + lottoGame.start());
    }


    /**
     * random number
     */
    public static Set<Integer> getRandomNumber() {
        Set<Integer> randomNumberSet = new HashSet<Integer>();
        while (randomNumberSet.size() < 6) {
            randomNumberSet.add((int) (Math.random() * 45 + 1));
        }

        return randomNumberSet;
    }

}
