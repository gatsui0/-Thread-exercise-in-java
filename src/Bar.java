import java.util.ArrayList;

public class Bar  {
    private int capacidadeMax;
    private int quantidadeRodada;
    public boolean fechouBar;
    ArrayList<ThreadCliente> filaDePedidos = new ArrayList<ThreadCliente>();

    Bar(int quantidadeRodada, int capacidadeMax){
        this.quantidadeRodada = quantidadeRodada;
        this.capacidadeMax = capacidadeMax;
    }

    public synchronized ThreadCliente getElement(){
        return filaDePedidos.get(0);
    }
    public synchronized void addElement(ThreadCliente client){
        filaDePedidos.add(client);
        filaDePedidos.trimToSize();
    }

    public synchronized void rmElement(){
        filaDePedidos.remove(0);
        filaDePedidos.trimToSize();
    }

    public synchronized boolean countElement(){
        return filaDePedidos.isEmpty();
    }

    public void main () throws InterruptedException {

        ThreadGarcom garcom1 = new ThreadGarcom(capacidadeMax, this, "garcom1");
        ThreadGarcom garcom2 = new ThreadGarcom(capacidadeMax, this, "garcom2");
//        ThreadGarcom garcom3 = new ThreadGarcom(capacidadeMax, this, "garcom3");
//        ThreadGarcom garcom4 = new ThreadGarcom(capacidadeMax, this, "garcom4");

        ThreadCliente cliente1 = new ThreadCliente(this, "client1", quantidadeRodada);
        ThreadCliente cliente2 = new ThreadCliente(this, "client2", quantidadeRodada);
        ThreadCliente cliente3 = new ThreadCliente(this, "client3", quantidadeRodada);
        ThreadCliente cliente4 = new ThreadCliente(this, "client4", quantidadeRodada);
//        ThreadCliente cliente5 = new ThreadCliente(this, "client5", quantidadeRodada);
//        ThreadCliente cliente6 = new ThreadCliente(this, "client6", quantidadeRodada);
//        ThreadCliente cliente7 = new ThreadCliente(this, "client7", quantidadeRodada);
//        ThreadCliente cliente8 = new ThreadCliente(this, "client8", quantidadeRodada);
//        ThreadCliente cliente9 = new ThreadCliente(this, "client9", quantidadeRodada);
//        ThreadCliente cliente10 = new ThreadCliente(this, "client10", quantidadeRodada);
//        ThreadCliente cliente11 = new ThreadCliente(this, "client11", quantidadeRodada);
//        ThreadCliente cliente12 = new ThreadCliente(this, "client12", quantidadeRodada);
//        ThreadCliente cliente13 = new ThreadCliente(this, "client13", quantidadeRodada);
//        ThreadCliente cliente14 = new ThreadCliente(this, "client14", quantidadeRodada);
//        ThreadCliente cliente15 = new ThreadCliente(this, "client15", quantidadeRodada);

    }
}
