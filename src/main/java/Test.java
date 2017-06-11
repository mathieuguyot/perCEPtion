import java.util.Optional;

/**
 * Created by Chloe on 10/06/2017.
 */
public class Test {

    public static void main(String[] args) {

        Optional<String> op = Optional.empty();

        System.out.println(op.isPresent());
        System.out.println(op.get());

    }

}
