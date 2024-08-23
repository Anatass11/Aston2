import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        List<Transaction> res1 = transactions.stream().filter(x -> x.getYear() == 2011).sorted(Comparator.comparingInt(Transaction::getValue)).toList(); //задание 1
        System.out.println(res1);
        Set<String> res2 = transactions.stream().map(x -> x.getTrader().getCity()).collect(Collectors.toSet()); // задание 2
        System.out.println(res2);
        List<Trader> res3 = transactions.stream().filter(x -> x.getTrader().getCity().equals("Cambridge")).sorted((x,y) -> {
            return Integer.compare(x.getTrader().getName().compareTo(y.getTrader().getName()), 0);
        }).map(Transaction::getTrader).distinct().toList(); // задание 3
        System.out.println(res3);
        List<String> res4 = transactions.stream().sorted((x,y) -> {
            return Integer.compare(x.getTrader().getName().compareTo(y.getTrader().getName()), 0);
        }).map(Transaction::getTrader).distinct().map(Trader::getName).toList(); // задание 4
        System.out.println(res4);
        System.out.println("Milan is " +  transactions.stream().map(x -> x.getTrader().getCity()).anyMatch(x -> x.equals("Milan"))); //задание 5
        System.out.println(transactions.stream().filter(x -> x.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue).toList()); //задание 6
        System.out.println(transactions.stream().filter(x -> x.getTrader().getCity().equals("Cambridge")).mapToInt(Transaction::getValue).sum()); //задание 6 (я не понял формулировку)
        System.out.println(transactions.stream().mapToInt(Transaction::getValue).max().getAsInt()); //Задание 7
        System.out.println(transactions.stream().sorted((x,y) -> {
            return Integer.compare(x.getValue(), y.getValue());
        }).limit(1).toList().get(0));//задание 8
    }
}
