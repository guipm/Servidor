package Cliente;


import java.awt.HeadlessException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

import javax.swing.JOptionPane;

/**
 * @author guilherme Mendes
 * Data 18/03/2017
 */
public class Cliente {
        
        private String ipServidor;
        private int porta;
        private String nome;
        private String mensagem;
        public String texto;
        
/**
     *
     * @param ipServidor_
     * @param porta_
     * @throws Exception
     */
        //Iniciando a comunicação com o servidor
        public  Cliente(String ipServidor_, int porta_, String nome_, String mensagem_)throws Exception{
           try{
               this.ipServidor = ipServidor_;       
               this.porta = porta_;
               this.nome = nome_;
               this.mensagem = mensagem_;
               Date data = new Date(System.currentTimeMillis());
               Socket clientSocket = new Socket (ipServidor_, porta_);

               //Prepara para receber e enviar mensagens ao servidor
               ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
               outputStream.flush();
               ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
               
               //Exibe a mensagem do servidor
               String message = (String)inputStream.readObject();
               System.out.println("Mensagem vinda do servidor: " + message);
               
               //Prepara mensagem do client e a envia ao servidor
               message = data + " - " + nome_ + " - " + mensagem_;  
               outputStream.writeObject(message);
               outputStream.flush();
               
               //Encerrando conexão
               inputStream.close();
               outputStream.close();
               clientSocket.close();
               
           }catch(HeadlessException | IOException e){ //caso haja erro de IP,porta ou servidor desligado.
               JOptionPane.showMessageDialog(null, "Erro o servidor pode estar Desligado...");
           }
        }
       
        //geters
        public String getIpServidor(){
            return this.ipServidor;
        }

        public int getPorta(){
            return this.porta;
        }
        //Seters
        public void setIpServidor(String ipServidor_){
            this.ipServidor = ipServidor_;
        }

        public void setPorta(int porta_) {
            this.porta = porta_;
        }

}
