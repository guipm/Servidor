package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 * @author guilherme Mendes
 * Data 18/03/2017
 */
public class Servidor implements Runnable {
    ServerSocket server;
//    private String mensagem;
    private int porta;    
    /**
     *
     * @param porta_
     * @throws Exception
     */
    //Inicializado do Servidor
    public Servidor(int porta_)throws Exception{
        
        this.porta = porta_;
        ServerSocket server = new ServerSocket(porta_);
        new Thread(this).start();
     //   Socket socket = server.accept();
        JOptionPane.showMessageDialog(null, "Servidor aguardando conexao para a porta :" + porta_);
        

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
       @Override
       public void run(){
           try{
                while(true){
                    new MensagemCliente(server.accept()).start();
              // System.out.println("Conectado ao Cliente"); 
                }
           }catch (Exception e){
               JOptionPane.showMessageDialog(null,"Ocorreu um erro aqui");
               System.exit(1);
           }
       } 
       
    class MensagemCliente extends Thread{
       private Socket dados;
       
       public MensagemCliente(Socket s){
           dados = s;
       }
       
       public void run(){
            try {
               InputStream input = dados.getInputStream();
               OutputStream output = dados.getOutputStream();
        
               BufferedReader in = new BufferedReader(new InputStreamReader(input));
               PrintStream out = new PrintStream (output);
          
               String mensagem = in.readLine();
               new ArquivoLog(mensagem);

               in.close();
               out.close();
               dados.close();
               
            } catch (IOException ex) {
               JOptionPane.showMessageDialog(null,"Ocorreu um erro TextoCliente");
               Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
           }           
       }
    }     
}

