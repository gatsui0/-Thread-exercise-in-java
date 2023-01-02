public class Main {
    public static void main(String[] args) {
        System.out.println("        Iniciando o programa!      ");
        Bar bar = new Bar(5, 2);
        try{
            bar.main();
        } catch(InterruptedException e){e.printStackTrace();}

    }
}