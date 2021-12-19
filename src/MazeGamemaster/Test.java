package MazeGamemaster;

import MazeGamemaster.Entity.Agent;

public class Test {
    public static void main(String[] args) {
        Agent player = new Agent(3);
        Thread thread = new Thread(player);
        thread.start();
//        player.getCountDown();

        
//        while (LocalDateTime.now().isBefore(twoSecondsLater)) {
//            assertThat(newDatabase).isEmpty();
//            Thread.sleep(500);
//        }
//        assertThat(newDatabase).containsExactlyElementsOf(oldDatabase);

    }

}
