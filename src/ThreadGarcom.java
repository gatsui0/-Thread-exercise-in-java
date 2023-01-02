import java.util.ArrayList;
import java.util.Random;

public class ThreadGarcom implements Runnable {
    private int capacidade;
    private Bar bar;
    private boolean fechouBar = false;
    private String nome;
    private int rodada = 1;

    ArrayList<ThreadCliente> filaDePedidos = new ArrayList<ThreadCliente>();

    ThreadGarcom(int capacidadeMax, Bar bar, String nome) throws InterruptedException {
        this.bar = bar;
        this.capacidade = capacidadeMax;
        this.nome = nome;
        Thread t = new Thread(this);
        t.start();

    }

    public void run() {
        while(!fechouBar){
            recebeMaximoDePedido();
            registraPedido();
            entregaPedidos();
            rodada++;
        }
    }

    private synchronized void recebeMaximoDePedido(){
        while(filaDePedidos.size() <= capacidade){
            if(!bar.countElement()){
                filaDePedidos.add(bar.getElement());
                bar.rmElement();

                filaDePedidos.trimToSize();
            }
        }
    }
    private synchronized void registraPedido(){
        try{
            for (int i = 0; i < capacidade; i++){
                Random time = new Random();
                Thread.sleep(time.nextInt(10));
                System.out.println("Garçom " + nome + ": registrou o pedido de " + filaDePedidos.get(i).nome);
        }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private synchronized void entregaPedidos(){
        for (ThreadCliente client : filaDePedidos){
            client.rodadas = rodada;
            client.pedidoChegou = true;
            client.acordar();
        }
        filaDePedidos.clear();
        filaDePedidos.trimToSize();
        fechouBar = bar.fechouBar;
    }
}
