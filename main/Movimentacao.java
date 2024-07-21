package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Movimentacao implements KeyListener {
    GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    public Movimentacao(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e){

    }

    @Override
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();

        //Menu State
        if(gp.gameState == gp.menuState){
            titleState(code);
        }
        //Play State
        else if (gp.gameState == gp.playState){
            playState(code);
        }
        //Pause State
        else if(gp.gameState == gp.pauseState){     
            pauseState(code);
        }
        //Diagolo State
        else if(gp.gameState == gp.dialogoState){
            dialogoState(code);   
        }
        //Personagem State
        else if(gp.gameState == gp.personagemState){
            personagemState(code);
        }

    }

    public void titleState(int code){
        if(gp.ui.telaScreenState == 0){
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                gp.ui.menuNum--;
                if (gp.ui.menuNum < 0){
                    gp.ui.menuNum = 2;
                }
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                gp.ui.menuNum++;
                if (gp.ui.menuNum >  3){
                    gp.ui.menuNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER){
                 if(gp.ui.menuNum == 0){gp.ui.telaScreenState = 1;} 
                 else if(gp.ui.menuNum == 1){ }
                 else if(gp.ui.menuNum == 2){ /*fazer dps uma tela contando a história e ensinando como jogar */}
                 else if(gp.ui.menuNum == 3){ System.exit(0);}
            }
        }
        else if(gp.ui.telaScreenState == 1){
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                gp.ui.menuNum--;
                if (gp.ui.menuNum < 0){
                    gp.ui.menuNum = 2;
                }
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                gp.ui.menuNum++;
                if (gp.ui.menuNum >  2){
                    gp.ui.menuNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER){
                 if(gp.ui.menuNum == 0){
                   gp.gameState = gp.playState;
                 } 
                 else if(gp.ui.menuNum == 1){
                    gp.gameState = gp.playState;  //Mudar o personagem dps
                 }
                 else if(gp.ui.menuNum == 2){
                    gp.ui.telaScreenState = 0;
                 }
            }
        }
    }

    public void playState(int code){
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){ upPressed = true; }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){ downPressed = true; }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){ leftPressed= true; }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){ rightPressed = true;}
        if (code == KeyEvent.VK_ESCAPE){ gp.gameState = gp.pauseState; }
        if(code == KeyEvent.VK_SPACE){ gp.gameState = gp.personagemState; }
        if (code == KeyEvent.VK_ENTER){ enterPressed = true; }
    }

    public void pauseState(int code){
        if (code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
          }  
        
    }

    public void dialogoState(int code){
        if(code == KeyEvent.VK_ENTER){
            gp.gameState = gp.playState;

        }    
    }

    public void personagemState(int code){
        if(code == KeyEvent.VK_SPACE){
            gp.gameState = gp.playState;
        }
    }


    @Override
    public void keyReleased(KeyEvent e){
        
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            upPressed = false;
        }

        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed = false;
        }

        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed= false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed = false;
            
        }
    }
    
}
