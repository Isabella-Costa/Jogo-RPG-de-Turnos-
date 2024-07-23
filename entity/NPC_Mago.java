package entity;
import java.util.Random;

import main.*;

public class NPC_Mago extends Entity {

    public NPC_Mago(GamePanel gp){
        super(gp);

        direction = "descida";
        speed = 1;

        getImage();
        setDialogo();
    }

    public void getImage() {
        subida1 = setup("/npc/magoSubida1", gp.tileSize,gp.tileSize);
        subida2 = setup("/npc/magoSubida2", gp.tileSize,gp.tileSize);
        descida1 = setup("/npc/magoDescida1", gp.tileSize,gp.tileSize);
        descida2 = setup("/npc/magoDescida2", gp.tileSize,gp.tileSize);
        esquerda1 = setup("/npc/magoEsquerda1", gp.tileSize,gp.tileSize);
        esquerda2 = setup("/npc/magoEsquerda2", gp.tileSize,gp.tileSize);
        direita1 = setup("/npc/magoDireita2", gp.tileSize,gp.tileSize);
        direita2 = setup("/npc/magoDireita1", gp.tileSize,gp.tileSize);
    }

    public void setDialogo() {
        dialogos[0] = "O reino de Arvandor é uma terra de vastas florestas, majestosas montanhas e antigas ruínas. \nGovernado pelo sábio Rei Alistair, o reino viveu em paz por muitos anos, \n" + //
                        "protegido por suas fortalezas e heróis lendários.";
        dialogos[1] = "No entanto, uma antiga ameaça despertou: Zargath, o Devastador, um monstro colossal \nque uma vez foi aprisionado pelos primeiros magos do reino.";
        dialogos[2] = "Nos confins das Montanhas Negras, um grupo de cultistas sombrios\n realizou um ritual proibido, liberando Zargath de sua prisão milenar. \nEm resposta, o Rei Alistair convocou os maiores heróis do reino para enfrentar essa ameaça.";
        dialogos[3] = "MAGO: Olá, eu sou Elyndor, um mago do Conselho dos Magos de Arvandor.";
        dialogos[4] = "PLAYER: Mestre Solis, ouvi falar da sua sabedoria. \nPreciso de sua orientação para deter Zargath.";
        dialogos[5] = "PLAYER: A Biblioteca Esquecida... claro, \né lá que encontrarei o Feitiço Proibido.";
        dialogos[6] = "MAGO: Cada enigma resolvido nos aproxima da derrota de Zargath.\n Mas cuidado com as armadilhas que você nao pode ver";
        dialogos[7] = "PLAYER:Essas armadilhas mágicas são complexas, \nmas nada que eu não possa resolver.";
        dialogos[8] = "PLAYER:Onde está esse Feitiço, Mago?";
        dialogos[9] = "MAGO:Este feitiço antigo deve está guardado num \nPortal Secreto. Assim que você estiver experência vai poder atravessá-lo";
        dialogos[10] = "PLAYER:Mestre Solis, encontrei o feitiço. \nO que devo fazer agora?";
        dialogos[11] = "PLAYER:Com este poder, posso enfraquecer Zargath \ne salvar nosso reino.";
        dialogos[12] = "MAGO: Alex,\n juntos podemos derrotar o Devastador.";
        dialogos[13] = "PLAYER:Vou canalizar toda a minha energia \nmágica para este feitiço.";
        dialogos[14] = "MAGO: Os elementais serão uma grande ajuda na \nbatalha contra Zargath.";
        dialogos[15] = "MAGO: Precisamos de uma defesa sólida para enfrentar Zargath.";
        dialogos[16] = "PLAYER:Vamos lutar pelo nosso \nreino e pelo nosso povo!";
        dialogos[17] = "MAGO: A magia é tanto uma arte quanto uma ciência. \nCada feitiço tem sua própria vida.";
        dialogos[18] = "PLAYER:A força bruta não derrotará Zargath. \nPrecisamos de estratégia e inteligência.";
        dialogos[19] = "MAGO: O conhecimento é a arma mais poderosa \nque um mago pode possuir.";
        dialogos[20] = "MAGO: Nunca subestime o poder da natureza. \nOs elementais são nossos aliados mais antigos.";
        dialogos[21] = "MAGO: Lembrem-se, coragem e sabedoria nos \nguiarão à vitória.";
        dialogos[22] = "PLAYER:Com união e determinação, venceremos Zargath \ne restauraremos a paz em Arvandor.";
    }
    
    public void setAction(){

        bloqueioDeAcaoContador++;
        
        if(bloqueioDeAcaoContador == 120){
          Random random = new Random();
          int i = random.nextInt(100) + 1; // escolhe um numero de 1 a 100

          if(i <= 25){
              direction = "subida";
          }
          if(i > 25 && i<= 50){
              direction = "descida";
          }
          if(i > 50 && i<= 75){
              direction = "esquerda";
          }
          if(i > 75 && i <= 100){
              direction = "direita";
          }

          bloqueioDeAcaoContador = 0;
    }

    }

    public void speak(){
        //fazer as coisas específicas do personagem
        super.speak();

    }
}
