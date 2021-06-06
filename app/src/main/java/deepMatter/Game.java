package deepMatter;

import deepMatter.Frame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Game {

    private static final int FRAMES = 10;
    private static final int PINS = 10;
    private int currentIndex = 0;

    private Frame[] frames = new Frame[FRAMES];

    public void addFrame(Frame f) {
        frames[currentIndex] = f;
        currentIndex++;
    }

    public int[] getFramesScores() {
        int[] scores = new int[FRAMES];
        for(int i = 0; i < scores.length; i++){
            scores[i] = getFrameScore(i);
        }
        return scores;
    }

    public int gameScore() {
        return Arrays.stream(getFramesScores()).reduce(Integer::sum).getAsInt();
    }

    private int getNextBallScore(int frameIndex) {
        int nextFrameIndex = frameIndex + 1;
        if (nextFrameIndex < frames.length && frameIndex > 0) {
            return frames[nextFrameIndex].getFirstBall();
        } else {
            return 0;
        }
    }

    private int getNextTwoBallsScore(int frameIndex) {
        int nextFrameIndex = frameIndex + 1;
        if (nextFrameIndex < frames.length && frameIndex > 0) {
            return frames[nextFrameIndex].getFirstBall() + frames[nextFrameIndex].getSecondBall();
        } else {
            return 0;
        }
    }

    private int getFrameScore(int frameIndex) {

        int score = frames[frameIndex].getFirstBall() + frames[frameIndex].getSecondBall();

        if(frames[frameIndex].getFirstBall() == PINS) {
            return PINS + getNextTwoBallsScore(frameIndex + 1);
        } else if (score == PINS) {
            return score + getNextBallScore(frameIndex);
        } else {
            return score;
        }
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
            int firstBall = scanner.nextInt();
            if(scanner.hasNextInt()) {
                int secondBall = scanner.nextInt();
                Frame frame = new Frame(firstBall, secondBall);
                game.addFrame(frame);
            } else {
                System.err.println("Invalid input! Frame is missing a number of knocked out pins for a 2nd ball.");
                System.exit(-1);
            }
        }

        System.out.println("Game score: ");

    }
}
