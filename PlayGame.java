import java.util.Random;
import java.util.Scanner;

public class PlayGame {
    //Variáveiss do programa

    Scanner sc = new Scanner(System.in);
    int op0 = -1;
    int op1 = -1;
    int op2 = -1;
    int op3 = -1;

    String info = ("""
       \n =-=-=-=-INFORMAÇÕES SOBRE CADA PERSONAGEM:=-=-=-=-=-

        =-=-=-=-=-SOLDADO=-=-=-=-=-
        Indicado para jogadores amadores que gostam de ação em combate.
        Utiliza faca e todas as armas de fogo, além de dar soco e chute quando desarmado.
        
        HABILIDADE: Plantar bombas.
        ESPECIAL: Jogar uma granada.
        
        
        =-=-=-=-=-GENERAL=-=-=-=-=-
        Indicado para jogadores um pouco mais experientes.
        Faz tudo o que o SOLDADO faz, mas com outros comandos específicos para a sua patente.
        Utiliza faca e todas as armas de fogo, além de dar soco e chute quando desarmado.
        
        HABILIDADES: Plantar bombas e Tanque de Guerra.
        ESPECIAL: Chamar ataque aéreo.
        
        =-=-=-=-=-LUTADOR DE SUMÔ=-=-=-=-=- 
        Indicado para jogadores que gostam de um desafio.
        O LUTADOR DE SUMÔ não utiliza nenhuma arma, mas ele ainda tem grande poder mesmo desarmado.
        HABILIDADE: Quebrar estruturas densas.
        ESPECIAL: Barrigada.
        
        =-=-=-=-=-MAGO=-=-=-=-=-
        Indicado para jogadores que gostam de dar suporte a equipe.
        O MAGO não utiliza nenhuma arma, nem mesmo dar soco e chute.
        A sua arma é exclusiva, e está no seu bastão, atirando poder mágico dele para dar dano, e também ajudar os aliados.
        
        HABILIDADES: Conhecimento, Criar Feitiços, Teletransportar e Curar Aliados.
        ESPECIAL: Invulnerabilidade.
        
        =-=-=-=-=-DRAGÃO ALADO=-=-=-=-=-
        Indicado para jogadores que querem "torrar" os inimigos mais rapidamente.
        O DRAGÃO ALADO não utiliza armas. Afinal, para que usar uma arma quando se tem um poder de cuspir fogo nos inimigos?
        
        HABILIDADES: Transportar aliados e voar.
        ESPECIAL: Cuspir Lava.
        """);
    //Variáveis do modo "Jogar Soldado x Dragão"

        Random  rd = new Random();
        int opUser = -1 ; //Opção do usuário

        while (op0 != 0) {
          System.out.println("""
            \n=-=-=-=-=MENU INICIAL=-=-=-=-=-=

            Escolha uma opcao de jogo 

            1 - Testar personagens
            2 - Soldado x Dragão

            0 - SAIR DO JOGO

                   """);
            
            op0 = sc.nextInt();

            switch (op0) {
                case 0:
                    break;
                //Modo de Jogo "Testar Personagem"
                case 1:
                     while (op0 != 0) {
                     System.out.println("""
                             \n=-=-=-=-=MENU DE TESTE DE PERSONAGENS=-=-=-=-=-=
                             Escolha um  personagem:

                             1. Soldado
                             2. General
                             3. Lutador
                             4. Mago
                             5. Dragao Alado

                             6. Conhecer Personagens e suas Habilidades

                             0. Voltar ao Menu Inicial

                             """);
                            
                        System.out.println("Digite a sua opcao aqui: ");
                        op1 = sc.nextInt();
                        switch (op1) {
                            case 0: break;

                            case 1 :
                            Soldado sol = new Soldado();
                            sol.desenhar();
                            
                            while (op2 != 0) {
                                System.out.println("""
                                            \n=-=-=-=-=-SOLDADO=-=-=-=-=-
                                            +Escolha uma habilidade do Soldado

                                            1. Falar
                                            2. Correr
                                            3. Armar 
                                            4. Usar Arma
                                            5. Largar Arma
                                            6. Plantar Bombas
                                            7. Especial

                                            0. Trocar de Personagem
                                            """);
                                System.out.println("\nDigite a sua opcao aqui: ");
                                op2  = sc.nextInt();

                                switch (op2) {
                                    case 0:
                                        sol.morrer();
                                        System.out.println("\nVoltando ao menu anterior");
                                        break;
                                    case 1:
                                        sol.falar();
                                    case 2:
                                        sol.correr();
                                    case 3:
                                        if (op3 >= 1 || op3 <= 4) {
                                            System.out.println("\nVocê já está armado!");
                                            
                                        } else {
                                            while (op3 < 1 || op3 > 4) { 
                                                System.out.println("""
                                                        \n=-=-=-=-=-ARMAMENTO=-=-=-=-=-
                                                        
                                                        Escolher uma arma:

                                                        1. Soco 
                                                        2. Faca
                                                        3. Pistola
                                                        4. Fuzil
                                                        """);

                                                System.out.println("Digite a sua opcao aqui: ");
                                                op3 = sc.nextInt();

                                                if (op3 < 0 || op3 > 4){
                                                    System.out.println("\nDigite uma opcao valida!");
                                                }        
                                            }

                                            System.out.println("\nArma SELECIONADA!");
                                        }
                                    case 4: {
                                        switch (op3) {
                                            case -1: {
                                                System.out.println("\nVocê não está armado!");
                                                }
                                            case 1: {
                                                Desarmado des = new Desarmado();
                                                des.usarArma();
                                                }
                                            case 2: {
                                                Faca faca = new Faca();
                                                faca.usarArma();
                                                }
                                            case 3:{
                                                Pistola pistola = new Pistola();
                                                pistola.usarArma();
                                                }
                                            case 4: {
                                                Fuzil fuzil = new Fuzil();
                                                fuzil.usarArma();
                                                }
                                        }
                                    }
                                    case 5:{
                                        if(op3 == -1){
                                        System.out.println("\nVocê não está armado!");}
                                        else{
                                            sol.largarArma();
                                            op3 = -1;}
                                        }

                                    case 6: {
                                        sol.plantarBombas();
                                        }
                                    case 7: {
                                        sol.especial();}

                                    default: System.out.println("Opcao invalida. Tente novamente");         
                                }
                                }
                                op1 = -1;
                                op2 = -1;
                                op3 = -1;

                            //GENERAL
                            case 2:{
                                General gen = new General();
                                gen.desenhar();

                                while(op2 != 0){
                                    System.out.println("""
                                        \n=-=-=-=-=-GENERAL=-=-=-=-=-
                                        +Escolha uma habilidade do General

                                        1. Falar
                                        2. Correr
                                        3. Armar 
                                        4. Usar Arma
                                        5. Largar Arma
                                        6. Plantar Bombas
                                        7.Tanque de Guerra
                                        8. Especial

                                        0. Trocar de Personagem
                                        """);
                                System.out.println("\nDigite a sua opcao aqui: ");
                                op2  = sc.nextInt();
                                }

                                switch (op2) {
                                    case 0:
                                        gen.morrer();
                                        System.out.println("\nVoltando ao menu anterior");
                                        break;
                                    case 1:
                                    gen.falar();
                                    case 2:
                                    gen.correr();
                                    case 3:
                                        if (op3 >= 1 || op3 <= 4) {
                                            System.out.println("\nVocê já está armado!");
                                            
                                        } else {
                                            while (op3 < 1 || op3 > 4) { 
                                                System.out.println("""
                                                        \n=-=-=-=-=-ARMAMENTO=-=-=-=-=-
                                                        
                                                        Escolher uma arma:

                                                        1. Soco 
                                                        2. Faca
                                                        3. Pistola
                                                        4. Fuzil
                                                        """);
                                                System.out.println("Digite a sua opcao aqui: ");
                                                op3 = sc.nextInt();

                                                if (op3 < 0 || op3 > 4){
                                                    System.out.println("\nDigite uma opcao valida!");
                                                }        
                                            }

                                            System.out.println("\nArma SELECIONADA!");
                                        }
                                    case 4: {
                                        switch (op3) {
                                            case -1: {
                                                System.out.println("\nVocê não está armado!");
                                                }
                                            case 1: {
                                                Desarmado des = new Desarmado();
                                                des.usarArma();
                                                }
                                            case 2: {
                                                Faca faca = new Faca();
                                                faca.usarArma();
                                                }
                                            case 3:{
                                                Pistola pistola = new Pistola();
                                                pistola.usarArma();
                                                }
                                            case 4: {
                                                Fuzil fuzil = new Fuzil();
                                                fuzil.usarArma();
                                            }
                                        }
                                    }
                                    case 5:{
                                        if(op3 == -1){
                                        System.out.println("\nVocê não está armado!");}
                                        else{
                                            gen.largarArma();
                                            op3 = -1;}
                                        }

                                    case 6: {
                                        gen.plantarBombas();
                                        }
                                    case 7:
                                         gen.tanqueDeGuerra();
                                    case 8: {
                                        gen.especial();}

                                    default: System.out.println("Opcao invalida. Tente novamente");         
                                }
                                }
                                op1 = -1;
                                op2 = -1;
                                op3 = -1;

                            //LUTADOR SUMO
                            case 3:{
                                LutSUMO lut = new LutSUMO();
                                lut.desenhar();
                                Desarmado des = new Desarmado();

                                while(op2 != 0){
                                    System.out.println("""
                                        \n=-=-=-=-=-LUTADOR DE SUMO=-=-=-=-=-
                                        +Escolha uma habilidade do Lutador de Sumo

                                        1. Falar
                                        2. Correr
                                        3. Usar Arma
                                        4. Quebrar Estruturas
                                        5. Especial

                                        0. Trocar de Personagem
                                        """);
                                System.out.println("\nDigite a sua opcao aqui: ");
                                op2  = sc.nextInt();
                                

                                switch (op2) {
                                    case 0:
                                        lut.morrer();
                                        System.out.println("\nVoltando ao menu anterior");
                                        break;
                                    case 1:
                                        lut.falar();
                                    case 2:
                                        lut.correr();
                                    case 3:{
                                        des.usarArma();
                                    }
                                           
                                    case 4: {
                                        lut.quebrarEstruturas();
                                    }
                                    case 5: {
                                        lut.especial();
                                    }
                                    default: System.out.println("\nOpcao Invalida");
                            }
                            }
                            op1 = -1;
                            op2 = -1;
                        }

                            
                            //MAGO
                            case 4:{
                                Mago mago = new Mago();
                                mago.desenhar();
                                Encantamento enc = new Encantamento();
                                

                                while(op2 != 0){
                                   System.out.println("""
                                       \n=-=-=-=-=-MAGO=-=-=-=-=-
                                        +Escolha uma habilidade do Mago:

                                        1. Falar
                                        2. Correr
                                        3. Usar Ataque Mágico
                                        4. Conhecimento
                                        5. Criar Feitico
                                        6. teletransportar
                                        7. Curar Aliados
                                        8. Usar Eapecial

                                        0. Trocar de Personagem""");

                                    System.out.println("\nDigite a sua opcao aqui: ");
                                    op2  = sc.nextInt();

                                    switch (op2) {
                                        case 0:
                                            mago.morrer();
                                            System.out.println("\nVoltando ao menu anterior");
                                            break;
                                        case 1:
                                            mago.falar();
                                        case 2:
                                            mago.correr();
                                        case 3:{
                                            enc.usarArma();
                                        }
                                               
                                        case 4: {
                                            mago.conhecimento();
                                        }
                                        case 5: {
                                            mago.criarFeitiços();
                                        }
                                        case 6:{
                                            mago.teletransportar();
                                        }
                                        case 7: {
                                            mago.curarAliados();
                                        }
                                        case 8:{
                                            mago.especial();
                                        }
                                        default: System.out.println("\nOpcao Invalida");

                                    
                                    }
                                }
                                op1 = -1;
                                op2 = -1;
                                }

                                case 5:{
                                    DragaoAlado drag = new DragaoAlado();
                                    drag.desenhar();
                                    BolaDeFogo bdf = new BolaDeFogo();

                                    while (op2 != 0){
                                        System.out.println("""
                                       \n=-=-=-=-=-DRAGAO  ALADO=-=-=-=-=-
                                        +Escolha uma habilidade do Dragao:

                                        1. Falar
                                        2. Correr
                                        3. Cuspir Fogo
                                        4. Voar
                                        5. Carregar Aliados
                                        6. Usar Especial

                                        0. Trocar de Personagem""");

                                    System.out.println("\nDigite a sua opcao aqui: ");
                                    op2  = sc.nextInt();

                                    switch (op2) {
                                        case 0:
                                            drag.morrer();
                                            System.out.println("\nVoltando ao menu anterior");
                                            break;
                                        case 1:
                                            drag.falar();
                                        case 2:
                                            drag.correr();
                                        case 3:{
                                            bdf.usarArma();
                                        }
                                               
                                        case 4: {
                                            drag.voar();
                                        }
                                        case 5: {
                                            drag.carregarAliados();
                                        }
                                        case 6:{
                                            drag.especial();
                                        }
                                        default: System.out.println("\nOpcao Invalida");
                                }
                                }
                                    op1 = -1;
                                    op2 = -1;
                
                               }

                               //INFORMAÇÔES
                               case 6:{
                                System.out.println("\n" + info);
                               }

                               default: System.out.println("\nOpcao Invalida");
                             }
                             }
                            op1 = -1;
                            op2 = -1;
                            System.out.println("\nVoltando ao menu inicial...");
                            
                        case 2:{

                        }
                        }
                     
                    }
}