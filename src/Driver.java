public class Driver
{
    public static void main(String[] args)
    {
        int[] test = new int[14];
        test[0] = 2;
        test[7] = 5;

        Mancala game = new Mancala(test);
        System.out.println(game);

        System.out.println(game.move(7));
        System.out.println(game);
    }
}
