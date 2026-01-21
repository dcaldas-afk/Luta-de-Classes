
public class Mage extends Player {

    public Mage(String name, boolean ifHuman) {
        super(name, 100, ifHuman);

        actions.add(new Attack());
    }
}