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
        dialogos[0] = "MAGO: Olá, eu sou Elyndor, um mago do Conselho dos Magos de Arvandor.";
        dialogos[1] = "PLAYER: Mestre Solis, ouvi falar da sua sabedoria. \nPreciso de sua orientação para deter Zargath.";
        dialogos[2] = "PLAYER: A Biblioteca Esquecida... claro, \né lá que encontrarei o Feitiço Proibido.";
        dialogos[3] = "PLAYER:Essas armadilhas mágicas são complexas, \nmas nada que eu não possa resolver.";
        dialogos[4] = "MAGO: Cada enigma resolvido nos aproxima \nda derrota de Zargath.";
        dialogos[5] = "PLAYER:Consegui! Os pergaminhos do \nFeitiço Proibido estão em minhas mãos.";
        dialogos[6] = "MAGO:Este feitiço antigo deve ser usado \ncom cuidado e precisão.";
        dialogos[7] = "PLAYER:Mestre Solis, encontrei o feitiço. \nO que devo fazer agora?";
        dialogos[8] = "PLAYER:Com este poder, posso enfraquecer Zargath \ne salvar nosso reino.";
        dialogos[9] = "MAGO: Alex,\n juntos podemos derrotar o Devastador.";
        dialogos[10] = "PLAYER:Vou canalizar toda a minha energia \nmágica para este feitiço.";
        dialogos[11] = "MAGO: Os elementais serão uma grande ajuda na \nbatalha contra Zargath.";
        dialogos[12] = "MAGO: Precisamos de uma defesa sólida para enfrentar Zargath.";
        dialogos[13] = "PLAYER:Vamos lutar pelo nosso \nreino e pelo nosso povo!";
        dialogos[14] = "MAGO: A magia é tanto uma arte quanto uma ciência. \nCada feitiço tem sua própria vida.";
        dialogos[15] = "PLAYER:A força bruta não derrotará Zargath. \nPrecisamos de estratégia e inteligência.";
        dialogos[16] = "MAGO: O conhecimento é a arma mais poderosa \nque um mago pode possuir.";
        dialogos[17] = "MAGO: Nunca subestime o poder da natureza. \nOs elementais são nossos aliados mais antigos.";
        dialogos[18] = "MAGO: Lembrem-se, coragem e sabedoria nos \nguiarão à vitória.";
        dialogos[19] = "PLAYER:Com união e determinação, venceremos Zargath \ne restauraremos a paz em Arvandor.";
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
