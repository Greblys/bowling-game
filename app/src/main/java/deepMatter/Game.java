package deepMatter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game {

    private static final int FRAMES = 10;
    private static final int PINS = 10;

    private final ArrayList<Integer> balls = new ArrayList<>();
    private final int[] scores = new int[FRAMES];

    public void addBallScore(int score) {
        balls.add(score);
    }

    public void simulate() {
        int currentFrame = 0;
        int i = 0; // current ball index

        while(currentFrame < FRAMES) {
            try {
                // strike
                if (balls.get(i) == PINS) {
                    scores[currentFrame] = balls.get(i) + balls.get(i + 1) + balls.get(i + 2);
                    i++;
                // spare
                } else if (balls.get(i) + balls.get(i + 1) == PINS) {
                    scores[currentFrame] = balls.get(i) + balls.get(i + 1) + balls.get(i + 2);
                    i += 2;
                } else {
                    scores[currentFrame] = balls.get(i) + balls.get(i + 1);
                    i += 2;
                }
                currentFrame++;
            } catch (IndexOutOfBoundsException e) {
                System.err.println("Invalid input file. Some ball scores are missing");
                System.exit(-1);
            }
        }
    }

    public int[] getFramesScores() {
        return scores;
    }

    public int gameScore() {
        return Arrays.stream(scores).reduce(Integer::sum).getAsInt();
    }

    public static void main(String[] args) {
        if(args.length < 2) {
            System.err.println("Please provide input file with listed frames. If ball didn't hit pins, use 0");
            System.exit(-1);
        }

        String inputFile = args[1];
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(inputFile));
        } catch (FileNotFoundException e) {
            System.err.println(String.format("File %s does not exist. Please create one!", inputFile));
            System.exit(-1);
        }

        Game game = new Game();

        while(scanner.hasNextInt()){
            game.addBallScore(scanner.nextInt());
        }
        scanner.close();

        game.simulate();

        System.out.println("Game score: " + game.gameScore());
        System.out.println("Frames scores: " + Arrays.toString(game.getFramesScores()));
    }
}
