public class Arena {
    private Party partyA;
    private Party partyB;
    private int round = 1;

    public Arena(Party partyA, Party partyB) {
        this.partyA = partyA;
        this.partyB = partyB;
    }

    public void start() {
        while (partyA.hasLivingMembers() && partyB.hasLivingMembers()) {
            CombatLog.register("\n--------- Rodada " + round + " ---------");
            versusParty(partyA,partyB);
            versusParty(partyB,partyA);
            if (!partyA.hasLivingMembers() || !partyB.hasLivingMembers())
                break;
            CombatLog.register("Fim da rodada " + round);
            round++;
        }
        if (!partyA.hasLivingMembers()) 
            CombatLog.register("Sua equipe foi dizimada!");
        else
            CombatLog.register("Todos os inimigos pereceram!");
        CombatLog.register("\nCombate encerrado.");
    }

    private void turn(Player player, Player partyB) {
        if (!player.isAlive())
            return;
        if (player.isHuman()) {
            CombatLog.register("\nCombatente: " + player.getName());
            Action action = Menu.select(player);
            action.act(player, partyB);
        } else {
            Action action = IA.act(player);
            action.act(player, partyB);
        }
    }

    private void versusParty(Party playerParty, Party targetParty) {
        for (Player p : playerParty.getAllMembers()) {
            if (!p.isAlive())
                continue;
            Player target = targetParty.randomLivingMember();
            if (target == null)
                return;
            turn(p, target);
        }
    }
}