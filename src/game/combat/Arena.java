package game.combat;

import game.action.*;
import game.core.*;
import game.menu.Menu;
import game.resources.Mana;
import game.skill.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Arena {

    private Party partyA;
    private Party partyB;
    private int round = 1;
    private List<Player> generalPlayerList = new ArrayList<>();

    public Arena(Party partyA, Party partyB) {
        this.partyA = partyA;
        this.partyB = partyB;
    }

    public void start() {

        generalPlayerList.addAll(partyA.getAllMembers());
        generalPlayerList.addAll(partyB.getAllMembers());

        while (partyA.hasLivingMembers() && partyB.hasLivingMembers()) {

            CombatLog.register("\n--------- Rodada " + round + " ---------");

            Set<Player> pendingActions = new HashSet<>();
            for (Player p : generalPlayerList) {
                if (p.isAlive())
                    pendingActions.add(p);
            }

            while (!pendingActions.isEmpty()) {

                Player actor = getNextActor(pendingActions);
                if (actor == null)
                    break;

                Party targetParty =
                        partyA.getAllMembers().contains(actor) ? partyB : partyA;

                turn(actor, targetParty);
                actor.resetATB();
                pendingActions.remove(actor);

                if (!partyA.hasLivingMembers() || !partyB.hasLivingMembers())
                    break;
            }

            CombatLog.register("Fim da rodada " + round);
            round++;
        }

        if (!partyA.hasLivingMembers())
            CombatLog.register("Sua equipe foi dizimada!");
        else
            CombatLog.register("Todos os inimigos pereceram!");

        CombatLog.register("\nCombate encerrado.");
    }

    private void turn(Player actor, Party enemyParty) {

        if (!actor.isAlive())
            return;

        Action action;

        if (actor.isHuman()) {
            CombatLog.register("\nCombatente: " + actor.getName() + " [" + actor.getJob() + "]");
            CombatLog.register("HP: " + actor.getCurrentHP() + "/" + actor.getMaxHP());
            Mana mana = actor.getResource(Mana.class);
            CombatLog.register("MP: " + mana.getCurrent() + "/" + mana.getMax());
            action = Menu.select(actor);
        } else {
            action = IA.act(actor);
        }

        // ==== SKILL MENU ====
        if (action instanceof SkillMenuAction) {

            Skill skill = actor.isHuman()
                    ? Menu.selectSkill(actor)
                    : IA.selectSkill(actor);

            // VOLTAR → não perde o turno
            if (skill == null) {
                turn(actor, enemyParty);
                return;
            }

            switch (skill.getTargetType()) {

                case ENEMY_SINGLE -> {
                    Player target = actor.isHuman()
                            ? Menu.selectTarget(enemyParty.getLivingMembers())
                            : enemyParty.randomLivingMember();
                    if (target != null)
                        skill.use(actor, target);
                }

                case ALLY_SINGLE -> {
                    Party ally = partyA.getAllMembers().contains(actor) ? partyA : partyB;
                    Player target = actor.isHuman()
                            ? Menu.selectTarget(ally.getLivingMembers())
                            : ally.randomLivingMember();
                    if (target != null)
                        skill.use(actor, target);
                }

                case ENEMY_AREA -> {
                    for (Player p : enemyParty.getLivingMembers())
                        skill.use(actor, p);
                }

                case ALLY_AREA -> {
                    Party ally = partyA.getAllMembers().contains(actor) ? partyA : partyB;
                    for (Player p : ally.getLivingMembers())
                        skill.use(actor, p);
                }

                case SELF -> skill.use(actor, actor);
            }

            return;
        }

        // ==== ACTION NORMAL ====
        switch (action.getTargetType()) {

            case ENEMY_SINGLE -> {
                Player target = actor.isHuman()
                        ? Menu.selectTarget(enemyParty.getLivingMembers())
                        : enemyParty.randomLivingMember();

                if (target != null)
                    action.act(actor, target);
            }

            case SELF -> action.act(actor, actor);
        }
    }

    private Player getNextActor(Set<Player> availablePlayers) {
        int safety = 0;
        int maxTries = availablePlayers.size() * 10;

        while (safety < maxTries) {
            for (Player p : availablePlayers) {
                if (!p.isAlive())
                    continue;

                p.gainATB();

                if (p.isReady())
                    return p;
            }
            safety++;
        }

        return null;
    }

    private void processEndOfRoundEffects() {
        for (Player p : generalPlayerList) {
            if (p.isAlive()) {
                // buffs, debuffs, DoT, HoT etc
            }
        }
    }
}
