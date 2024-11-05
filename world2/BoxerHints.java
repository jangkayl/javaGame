package world2;

import world1.GameLogic;

public class BoxerHints {
    private String[][] skillsWithHints = {
        {"Jab", 
            "The Bruiser's fists hover in front, his stance loose, ready to test your guard.",
            "He keeps his distance, his hands moving in quick feints to gauge your response."
        },
        {"Hook", 
            "You catch him shifting his weight, his shoulders tightening - a powerful swing might be coming.",
            "He steps slightly to the side, setting himself up for a heavy, arcing strike."
        },
        {"Block", 
            "His elbows tuck in close, and his stance steadies, as if preparing to absorb a hit.",
            "He plants his feet firmly, guard high, waiting for your move."
        },
        {"Uppercut", 
            "The Bruiser's body lowers slightly, fists clenched, ready to spring upward.",
            "He tightens his core, his stance compact, as if preparing for an explosive strike."
        },
        {"Elbow Strike", 
            "He sidles closer, his stance narrowing, like he's about to pull a dirty move.",
            "You notice his arm shifting subtly, as if he's hiding something behind his guard."
        },
        {"Head Butt", 
            "His chin dips, eyes unwavering as he edges closer, looking for an opening.",
            "He leans forward, his forehead positioned dangerously close to yours."
        },
        {"Low Blow", 
            "He glances down briefly, adjusting his stance lower than usual.",
            "His gaze flickers down, his fists lowering ever so slightly - something's off."
        }
    };

    public static void teachHints() {
        BoxerHints boxerHints = new BoxerHints(); 
        GameLogic.printSeparator(100);
        System.out.println("\n\t*** Read Opponent's First Move Hints ***");
        System.out.println();
        for (String[] skillWithHints : boxerHints.skillsWithHints) {
            System.out.println("\t" + skillWithHints[0] + ":");
            System.out.println("\t\t\t- " + skillWithHints[1]);
            System.out.println("\t\t\t- " + skillWithHints[2]);
        }
        System.out.println("\n\t( You take in the lessons, ready to outsmart your opponents. )");
        GameLogic.pressAnything();
    }

}
