import java.util.Random;

public class ThreadCliente implements Runnable {

    private boolean fechouBar = false;
    private Bar bar;

    public String nome;
    public boolean pedidoChegou;
    private boolean consumir;
    private int rodadasMax;
    public int rodadas = 1;


    ThreadCliente(Bar bar, String nome, int rodadasMax){
        this.bar = bar;
        this.nome = nome;
        this.rodadasMax = rodadasMax;
        new Thread(this).start();

    }
    public void run() {
        while(!fechouBar){
            fazPedido();
            esperaPedido();
            recebePedido();
            consomePedido();
        }
    }

    private void fazPedido(){
        String pedido = nome + ": Fez um pedido";
        System.out.println(pedido);
        bar.addElement(this);
        pedidoChegou = false;
    }
    public synchronized void acordar(){
        notifyAll();
    }

    private synchronized void esperaPedido(){
        while(!pedidoChegou){
            try {
                wait();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    private void recebePedido(){
        System.out.println(nome + ": recebeu seu pedido!");

        //90% de chance do cliente aprovar o pedido
        Random rate = new Random();
        if(rate.nextInt(10) == 1){
            consumir = false;
        }
        else consumir = true;

    }
    private void consomePedido(){
        if (consumir){
            try {
                System.out.println(nome + ": consumiu o pedido!");
                Random gerador = new Random();
                Thread.sleep(gerador.nextInt(6) * 1000); // vai dormir por 0-6 segundos.
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        rodadas++;
        if(rodadas >= rodadasMax){
            fechouBar = true;
            bar.fechouBar = true;
        }
    }
}
