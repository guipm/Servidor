package servidor;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;
import java.io.ObjectOutputStream;

/**
 * @author guilherme Mendes
 * Data 18/03/2017
 */
public class Servidor implements Runnable {
    ServerSocket server;
    private String mensagem;
    private int porta;
    
    /**
     *
     * @param porta_
     * @throws Exception
     */
    //Inicializado do Servidor
    public Servidor(int porta_,String mensagem_)throws Exception{
        this.porta = porta_;
        this.mensagem = mensagem_;
        ServerSocket server = new ServerSocket(porta_);
        new Thread(this).start();
        JOptionPane.showMessageDialog(null, "Servidor aguardando conexao para a porta :" + porta_);
        //.out.println("Servidor aguardando coexao para a porta :" + porta_);
    }
    //geters
    public int getPorta(){
        return this.porta;
    }
    //Seters
    public void setPorta(int porta_){
        this.porta = porta_;
    }
    //Manter Servidor Ativo
    public void rum(){
        try{
            while(true){
                new TrataCliente(server.accept()).start();
                }
            }catch(Exception e){
                    e.printStackTrace();
                    System.exit(1);
                }
        }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    class TrataCliente extends Thread{
       private Socket cliente;
       
       public TrataCliente(Socket s){
           cliente = s;
       }
       public void rum(){
           try{
               System.out.println("Conectado ao Cliente");   
           }catch (Exception e){
               JOptionPane.showMessageDialog(null,"Ocorreu um erro");
               System.exit(1);
           }
       }
    }     
}

